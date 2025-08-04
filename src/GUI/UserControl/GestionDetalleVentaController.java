package GUI.UserControl;

import DataAccessComponent.AdministrarDetalleVenta;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

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

    @FXML 
    public void initialize() {
        colIdVenta.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(0)));
        colIdDetalle.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(1)));
        colIdProducto.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(2)));
        colCantidad.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(3)));
        colPrecioUnitario.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(4)));
        colSubtotal.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(5)));

        tablaDetalles.setItems(AdministrarDetalleVenta.obtenerTodos());
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

    @FXML
    void agregarNuevoDetalleVenta(ActionEvent event) throws Exception {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioDetalleVenta.fxml","Añadir Detalle de Venta");
    }

    @FXML
    void editarDetalleVenta(ActionEvent event) throws Exception {
        ObservableList<String> seleccionada = tablaDetalles.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            System.out.println("Debe seleccionar un detalle de venta para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorDetalleVenta.fxml"));
            Parent root = loader.load();
            ModificadorDetalleVentaController controller = loader.getController();
            controller.recibirDatos(seleccionada.get(0), seleccionada.get(1), seleccionada.get(2), seleccionada.get(3), seleccionada.get(4), seleccionada.get(5));
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificar Detalle de Venta");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void eliminarDetalleVenta(ActionEvent event) {
        ObservableList<String> seleccionada = tablaDetalles.getSelectionModel().getSelectedItem();
        if (seleccionada == null) {
            mostrarAlerta("Debe seleccionar un detalle de venta para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        int ventaId = Integer.parseInt(seleccionada.get(0));
        int detalleId = Integer.parseInt(seleccionada.get(1));
        int productoId = Integer.parseInt(seleccionada.get(2));
        AdministrarDetalleVenta.eliminar(ventaId, detalleId, productoId);
        tablaDetalles.setItems(AdministrarDetalleVenta.obtenerTodos());
        mostrarAlerta("Detalle de venta eliminado correctamente.", Alert.AlertType.INFORMATION);
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Menu Principal");
        
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
