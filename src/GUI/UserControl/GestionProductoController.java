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
            ajustarInterfazPorConexion();
        } catch (SQLException e){
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
    void agregarNuevoProducto(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioProductos.fxml", "Agregar Nuevo Producto");
    }

    @FXML
    void editarProducto(ActionEvent event) throws Exception {
        productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/ModificadorProducto.fxml", "Editar Producto");
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
