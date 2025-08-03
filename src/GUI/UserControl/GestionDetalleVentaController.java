package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

public class GestionDetalleVentaController {

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
    private TableColumn<ObservableList<String>, String> colIdDetalle;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdProducto;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdVenta;

    @FXML
    private TableColumn<ObservableList<String>, String> colPrecioUnitario;

    @FXML
    private TableColumn<ObservableList<String>, String> colSubtotal;

    @FXML
    private TableView<ObservableList<String>> tablaDetalles;

    public static ObservableList<String> detalleSeleccionado;

    @FXML public void initialize() {
        cargarDatos();
    }
    public void cargarDatos() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "SELECT VENTA_ID, DETALLE_ID, PRODUCTO_ID, CANTIDAD, PRECIO_UNITARIO, SUB_TOTAL " +
                            "FROM DETALLE_VENTA ORDER BY VENTA_ID, DETALLE_ID");

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("VENTA_ID"));
                row.add(rs.getString("DETALLE_ID"));
                row.add(rs.getString("PRODUCTO_ID"));
                row.add(rs.getString("CANTIDAD"));
                row.add(rs.getString("PRECIO_UNITARIO"));
                row.add(rs.getString("SUB_TOTAL"));
                data.add(row);
            }

            colIdVenta.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(0)));
            colIdDetalle.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(1)));
            colIdProducto.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(2)));
            colCantidad.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(3)));
            colPrecioUnitario.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(4)));
            colSubtotal.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(5)));

            tablaDetalles.setItems(data);
            ajustarInterfazPorConexion();

        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @FXML
    void agregarNuevoDetalleVenta() throws Exception {
        abrirVentana("GUI/Interfaz/FormularioDetalleVenta.fxml","Registrar Detalle de Venta");
    }

    @FXML
    void editarDetalleVenta() throws Exception {
        detalleSeleccionado = tablaDetalles.getSelectionModel().getSelectedItem();
        if(detalleSeleccionado != null) {
            abrirVentana("GUI/Interfaz/ModificadorDetalleVenta.fxml", "Modificar Detalle de venta");
        } else {
            mostrarAlerta("Seleccione un detalle de venta para editar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarDetalleVenta() {
        ObservableList<String> seleccionada = tablaDetalles.getSelectionModel().getSelectedItem();
        if(seleccionada != null) {
            try(Connection conn = ConexionOracleMaster.getConnection()){
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM DETALLE_VENTA WHERE VENTA_ID = " + seleccionada.get(0) + " AND DETALLE_ID = " + seleccionada.get(1));

                cargarDatos();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            mostrarAlerta("Seleccione un detalle de venta para eliminar", Alert.AlertType.WARNING);
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
        cargarDatos(); // refrescar tabla
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
