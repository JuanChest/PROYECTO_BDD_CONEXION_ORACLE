package GUI.UserControl;

import Util.ContextoConexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionProveedorController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colNombre;

    @FXML
    private TableColumn<?, ?> colProveedorId;

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
    private TextField proveedorIdField;

    @FXML
    private TableView<?> tablaProveedor;

    @FXML
    private TextField telefonoField;

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
    void editarProveedor(ActionEvent event) {

    }

    @FXML
    void eliminarProveedor(ActionEvent event) {

    }

    @FXML
    void guardarProveedor(ActionEvent event) {

    }

    @FXML
    void irAuditoria(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaAuditoria.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaAuditoria.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesGlobal(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irProductos(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProductos.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProductos.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irProveedores(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProveedor.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProveedor.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irProvincia(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProvincia.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProvincia.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }
}

// package GUI.UserControl;

// import DataAccessComponent.AdministrarProveedor;
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

// public class GestionProveedorController {

//     @FXML
//     private Button btnAgregar;

//     @FXML
//     private Button btnEditar;

//     @FXML
//     private Button btnEliminar;

//     @FXML
//     private Button btnEliminar1;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colCorreo;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIDProveedor;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colNombre;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colTelefono;

//     @FXML
//     private TableView<ObservableList<String>> tablaClientes;

//     @FXML
//     public void initialize() {
//         colIDProveedor.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
//         colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
//         colCorreo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
//         colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));

//         tablaClientes.setItems(AdministrarProveedor.obtenerTodos());
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
//     void agregarNuevoProveedor(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioProveedor.fxml", "Agregar Nuevo Proveedor");
//     }

//     @FXML
//     void editarProveedor(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione un proveedor para editar.");
//             return;
//         }

//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorProveedor.fxml"));
//             Parent root = loader.load();

//             ModificadorProveedorController controlador = loader.getController();

//             controlador.recibirDatos(seleccion.get(0), seleccion.get(1), seleccion.get(2), seleccion.get(3));
//             Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//             stage.setScene(new Scene(root));
//             stage.setTitle("Editar Proveedor");
//             stage.show();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }

//     @FXML
//     void eliminarProveedor(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione un proveedor para eliminar.");
//             return;
//         }

//         int ProveedorId = Integer.parseInt(seleccion.get(0));
//         AdministrarProveedor.eliminar(ProveedorId);

//         // Refrescar la tabla asignando directamente los datos
//         tablaClientes.setItems(AdministrarProveedor.obtenerTodos());
//     }

//     @FXML
//     void regresar(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
//     }

// }

