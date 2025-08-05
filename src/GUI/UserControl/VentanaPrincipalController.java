package GUI.UserControl;

import Util.ContextoConexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class VentanaPrincipalController {

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
