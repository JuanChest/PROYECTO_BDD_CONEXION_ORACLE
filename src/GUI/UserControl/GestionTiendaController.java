package GUI.UserControl;

import Util.ContextoConexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionTiendaController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<?, ?> colDireccion;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colProvinciaId;

    @FXML
    private TableColumn<?, ?> colTiendaId;

    @FXML
    private TextField direccionField;

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
    private TextField nombreField;

    @FXML
    private TextField provinciaIdField;

    @FXML
    private TableView<?> tablaTienda;

    @FXML
    private TextField tiendaIdField;

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
    void editarTienda(ActionEvent event) {

    }

    @FXML
    void eliminarTienda(ActionEvent event) {

    }

    @FXML
    void guardarTienda(ActionEvent event) {

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

// import DataAccessComponent.AdministrarTienda;
// import javafx.beans.property.SimpleStringProperty;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.stage.Stage;

// public class GestionTiendaController {

//     @FXML
//     private Button btnAgregar;

//     @FXML
//     private Button btnEditar;

//     @FXML
//     private Button btnEliminar;

//     @FXML
//     private Button btnEliminar1;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colDireccion;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIdRegion;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIdTienda;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colNombre;

//     @FXML
//     private TableView<ObservableList<String>> tablaClientes;

//     @FXML
//     public void initialize() {
//         colIdTienda.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
//         colIdRegion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
//         colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
//         colDireccion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));

//         tablaClientes.setItems(AdministrarTienda.obtenerTodos());
//         ajustarInterfazPorConexion();
//     }

//     private void ajustarInterfazPorConexion() {
//         System.out.println("Tipo de conexión actual: " + Util.ContextoConexion.getTipoConexion());

//         if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
//             System.out.println("Modo REMOTO: Ocultando botones");
//             //btnAgregar.setVisible(false);
//             //btnEditar.setVisible(false);
//             //btnEliminar.setVisible(false);
//         } else {
//             System.out.println("Modo MASTER: Mostrando botones");
//             btnAgregar.setVisible(true);
//             btnEditar.setVisible(true);
//             btnEliminar.setVisible(true);
//         }
//     }

//     @FXML
//     void agregarNuevaTienda(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioTienda.fxml", "Formulario de Tienda");
//     }

//     @FXML
//     void editarTienda(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione una tienda para editar.");
//             return;
//         }

//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorTienda.fxml"));
//             Parent root = loader.load();

//             ModificadorTiendaController controlador = loader.getController();

//             controlador.recibirDatos(seleccion.get(0), seleccion.get(1), seleccion.get(2), seleccion.get(3));
//             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//             stage.setScene(new Scene(root));
//             stage.setTitle("Modificar Tienda");
//             stage.show();
//         } catch (Exception e) {
//             System.out.println("Error al cargar la ventana de modificación de tienda.");
//         }
//     }

//     @FXML
//     void eliminarTienda(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione una tienda para eliminar.");
//             return;
//         }

//         int tiendaId = Integer.parseInt(seleccion.get(0));
//         AdministrarTienda.eliminar(tiendaId);

//         // Refrescar la tabla asignando directamente los datos
//         tablaClientes.setItems(AdministrarTienda.obtenerTodos());
//     }

//     @FXML
//     void regresar(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
//     }

// }
