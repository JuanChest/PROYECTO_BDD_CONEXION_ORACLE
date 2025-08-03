package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    void agregarNuevoInventario() throws Exception {
        abrirVentana("GUI/Interfaz/FormularioInventario.fxml", "Registrar Inventario");
    }

    @FXML
    void editarInventario() throws Exception {
        inventarioSeleccionado = tablaInventario.getSelectionModel().getSelectedItem();
        if (inventarioSeleccionado != null) {
            abrirVentana("GUI/Interfaz/ModificadorInventario.fxml", "Modificar Inventario");
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
    void regresar() {
        ((Stage) btnEliminar1.getScene().getWindow()).close();
    }

    private void abrirVentana(String fxml, String titulo) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        cargarDatos(); // refrescar tabla al cerrar
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
