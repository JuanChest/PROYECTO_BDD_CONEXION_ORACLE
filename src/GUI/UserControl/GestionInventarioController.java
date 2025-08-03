package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GestionInventarioController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colCantidad;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdInventario;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdTienda;

    @FXML
    private TableView<ObservableList<String>> tablaInventario;

    public static ObservableList<String> inventarioSeleccionado;

    @FXML
    public void initialize() {
        cargarDatos();
        ajustarInterfazPorConexion();
    }

    private void ajustarInterfazPorConexion() {
        System.out.println("Tipo de conexión actual: " + Util.ContextoConexion.getTipoConexion());

        if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
            System.out.println("Modo REMOTO: Ocultando botones");
            btnAgregar.setVisible(false);
            btnEditar.setVisible(false);
            btnEliminar.setVisible(false);
        } else {
            System.out.println("Modo MASTER: Mostrando botones");
            btnAgregar.setVisible(true);
            btnEditar.setVisible(true);
            btnEliminar.setVisible(true);
        }
    }

    private void cargarDatos() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT INVENTARIO_ID, ID_TIENDA, CANTIDAD FROM INVENTARIO ORDER BY INVENTARIO_ID"
            );

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("INVENTARIO_ID"));
                row.add(rs.getString("ID_TIENDA"));
                row.add(rs.getString("CANTIDAD"));
                data.add(row);
            }

            colIdInventario.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(0)));
            colIdTienda.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(1)));
            colCantidad.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(2)));

            tablaInventario.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void agregarNuevoInventario(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioInventario.fxml", "Formulario de Inventario");
    }

    @FXML
    void editarInventario(ActionEvent event) throws Exception {
        inventarioSeleccionado = tablaInventario.getSelectionModel().getSelectedItem();
        if (inventarioSeleccionado != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/ModificadorInventario.fxml", "Editar Inventario");
        } else {
            mostrarAlerta("Seleccione un inventario para editar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarInventario() {
        ObservableList<String> seleccionado = tablaInventario.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try (Connection conn = ConexionOracleMaster.getConnection()) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM INVENTARIO WHERE INVENTARIO_ID = " + seleccionado.get(0));
                cargarDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Seleccione un inventario para eliminar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
