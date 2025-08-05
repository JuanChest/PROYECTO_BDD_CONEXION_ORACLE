package GUI.UserControl;

import DataAccessComponent.AdministrarInventario;
import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModificadorInventarioController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtIdTienda;

    @FXML
    public void initialize() {
        if (GestionInventarioController.inventarioSeleccionado != null) {
            txtId.setText(GestionInventarioController.inventarioSeleccionado.get(0));
            txtIdTienda.setText(GestionInventarioController.inventarioSeleccionado.get(1));
            txtCantidad.setText(GestionInventarioController.inventarioSeleccionado.get(2));
            txtId.setDisable(true);
        }
    }

    @FXML
    void guardarInventario(ActionEvent event) {
        try {
            int idTienda = Integer.parseInt(txtIdTienda.getText());
            int idProducto = Integer.parseInt(txtIdProducto.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            int idInventario = Integer.parseInt(txtId.getText());

            AdministrarInventario.actualizar(idInventario, idTienda, idProducto, cantidad);
            mostrarAlerta("Inventario actualizado correctamente", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionInventario.fxml", "Gestión del Inventario");

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al actualizar inventario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionInventario.fxml", "Gestión del Inventario");
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void recibirDatos(String id, String idTienda, String idProducto,String cantidad) {
        txtId.setText(id);
        txtId.setEditable(false);
        txtIdTienda.setText(idTienda);
        txtIdProducto.setText(idProducto);
        txtCantidad.setText(cantidad);
    }
}
