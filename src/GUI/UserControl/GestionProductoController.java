package GUI.UserControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessComponent.AdministrarProducto;
import DataAccessComponent.ConexionOracleMaster;
import Util.ContextoConexion;
import Util.ContextoModulo;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionProductoController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableColumn<ObservableList<String>, String> colPrecio;

    @FXML
    private TableColumn<ObservableList<String>, String> colProductoId;

    @FXML
    private TableColumn<ObservableList<String>, String> colProveedorId;

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
    private TextField precioField;

    @FXML
    private TextField productoIdField;

    @FXML
    private TextField proveedorIdField;

    @FXML
    private TableView<ObservableList<String>> tablaProducto;

    @FXML
    public void initialize() {
        String provincia = ContextoModulo.getProvinciaActual();
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
        colProductoId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        colProveedorId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        colPrecio.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
        tablaProducto.setItems(AdministrarProducto.obtenerTodos(provincia));
        ajustarInterfazPorConexion();

        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.MASTER) {
            cargarSiguienteID();
        }
        productoIdField.setEditable(false);
        tablaProducto.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                productoIdField.setText(newValue.get(0));
                proveedorIdField.setText(newValue.get(1));
                nombreField.setText(newValue.get(2));
                precioField.setText(newValue.get(3));
            }
            else {
                productoIdField.clear();
                proveedorIdField.clear();
                nombreField.clear();
                precioField.clear();
            }
        });
        tablaProducto.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ObservableList<String> selectedItem = tablaProducto.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    tablaProducto.getSelectionModel().clearSelection();
                    cargarSiguienteID();
                    proveedorIdField.clear();
                    nombreField.clear();
                    precioField.clear();
                }
            }
        });
    }
    
    private void ajustarInterfazPorConexion() {
        System.out.println("Tipo de conexión actual: " + Util.ContextoConexion.getTipoConexion());

        if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
            System.out.println("Modo REMOTO: Ocultando botones");
            btnGuardar.setVisible(false);
            btnActualizar.setVisible(false);
            btnEliminar.setVisible(false);
            // Deshabilitar campos de entrada
            productoIdField.setVisible(false);
            proveedorIdField.setVisible(false);
            nombreField.setVisible(false);
            precioField.setVisible(false);
        } else {
            System.out.println("Modo MASTER: Mostrando botones");
            btnGuardar.setVisible(true);
            btnActualizar.setVisible(true);
            btnEliminar.setVisible(true);
        }
    }

    private void cargarSiguienteID() {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "SELECT MAX(PRODUCTO_ID) AS MAX_ID FROM PRODUCTO";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    productoIdField.setText(String.valueOf(maxId + 1));
                } else {
                    productoIdField.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            productoIdField.setText("1");
        }
    }

    @FXML
    void editarProducto(ActionEvent event) {
        if (productoIdField.getText().isEmpty() || proveedorIdField.getText().isEmpty() ||
            nombreField.getText().isEmpty() || precioField.getText().isEmpty()) {
            System.out.println("Todos los campos deben estar llenos.");
            return;
        }
        try {
            int id = Integer.parseInt(productoIdField.getText());
            int proveedorId = Integer.parseInt(proveedorIdField.getText());
            String nombre = nombreField.getText();
            double precio = Double.parseDouble(precioField.getText());

            AdministrarProducto.actualizar(id, proveedorId, nombre, precio);
            System.out.println("Producto actualizado correctamente.");
            tablaProducto.setItems(AdministrarProducto.obtenerTodos(ContextoModulo.getProvinciaActual()));
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir los campos a números: " + e.getMessage());
        }
    }

    @FXML
    void eliminarProducto(ActionEvent event) {
        ObservableList<ObservableList<String>> selectedItems = tablaProducto.getSelectionModel().getSelectedItems();
        if (selectedItems.isEmpty()) {
            System.out.println("No se ha seleccionado ningún producto para eliminar.");
            return;
        }
        int productoId = Integer.parseInt(selectedItems.get(0).get(0));
        AdministrarProducto.eliminar(productoId);  
        System.out.println("Producto eliminado correctamente.");
        tablaProducto.setItems(AdministrarProducto.obtenerTodos(ContextoModulo.getProvinciaActual()));
    }

    @FXML
    void guardarProducto(ActionEvent event) {
        String productoID = productoIdField.getText();
        String proveedorID = proveedorIdField.getText();
        String nombre = nombreField.getText();
        String precioStr = precioField.getText();
        if (productoID.isEmpty() || proveedorID.isEmpty() || nombre.isEmpty() || precioStr.isEmpty()) {
            System.out.println("Todos los campos deben estar llenos.");
            return;
        }
        try {
            int id = Integer.parseInt(productoID);
            int proveedorId = Integer.parseInt(proveedorID);
            double precio = Double.parseDouble(precioStr);

            AdministrarProducto.insertar(id, proveedorId, nombre, precio);
            System.out.println("Producto guardado correctamente.");
            tablaProducto.setItems(AdministrarProducto.obtenerTodos(ContextoModulo.getProvinciaActual()));
            cargarSiguienteID();
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir los campos a números: " + e.getMessage());
        }
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
