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
        System.out.println("Provincia actual en initialize(): " + provincia);
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
        // Calcular subtotal automáticamente cuando cambie cantidad o precio unitario
        cantidadField.textProperty().addListener((obs, oldVal, newVal) -> calcularSubtotal());
        precioUnitarioField.textProperty().addListener((obs, oldVal, newVal) -> calcularSubtotal());
        tablaDetalleVenta.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            ventaIdField.setText(newSelection.get(0));
            detalleIdField.setText(newSelection.get(1));
            productoIdField.setText(newSelection.get(2));
            cantidadField.setText(newSelection.get(3));
            precioUnitarioField.setText(newSelection.get(4));
            subtotalField.setText(newSelection.get(5));

            ventaIdField.setEditable(false);
            detalleIdField.setEditable(false);
            productoIdField.setEditable(false);
            subtotalField.setEditable(false);
            cantidadField.setEditable(true);
            precioUnitarioField.setEditable(true);
        } else {
            // Limpiar los campos
            ventaIdField.clear();
            detalleIdField.clear();
            productoIdField.clear();
            cantidadField.clear();
            precioUnitarioField.clear();
            subtotalField.clear();

            // Opcional: volver editables si quieres permitir inserción nueva
            ventaIdField.setEditable(true);
            detalleIdField.setEditable(true);
            productoIdField.setEditable(true);
            cantidadField.setEditable(true);
            precioUnitarioField.setEditable(true);
            subtotalField.setEditable(false); // este lo calculas automáticamente
        }
    });

        tablaDetalleVenta.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2) {
            // Doble clic
            ObservableList<String> selectedItem = tablaDetalleVenta.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                tablaDetalleVenta.getSelectionModel().clearSelection();

                // Limpiar los campos
                ventaIdField.clear();
                detalleIdField.clear();
                productoIdField.clear();
                cantidadField.clear();
                precioUnitarioField.clear();
                subtotalField.clear();

                // Hacer los campos editables nuevamente si quieres permitir inserciones
                ventaIdField.setEditable(true);
                detalleIdField.setEditable(true);
                productoIdField.setEditable(true);
                cantidadField.setEditable(true);
                precioUnitarioField.setEditable(true);
                subtotalField.setEditable(false);
            }
        }
    });


    }

    private void calcularSubtotal() {
        try {
            String cantidadText = cantidadField.getText().replace(",", ".");
            String precioText = precioUnitarioField.getText().replace(",", ".");

            int cantidad = Integer.parseInt(cantidadText);
            double precioUnitario = Double.parseDouble(precioText);

            double subtotal = cantidad * precioUnitario;
            subtotalField.setText(String.format("%.2f", subtotal));
        } catch (NumberFormatException e) {
            // Si hay campos vacíos o con texto inválido, simplemente no hacer nada
            subtotalField.setText("");
        }
    }


    @FXML
    void editarDetalleVenta(ActionEvent event) {
        // Validar campos vacíos antes de convertir
        if (ventaIdField.getText().isEmpty() || detalleIdField.getText().isEmpty() || productoIdField.getText().isEmpty() ||
            cantidadField.getText().isEmpty() || precioUnitarioField.getText().isEmpty() || subtotalField.getText().isEmpty()) {
            System.out.println("Error: Todos los campos deben estar llenos.");
            return;
        }
        // // Imprimir los valores para depuración
        // System.out.println("ventaIdField: " + ventaIdField.getText());
        // System.out.println("detalleIdField: " + detalleIdField.getText());
        // System.out.println("productoIdField: " + productoIdField.getText());
        // System.out.println("cantidadField: " + cantidadField.getText());
        // System.out.println("precioUnitarioField: " + precioUnitarioField.getText());
        // System.out.println("subtotalField: " + subtotalField.getText());

        try {
            int ventaId = Integer.parseInt(ventaIdField.getText());
            int detalleId = Integer.parseInt(detalleIdField.getText());
            int productoId = Integer.parseInt(productoIdField.getText());
            int cantidad = Integer.parseInt(cantidadField.getText());
            // Si usas coma decimal, reemplaza coma por punto
            String precioStr = precioUnitarioField.getText().replace(',', '.');
            String subtotalStr = subtotalField.getText().replace(',', '.');

            double precioUnitario = Double.parseDouble(precioStr);
            double subtotal = Double.parseDouble(subtotalStr);

            AdministrarDetalleVenta.actualizar(ventaId, detalleId, productoId, cantidad, precioUnitario, subtotal);

            tablaDetalleVenta.setItems(AdministrarDetalleVenta.obtenerTodos(ContextoModulo.getProvinciaActual()));
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar datos válidos para actualizar.");
        }
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

        int idVenta = Integer.parseInt(ventaId);
        int idDetalle = Integer.parseInt(detalleId);
        int idProducto = Integer.parseInt(productoId);

        if (AdministrarDetalleVenta.existeDetalle(idVenta, idDetalle, idProducto)) {
            System.out.println("Error: Ya existe un detalle de venta con esa clave primaria.");
            return;
        }

        AdministrarDetalleVenta.insertar(
            idVenta,
            idDetalle,
            idProducto,
            Integer.parseInt(cantidad),
            Double.parseDouble(precioUnitario),
            Double.parseDouble(subtotal)
        );

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