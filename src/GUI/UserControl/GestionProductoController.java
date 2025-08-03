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
import java.sql.SQLException;
import java.sql.Statement;

public class GestionProductoController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdCategoria;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdProducto;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdProveedor;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableColumn<ObservableList<String>, String> colPrecio;

    @FXML
    private TableView<ObservableList<String>> tablaProductos;

    public static ObservableList<String> productoSeleccionado;
    @FXML
    public void initialize() {
        cargarDatos();
    }
    private void cargarDatos() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try(Connection conn = ConexionOracleMaster.getConnection()){
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT PRODUCTO_ID, PROVEEDOR_ID, CATEGORIA_ID, NOMBRE, PRECIO FROM PRODUCTO ORDER BY PRODUCTO_ID");

            while(rs.next()){
                ObservableList<String> row = FXCollections.observableArrayList();
                row.add(rs.getString("PRODUCTO_ID"));
                row.add(rs.getString("PROVEEDOR_ID"));
                row.add(rs.getString("CATEGORIA_ID"));
                row.add(rs.getString("NOMBRE"));
                row.add(rs.getString("PRECIO"));
                data.add(row);
            }

            colIdProducto.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(0)));
            colIdProveedor.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(1)));
            colIdCategoria.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(2)));
            colNombre.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(3)));
            colPrecio.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(4)));

            tablaProductos.setItems(data);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @FXML
    void agregarNuevoProducto() throws Exception {
        abrirVentana("GUI/Interfaz/FormularioProductos.fxml", "Registrar Producto");
    }

    @FXML
    void editarProducto() throws Exception {
        productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            abrirVentana("GUI/Interfaz/ModificadorProducto.fxml", "Modificar Producto");
        } else {
            mostrarAlerta("Seleccione un producto para editar", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarProducto() {
        ObservableList<String> seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            try(Connection conn = ConexionOracleMaster.getConnection()) {
                Statement stmt = conn.createStatement();
                stmt.executeUpdate("DELETE FROM PRODUCTO WHERE PRODUCTO_ID = " + seleccionado.get(0));
                cargarDatos();
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Seleccione un producto para eliminar", Alert.AlertType.WARNING);
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
        cargarDatos(); // refrescar tabla después de cerrar
    }
    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
