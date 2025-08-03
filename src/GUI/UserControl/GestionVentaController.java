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

public class GestionVentaController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colFecha;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdCliente;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdTienda;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdVenta;

    @FXML
    private TableColumn<ObservableList<String>, String> colTotal;

    @FXML
    private TableView<ObservableList<String>> tablaVentas;

    public static ObservableList<String> ventaSeleccionada;

    @FXML
    public void initialize() {
        cargarDatos();
    }

    private void cargarDatos() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT VENTA_ID, ID_TIENDA, CLIENTE_ID, TO_CHAR(FECHA, 'YYYY-MM-DD') AS FECHA, TOTAL FROM VENTAS ORDER BY VENTA_ID");

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("VENTA_ID"));
                row.add(rs.getString("ID_TIENDA"));
                row.add(rs.getString("CLIENTE_ID"));
                row.add(rs.getString("FECHA"));
                row.add(rs.getString("TOTAL"));
                data.add(row);
            }

            colIdVenta.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(0)));
            colIdTienda.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(1)));
            colIdCliente.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(2)));
            colFecha.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(3)));
            colTotal.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(4)));

            tablaVentas.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void agregarNuevoVenta(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage,"/GUI/Interfaz/FormularioVentas.fxml", "Registrar Venta");
    }

    @FXML
    void editarVenta(ActionEvent event) throws Exception {
        ventaSeleccionada = tablaVentas.getSelectionModel().getSelectedItem();
        if (ventaSeleccionada != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/ModificadorVenta.fxml", "Modificar Venta");
        } else {
            mostrarAlerta("Seleccione una venta para editar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarVenta() {
        ObservableList<String> seleccionada = tablaVentas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            try(Connection conn = ConexionOracleMaster.getConnection()){
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM VENTAS WHERE VENTA_ID = '" + seleccionada.get(0) + "'");
                cargarDatos();
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Seleccione una venta para eliminar", Alert.AlertType.WARNING);
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
