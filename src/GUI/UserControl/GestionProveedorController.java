package GUI.UserControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessComponent.AdministrarProveedor;
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

public class GestionProveedorController {

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableColumn<ObservableList<String>, String> colEmail;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableColumn<ObservableList<String>, String> colProveedorId;

    @FXML
    private TableColumn<ObservableList<String>, String> colTelefono;

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
    private TableView<ObservableList<String>> tablaProveedor;

    @FXML
    private TextField telefonoField;

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
        // Cargar los proveedores en la tabla
        colProveedorId.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        colEmail.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
        tablaProveedor.setItems(AdministrarProveedor.obtenerTodos(provincia));
        ajustarInterfazPorConexion();

        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.MASTER) {
            cargarSiguienteID();
        }
        proveedorIdField.setEditable(false);
        tablaProveedor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                proveedorIdField.setText(newValue.get(0));
                nombreField.setText(newValue.get(1));
                emailField.setText(newValue.get(2));
                telefonoField.setText(newValue.get(3));
            }
            else {
                proveedorIdField.clear();
                nombreField.clear();
                emailField.clear();
                telefonoField.clear();


            }
        });
        tablaProveedor.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ObservableList<String> selectedRow = tablaProveedor.getSelectionModel().getSelectedItem();
                if (selectedRow != null) {
                    tablaProveedor.getSelectionModel().clearSelection();
                    cargarSiguienteID();
                    nombreField.clear();
                    emailField.clear();
                    telefonoField.clear();
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
            proveedorIdField.setVisible(false);
            nombreField.setVisible(false);
            emailField.setVisible(false);
            telefonoField.setVisible(false);
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
            String sql = "SELECT MAX(PROVEEDOR_ID) AS MAX_ID FROM PROVEEDOR";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    proveedorIdField.setText(String.valueOf(maxId + 1));
                } else {
                    proveedorIdField.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            proveedorIdField.setText("1");
        }
    }
    @FXML
    void editarProveedor(ActionEvent event) {
        if (nombreField.getText().isEmpty() || emailField.getText().isEmpty() || telefonoField.getText().isEmpty()) {
            System.out.println("Por favor, complete todos los campos.");
            return;
        }
        try {
            int proveedorId = Integer.parseInt(proveedorIdField.getText());
            String nombre = nombreField.getText();
            String email = emailField.getText();
            String telefono = telefonoField.getText();

            AdministrarProveedor.actualizar(proveedorId, nombre, email, telefono);
            tablaProveedor.setItems(AdministrarProveedor.obtenerTodos(ContextoModulo.getProvinciaActual()));
            cargarSiguienteID();
            System.out.println("Proveedor actualizado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID de proveedor inválido.");
        }

    }

    @FXML
    void eliminarProveedor(ActionEvent event) {
        ObservableList<String> selectedRow = tablaProveedor.getSelectionModel().getSelectedItem();
        if (selectedRow == null) {
            System.out.println("Por favor, seleccione un proveedor para eliminar.");
            return;
        }
        int proveedorId = Integer.parseInt(selectedRow.get(0));
        AdministrarProveedor.eliminar(proveedorId);
        tablaProveedor.setItems(AdministrarProveedor.obtenerTodos(ContextoModulo.getProvinciaActual()));
        cargarSiguienteID();
        System.out.println("Proveedor eliminado correctamente.");
    }

    @FXML
    void guardarProveedor(ActionEvent event) {
        String ProveedorId = proveedorIdField.getText();
        String nombre = nombreField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        if (nombre.isEmpty() || email.isEmpty() || telefono.isEmpty()) {
            System.out.println("Por favor, complete todos los campos.");
            return;
        }
        try {
            int id = Integer.parseInt(ProveedorId);
            AdministrarProveedor.insertar(id, nombre, email, telefono);
            tablaProveedor.setItems(AdministrarProveedor.obtenerTodos(ContextoModulo.getProvinciaActual()));
            cargarSiguienteID();
            System.out.println("Proveedor guardado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("ID de proveedor inválido.");
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

