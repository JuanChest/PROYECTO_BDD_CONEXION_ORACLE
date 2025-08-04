package GUI.UserControl;

import DataAccessComponent.AdministrarProducto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

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
    private TableColumn<ObservableList<String>, String> colIdProducto;

    @FXML
    private TableColumn<ObservableList<String>, String> colProveedorId;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableColumn<ObservableList<String>, String> colPrecio;

    @FXML
    private TableView<ObservableList<String>> tablaProductos;

    @FXML
    public void initialize() {
        colIdProducto.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        colProveedorId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        colPrecio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));

        tablaProductos.setItems(AdministrarProducto.obtenerTodos());
        ajustarInterfazPorConexion();
    }

    private void ajustarInterfazPorConexion() {
        if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
            btnAgregar.setVisible(false);
            btnEditar.setVisible(false);
            btnEliminar.setVisible(false);
        } else {
            btnAgregar.setVisible(true);
            btnEditar.setVisible(true);
            btnEliminar.setVisible(true);
        }
    }

    @FXML
    void agregarNuevoProducto(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioProductos.fxml", "Agregar Nuevo Producto");
    }

    @FXML
    void editarProducto(ActionEvent event) {
        ObservableList<String> seleccion = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccion == null) {
            System.out.println("Por favor, seleccione un producto para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorProducto.fxml"));
            Parent root = loader.load();

            ModificadorProductoController controladorEditar = loader.getController();
            controladorEditar.recibirDatos(
                    seleccion.get(0), // PRODUCTO_ID
                    seleccion.get(1), // PROVEEDOR_ID
                    seleccion.get(2), // NOMBRE
                    seleccion.get(3)  // PRECIO
            );

            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificación de Producto");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        ObservableList<String> seleccion = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccion == null) {
            System.out.println("Por favor, seleccione un producto para eliminar.");
            return;
        }

        int productoId = Integer.parseInt(seleccion.get(0));
        AdministrarProducto.eliminar(productoId);
        tablaProductos.setItems(AdministrarProducto.obtenerTodos());
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
    }
}
