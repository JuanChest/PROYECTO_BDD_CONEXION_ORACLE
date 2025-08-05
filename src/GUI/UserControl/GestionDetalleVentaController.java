package GUI.UserControl;

import Util.ContextoConexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionDetalleVentaController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField cantidadField;

    @FXML
    private TableColumn<?, ?> colCantidad;

    @FXML
    private TableColumn<?, ?> colDetalleId;

    @FXML
    private TableColumn<?, ?> colPrecioUnitario;

    @FXML
    private TableColumn<?, ?> colProductoId;

    @FXML
    private TableColumn<?, ?> colSubtotal;

    @FXML
    private TableColumn<?, ?> colVentaId;

    @FXML
    private TextField detalleIdField;

    @FXML
    private MenuItem menuClientesCotopaxi;

    @FXML
    private MenuItem menuClientesPichincha;

    @FXML
    private MenuItem menuClientesTungurahua;

    @FXML
    private MenuItem menuDetalleVentaCotopaxi;

    @FXML
    private MenuItem menuDetalleVentaPichincha;

    @FXML
    private MenuItem menuDetalleVentaTungurahua;

    @FXML
    private MenuItem menuInventarioCotopaxi;

    @FXML
    private MenuItem menuInventarioPichincha;

    @FXML
    private MenuItem menuInventarioTungurahua;

    @FXML
    private MenuItem menuTiendasCotopaxi;

    @FXML
    private MenuItem menuTiendasPichincha;

    @FXML
    private MenuItem menuTiendasTungurahua;

    @FXML
    private MenuItem menuVentasCotopaxi;

    @FXML
    private MenuItem menuVentasPichincha;

    @FXML
    private MenuItem menuVentasTungurahua;

    @FXML
    private TextField precioUnitarioField;

    @FXML
    private TextField productoIdField;

    @FXML
    private TextField subtotalField;

    @FXML
    private TableView<?> tablaDetalleVenta;

    @FXML
    private TextField ventaIdField;

    @FXML
    public void initialize() {
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            menuClientesPichincha.setText("ClientesGuayas");
            menuClientesCotopaxi.setText("ClientesManabi");
            menuClientesTungurahua.setText("ClientesEsmeraldas");
            menuDetalleVentaPichincha.setText("DetalleVentaGuayas");
            menuDetalleVentaCotopaxi.setText("DetalleVentaManabi");
            menuDetalleVentaTungurahua.setText("DetalleVentaEsmeraldas");
            menuInventarioPichincha.setText("InventarioGuayas");
            menuInventarioCotopaxi.setText("InventarioManabi");
            menuInventarioTungurahua.setText("InventarioEsmeraldas");
            menuTiendasPichincha.setText("TiendasGuayas");
            menuTiendasCotopaxi.setText("TiendasManabi");
            menuTiendasTungurahua.setText("TiendasEsmeraldas");
            menuVentasPichincha.setText("VentasGuayas");
            menuVentasCotopaxi.setText("VentasManabi");
            menuVentasTungurahua.setText("VentasEsmeraldas");
        }
    }

    @FXML
    void editarDetalleVenta(ActionEvent event) {
        
    }

    @FXML
    void eliminarDetalleVenta(ActionEvent event) {

    }

    @FXML
    void guardarDetalleVenta(ActionEvent event) {

    }

    @FXML
    void irAuditoria(ActionEvent event) {

    }

    @FXML
    void irClientesCotopaxi(ActionEvent event) {

    }

    @FXML
    void irClientesGlobal(ActionEvent event) {

    }

    @FXML
    void irClientesPichincha(ActionEvent event) {

    }

    @FXML
    void irClientesTungurahua(ActionEvent event) {

    }

    @FXML
    void irDetalleVentaCotopaxi(ActionEvent event) {

    }

    @FXML
    void irDetalleVentaPichincha(ActionEvent event) {

    }

    @FXML
    void irDetalleVentaTungurahua(ActionEvent event) {

    }

    @FXML
    void irInventarioCotopaxi(ActionEvent event) {

    }

    @FXML
    void irInventarioPichincha(ActionEvent event) {

    }

    @FXML
    void irInventarioTungurahua(ActionEvent event) {

    }

    @FXML
    void irProductos(ActionEvent event) {

    }

    @FXML
    void irProveedores(ActionEvent event) {

    }

    @FXML
    void irProvincia(ActionEvent event) {

    }

    @FXML
    void irTiendasCotopaxi(ActionEvent event) {

    }

    @FXML
    void irTiendasPichincha(ActionEvent event) {

    }

    @FXML
    void irTiendasTungurahua(ActionEvent event) {

    }

    @FXML
    void irVentasCotopaxi(ActionEvent event) {

    }

    @FXML
    void irVentasPichincha(ActionEvent event) {

    }

    @FXML
    void irVentasTungurahua(ActionEvent event) {

    }

}

