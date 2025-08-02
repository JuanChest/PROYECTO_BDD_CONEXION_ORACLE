package GUI.UserControl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GestionDetalleVentaController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<?, ?> colCantidad;

    @FXML
    private TableColumn<?, ?> colIdDetalle;

    @FXML
    private TableColumn<?, ?> colIdProducto;

    @FXML
    private TableColumn<?, ?> colIdVenta;

    @FXML
    private TableColumn<?, ?> colPrecioUnitario;

    @FXML
    private TableColumn<?, ?> colSubtotal;

    @FXML
    private TableView<?> tablaClientes;

    @FXML
    void agregarNuevoDetalleVenta(ActionEvent event) {

    }

    @FXML
    void editarDetalleVenta(ActionEvent event) {

    }

    @FXML
    void eliminarDetalleVenta(ActionEvent event) {

    }

    @FXML
    void regresar(ActionEvent event) {

    }

}
