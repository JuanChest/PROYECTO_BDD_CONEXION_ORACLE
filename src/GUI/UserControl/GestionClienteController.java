package GUI.UserControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionClienteController {

    @FXML
    private TextField apellidoField;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField cedulaField;

    @FXML
    private TextField clienteIdField;

    @FXML
    private TableColumn<?, ?> colApellido;

    @FXML
    private TableColumn<?, ?> colCedula;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colIdCliente;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colProvinciaId;

    @FXML
    private TableColumn<?, ?> colTelefono;

    @FXML
    private TextField emailField;

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
    private TableView<?> tablaClientes;

    @FXML
    private TextField telefonoField;

    @FXML
    void editarCliente(ActionEvent event) {

    }

    @FXML
    void eliminarCliente(ActionEvent event) {

    }

    @FXML
    void guardarCliente(ActionEvent event) {

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

// import java.io.IOException;

// import DataAccessComponent.AdministrarCliente;
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

// public class GestionClientesController {

//     @FXML
//     private Button btnAgregar;

//     @FXML
//     private Button btnEditar;

//     @FXML
//     private Button btnEliminar;

//     @FXML
//     private Button btnEliminar1;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colApellido;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colCorreo;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIDCliente;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colNombre;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colTelefono;

//     @FXML
//     private TableView<ObservableList<String>> tablaClientes;

//     @FXML
//     public void initialize() {
//         colIDCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
//         colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
//         colApellido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
//         colCorreo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
//         colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(4)));

//         tablaClientes.setItems(AdministrarCliente.obtenerTodos());
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
//     void agregarNuevoCliente(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioClientes.fxml", "Agregar Nuevo Cliente");
//     }

//     @FXML
//     void editarCliente(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione un cliente para editar.");
//             return;
//         }

//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorCliente.fxml"));
//             Parent root = loader.load();

//             // Obtener controlador del modificador
//             ModificadorClienteController controladorEditar = loader.getController();

//             // Enviar datos a la ventana de modificación
//             controladorEditar.recibirDatos(
//                     seleccion.get(0), // id
//                     seleccion.get(1), // nombre
//                     seleccion.get(2), // apellido
//                     seleccion.get(3), // correo
//                     seleccion.get(4)  // teléfono
//             );

//             // Cambiar de escena
//             Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//             stage.setScene(new Scene(root));
//             stage.setTitle("Modificación de Cliente");
//             stage.show();

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }


//     @FXML
//     void eliminarCliente(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione un cliente para eliminar.");
//             return;
//         }

//         int clienteId = Integer.parseInt(seleccion.get(0));
//         AdministrarCliente.eliminar(clienteId);

//         // Refrescar la tabla asignando directamente los datos
//         tablaClientes.setItems(AdministrarCliente.obtenerTodos());
//     }



//     @FXML
//     void regresar(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
//     }

// }
