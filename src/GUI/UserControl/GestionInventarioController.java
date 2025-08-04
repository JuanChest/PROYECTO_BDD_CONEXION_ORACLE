package GUI.UserControl;

import DataAccessComponent.AdministrarInventario;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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
    private TableColumn<ObservableList<String>, String> colIdProducto;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdTienda;

    @FXML
    private TableView<ObservableList<String>> tablaInventario;

    public static ObservableList<String> inventarioSeleccionado;

    @FXML
    public void initialize() {
        colIdInventario.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(0)));
        colIdTienda.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(1)));
        colIdProducto.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(2)));
        colCantidad.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(3)));

        tablaInventario.setItems(AdministrarInventario.obtenerTodos());
        ajustarInterfazPorConexion();
        
    }

    private void ajustarInterfazPorConexion() {
        System.out.println("Tipo de conexión actual: " + Util.ContextoConexion.getTipoConexion());

        if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
            System.out.println("Modo REMOTO: Ocultando botones");
            //btnAgregar.setVisible(false);
            //btnEditar.setVisible(false);
            //btnEliminar.setVisible(false);
        } else {
            System.out.println("Modo MASTER: Mostrando botones");
            btnAgregar.setVisible(true);
            btnEditar.setVisible(true);
            btnEliminar.setVisible(true);
        }
    }

    @FXML
    void agregarNuevoInventario(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioInventario.fxml", "Formulario de Inventario");
    }

    @FXML
    void editarInventario(ActionEvent event) throws Exception {
        ObservableList<String> filaSeleccionada = tablaInventario.getSelectionModel().getSelectedItem();
        if (filaSeleccionada == null) {
            System.out.println("Por favor, seleccione un inventario para editar.");
            return;
        }
            
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorInventario.fxml"));
            Parent root = loader.load();
            ModificadorInventarioController controller = loader.getController();
            controller.recibirDatos(filaSeleccionada.get(0), filaSeleccionada.get(1), filaSeleccionada.get(2), filaSeleccionada.get(3));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new javafx.scene.Scene(root));
            stage.setTitle("Modificar Inventario");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la ventana de modificación: " + e.getMessage());
        }
    }

    @FXML
    void eliminarInventario(ActionEvent event) {
        ObservableList<String> filaSeleccionada = tablaInventario.getSelectionModel().getSelectedItem();
        if (filaSeleccionada == null) {
            System.out.println("Por favor, seleccione un inventario para eliminar.");
            return;
        }

        int inventarioId = Integer.parseInt(filaSeleccionada.get(0));
        try {
            AdministrarInventario.eliminar(inventarioId);
            System.out.println("Inventario eliminado correctamente.");
            tablaInventario.setItems(AdministrarInventario.obtenerTodos());
        } catch (Exception e) {
            System.out.println("Error al eliminar el inventario: " + e.getMessage());
        }

    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
    }

    // private void mostrarAlerta(String mensaje, Alert.AlertType type) {
    //     Alert alert = new Alert(type);
    //     alert.setContentText(mensaje);
    //     alert.showAndWait();
    // }

}
