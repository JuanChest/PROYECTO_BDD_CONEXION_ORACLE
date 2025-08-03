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

public class GestionProductoInventarioController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdInventario;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdProducto;

    @FXML
    private TableView<ObservableList<String>> tablaProductoInventario;

    public static ObservableList<String> productoInventarioSeleccionado;

    @FXML
    public void initialize() {
        cargarDatos();
    }

    private void cargarDatos() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PRODUCTO_ID, INVENTARIO_ID FROM PRODUCTO_INVENTARIO");

            while (rs.next()) {
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("PRODUCTO_ID"));
                row.add(rs.getString("INVENTARIO_ID"));
                data.add(row);
            }

            colIdProducto.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(0)));
            colIdInventario.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(1)));

            tablaProductoInventario.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void agregarNuevoInventario() throws Exception {
        abrirVentana("GUI/Interfaz/FormularioProductoInventario.fxml", "Registrar Producto-Inventario");
    }

    @FXML
    void editarInventario() throws Exception {
        productoInventarioSeleccionado = tablaProductoInventario.getSelectionModel().getSelectedItem();
        if (productoInventarioSeleccionado != null) {
            abrirVentana("GUI/Interfaz/ModificadorProductoInventario.fxml", "Modificar Producto-Inventario");
        } else {
            mostrarAlerta("Seleccione un registro para editar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarInventario() {
        ObservableList<String> seleccionado = tablaProductoInventario.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try (Connection conn = ConexionOracleMaster.getConnection()) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM PRODUCTO_INVENTARIO WHERE PRODUCTO_ID = "
                        + seleccionado.get(0) + " AND INVENTARIO_ID = " + seleccionado.get(1));
                cargarDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Seleccione un registro para eliminar", Alert.AlertType.WARNING);
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
        cargarDatos();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
