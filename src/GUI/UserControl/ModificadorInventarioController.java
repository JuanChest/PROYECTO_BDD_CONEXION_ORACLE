package GUI.UserControl;

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
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE INVENTARIO SET ID_TIENDA = ?, CANTIDAD = ? WHERE INVENTARIO_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtIdTienda.getText()));
            ps.setInt(2, Integer.parseInt(txtCantidad.getText()));
            ps.setInt(3, Integer.parseInt(txtId.getText()));

            int filas = ps.executeUpdate();
            if (filas > 0) {
                mostrarAlerta("Inventario actualizado correctamente", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionInventario.fxml", "Gestión del Inventario");
            } else {
                mostrarAlerta("No se pudo actualizar el inventario", Alert.AlertType.ERROR);
            }
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
}