// package GUI.UserControl;

// import DataAccessComponent.AdministrarDetalleVenta;
// import javafx.beans.property.ReadOnlyObjectWrapper;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Alert;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.stage.Stage;

// public class GestionDetalleVentaController {

//     @FXML
//     private Button btnAgregar;

//     @FXML
//     private Button btnEditar;

//     @FXML
//     private Button btnEliminar;

//     @FXML
//     private Button btnEliminar1;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colCantidad;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIdDetalle;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIdProducto;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIdVenta;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colPrecioUnitario;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colSubtotal;

//     @FXML
//     private TableView<ObservableList<String>> tablaDetalles;

//     public static ObservableList<String> detalleSeleccionado;

//     @FXML 
//     public void initialize() {
//         colIdVenta.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(0)));
//         colIdDetalle.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(1)));
//         colIdProducto.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(2)));
//         colCantidad.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(3)));
//         colPrecioUnitario.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(4)));
//         colSubtotal.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(5)));

//         tablaDetalles.setItems(AdministrarDetalleVenta.obtenerTodos());
//         ajustarInterfazPorConexion();
//     }
    

//     private void ajustarInterfazPorConexion() {
//         System.out.println("Tipo de conexión actual: " + Util.ContextoConexion.getTipoConexion());

//         if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
//             System.out.println("Modo REMOTO: Ocultando botones");
//             btnAgregar.setVisible(false);
//             btnEditar.setVisible(false);
//             btnEliminar.setVisible(false);
//         } else {
//             System.out.println("Modo MASTER: Mostrando botones");
//             btnAgregar.setVisible(true);
//             btnEditar.setVisible(true);
//             btnEliminar.setVisible(true);
//         }
//     }

//     @FXML
//     void agregarNuevoDetalleVenta(ActionEvent event) throws Exception {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioDetalleVenta.fxml","Añadir Detalle de Venta");
//     }

//     @FXML
//     void editarDetalleVenta(ActionEvent event) throws Exception {
//         ObservableList<String> seleccionada = tablaDetalles.getSelectionModel().getSelectedItem();
//         if (seleccionada == null) {
//             System.out.println("Debe seleccionar un detalle de venta para editar.");
//             return;
//         }

//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorDetalleVenta.fxml"));
//             Parent root = loader.load();
//             ModificadorDetalleVentaController controller = loader.getController();
//             controller.recibirDatos(seleccionada.get(0), seleccionada.get(1), seleccionada.get(2), seleccionada.get(3), seleccionada.get(4), seleccionada.get(5));
//             Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//             stage.setScene(new Scene(root));
//             stage.setTitle("Modificar Detalle de Venta");
//             stage.show();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     @FXML
//     void eliminarDetalleVenta(ActionEvent event) {
//         ObservableList<String> seleccionada = tablaDetalles.getSelectionModel().getSelectedItem();
//         if (seleccionada == null) {
//             mostrarAlerta("Debe seleccionar un detalle de venta para eliminar.", Alert.AlertType.WARNING);
//             return;
//         }

//         int ventaId = Integer.parseInt(seleccionada.get(0));
//         int detalleId = Integer.parseInt(seleccionada.get(1));
//         int productoId = Integer.parseInt(seleccionada.get(2));
//         AdministrarDetalleVenta.eliminar(ventaId, detalleId, productoId);
//         tablaDetalles.setItems(AdministrarDetalleVenta.obtenerTodos());
//         mostrarAlerta("Detalle de venta eliminado correctamente.", Alert.AlertType.INFORMATION);
//     }

//     @FXML
//     void regresar(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Menu Principal");
        
//     }

//     private void mostrarAlerta(String mensaje, Alert.AlertType type) {
//         Alert alert = new Alert(type);
//         alert.setContentText(mensaje);
//         alert.showAndWait();
//     }

// }
