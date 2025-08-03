package GUI.UserControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipalController {

    @FXML
    private Button btnAuditoria;

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnProveedores;

    @FXML
    private Button btnTienda;

    @FXML
    private Button btnVentas;

    @FXML
    void entrarAuditoria(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionClientes.fxml", "Gestión de Clientes");
    }

    @FXML
    void entrarClientes(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionClientes.fxml", "Gestión de Clientes");
    }

    @FXML
    void entrarGestionInventario(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionInventario.fxml", "Gestión de Inventario");
    }

    @FXML
    void entrarGestionProductos(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionProducto.fxml", "Gestión de Productos");
    }

    @FXML
    void entrarGestionProveedores(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionProveedor.fxml", "Gestión de Proveedores");
    }

    @FXML
    void entrarGestionTienda(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionTienda.fxml", "Gestión de Tienda");
    }

    @FXML
    void entrarGestionVentas(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionVenta.fxml", "Gestión de Ventas");
    }

    @FXML
    void entrarAuditar(ActionEvent actionEvent) {
    }

}
