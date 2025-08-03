package GUI.UserControl;

import DataAccessComponent.AdministrarProveedor;
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

public class GestionProveedorController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colCorreo;

    @FXML
    private TableColumn<ObservableList<String>, String> colIDProveedor;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableColumn<ObservableList<String>, String> colTelefono;

    @FXML
    private TableView<ObservableList<String>> tablaClientes;

    @FXML
    public void initialize() {
        colIDProveedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        colCorreo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));

        tablaClientes.setItems(AdministrarProveedor.obtenerTodos());
    }

    @FXML
    void agregarNuevoProveedor(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioProveedor.fxml", "Agregar Nuevo Proveedor");
    }

    @FXML
    void editarProveedor(ActionEvent event) {
        ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            System.out.println("Por favor, seleccione un proveedor para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorProveedor.fxml"));
            Parent root = loader.load();

            ModificadorProveedorController controlador = loader.getController();

            controlador.recibirDatos(seleccion.get(0), seleccion.get(1), seleccion.get(2), seleccion.get(3));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Editar Proveedor");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void eliminarProveedor(ActionEvent event) {
        ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            System.out.println("Por favor, seleccione un proveedor para eliminar.");
            return;
        }

        int ProveedorId = Integer.parseInt(seleccion.get(0));
        AdministrarProveedor.eliminar(ProveedorId);

        // Refrescar la tabla asignando directamente los datos
        tablaClientes.setItems(AdministrarProveedor.obtenerTodos());
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
    }

}

