package GUI.UserControl;

import Util.ContextoConexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionAuditoriaController {

    @FXML
    private TableColumn<?, ?> colFecha;

    @FXML
    private TableColumn<?, ?> colIdAudit;

    @FXML
    private TableColumn<?, ?> colInfo;

    @FXML
    private TableColumn<?, ?> colOperacion;

    @FXML
    private TableColumn<?, ?> colTabla;

    @FXML
    private TableColumn<?, ?> colUsuario;

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
    private TableView<?> tablaAuditoria;

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

// import javafx.beans.property.SimpleStringProperty;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.scene.Node;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.stage.Stage;

// public class GestionAuditoriaController {

//     @FXML
//     private Button btnEliminar1;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colFecha;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIdAuditoria;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colInfo;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colOperacion;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colTabla;
    
//     @FXML
//     private TableColumn<ObservableList<String>, String> colUsuario;

//     @FXML
//     private TableView<ObservableList<String>> tablaAuditoria;

//     @FXML
//     void initialize() {
//         colIdAuditoria.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
//         colTabla.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
//         colOperacion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
//         colUsuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
//         colFecha.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(4)));
//         colInfo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(5)));

//         tablaAuditoria.setItems(DataAccessComponent.AdministrarAuditoria.obtenerTodos());
//     }

//     @FXML
//     void regresar(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml","Menu Principal");
//     }

// }
