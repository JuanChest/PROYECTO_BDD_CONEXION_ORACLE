package GUI.UserControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuPrincipalController {

    @FXML
    private Button btnClientes;

    @FXML
    private Button btnTiendas;

    @FXML
    private Button btnInventario;

    @FXML
    private Button btnVentas;

    @FXML
    private Button btnProductos;

    @FXML
    private Button btnProveedor;

    @FXML
    private Button btnAuditar;

    @FXML
    public void gestionarTiendas(ActionEvent actionEvent) throws Exception {
        //abrirVentana("GUI/Interfaz/GestionTienda.fxml", "Gestionar Tiendas");
    }
    @FXML
    public void gestionarInventario(ActionEvent actionEvent) throws Exception {
        abrirVentana("GUI/Interfaz/GestionInventario.fxml", "Gestionar Inventario");
    }
    @FXML
    public void gestionarVentas(ActionEvent actionEvent) throws Exception {
        abrirVentana("GUI/Interfaz/GestionVenta.fxml", "Gestionar Ventas");
    }
    @FXML
    public void gestionarProductos(ActionEvent actionEvent) throws Exception {
        abrirVentana("GUI/Interfaz/GestionProducto.fxml", "Gestionar Productos");
    }
    @FXML
    public void gestionarProveedores(ActionEvent actionEvent) throws Exception {
        //abrirVentana("GUI/Interfaz/GestionProveedor.fxml", "Gestionar Proveedores");
    }
    @FXML
    public void gestionarClientes(ActionEvent actionEvent) throws Exception {
        //abrirVentana("GUI/Interfaz/GestionClientes.fxml", "Gestionar Clientes");
    }

    private void abrirVentana(String fxml, String titulo) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }

    @FXML
    public void entrarAuditar(ActionEvent actionEvent) {
    }
}
