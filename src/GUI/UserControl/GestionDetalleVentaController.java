package GUI.UserControl;

import DataAccessComponent.AdministrarDetalleVenta;
import Util.ContextoConexion;
import Util.ContextoModulo;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private TableColumn<ObservableList<String>, String> colCantidad;

    @FXML
    private TableColumn<ObservableList<String>, String> colDetalleId;

    @FXML
    private TableColumn<ObservableList<String>, String> colPrecioUnitario;

    @FXML
    private TableColumn<ObservableList<String>, String> colProductoId;

    @FXML
    private TableColumn<ObservableList<String>, String> colSubtotal;

    @FXML
    private TableColumn<ObservableList<String>, String> colVentaId;

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
    private TableView<ObservableList<String>> tablaDetalleVenta;

    @FXML
    private TextField ventaIdField;

    @FXML
    public void initialize() {
        String provincia = ContextoModulo.getProvinciaActual();
        System.out.println("Provincia actual: " + provincia);
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
        
        colVentaId.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(0)));
        colDetalleId.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(1)));
        colProductoId.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(2)));
        colCantidad.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(3)));
        colPrecioUnitario.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(4)));
        colSubtotal.setCellValueFactory(data -> new ReadOnlyObjectWrapper<>(data.getValue().get(5)));
        tablaDetalleVenta.setItems(AdministrarDetalleVenta.obtenerTodos(provincia));
    }

    @FXML
    void editarDetalleVenta(ActionEvent event) {
        ObservableList<ObservableList<String>> lista = tablaDetalleVenta.getSelectionModel().getSelectedItems();
        if (lista.isEmpty()) {
            System.out.println("Debe seleccionar un detalle de venta para editar.");
            return;
        }
        ObservableList<String> seleccionada = lista.get(0);
        ventaIdField.setText(seleccionada.get(0));
        detalleIdField.setText(seleccionada.get(1));
        productoIdField.setText(seleccionada.get(2));
        cantidadField.setText(seleccionada.get(3));
        precioUnitarioField.setText(seleccionada.get(4));
        subtotalField.setText(seleccionada.get(5));
    }

    @FXML
    void eliminarDetalleVenta(ActionEvent event) {
        ObservableList<ObservableList<String>> lista = tablaDetalleVenta.getSelectionModel().getSelectedItems();
        if (lista.isEmpty()) {
            System.out.println("Debe seleccionar un detalle de venta para eliminar.");
            return;
        }
        ObservableList<String> seleccionada = lista.get(0);
        int ventaId = Integer.parseInt(seleccionada.get(0));
        int detalleId = Integer.parseInt(seleccionada.get(1));
        int productoId = Integer.parseInt(seleccionada.get(2));
        AdministrarDetalleVenta.eliminar(ventaId, detalleId, productoId);
        tablaDetalleVenta.setItems(AdministrarDetalleVenta.obtenerTodos(ContextoModulo.getProvinciaActual()));
    }

    @FXML
    void guardarDetalleVenta(ActionEvent event) {
        String ventaId = ventaIdField.getText();
        String detalleId = detalleIdField.getText();
        String productoId = productoIdField.getText();
        String cantidad = cantidadField.getText();
        String precioUnitario = precioUnitarioField.getText();
        String subtotal = subtotalField.getText();

        if (ventaId.isEmpty() || detalleId.isEmpty() || productoId.isEmpty() || cantidad.isEmpty() || precioUnitario.isEmpty() || subtotal.isEmpty()) {
            System.out.println("Todos los campos deben ser completados.");
            return;
        }

        AdministrarDetalleVenta.insertar(Integer.parseInt(ventaId), Integer.parseInt(detalleId), Integer.parseInt(productoId), Integer.parseInt(cantidad), Double.parseDouble(precioUnitario), Double.parseDouble(subtotal));
        tablaDetalleVenta.setItems(AdministrarDetalleVenta.obtenerTodos(ContextoModulo.getProvinciaActual()));
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
            ContextoModulo.setProvinciaActual("Manabi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Cotopaxi");
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
            ContextoModulo.setProvinciaActual("Guayas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Pichincha");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Esmeraldas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Tungurahua");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Manabi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Cotopaxi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Guayas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Pichincha");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Esmeraldas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Tungurahua");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Manabi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Cotopaxi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Guayas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Pichincha");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Esmeraldas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Tungurahua");
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
            ContextoModulo.setProvinciaActual("Manabi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Cotopaxi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Guayas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Pichincha");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Esmeraldas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Tungurahua");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Manabi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Cotopaxi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Guayas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Pichincha");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Esmeraldas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Tungurahua");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
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

//         tablaDetalles.setItems(AdministrarDetalleVenta.obtenerTodos(ContextoModulo.getProvinciaActual()));
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
//         tablaDetalles.setItems(AdministrarDetalleVenta.obtenerTodos(ContextoModulo.getProvinciaActual()));
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
