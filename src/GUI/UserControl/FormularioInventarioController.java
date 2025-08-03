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

public class FormularioInventarioController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtIdTienda;

    @FXML
    private TextField txtInventarioID;

    @FXML
    void guardarInventario(ActionEvent event) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO INVENTARIO (INVENTARIO_ID, ID_TIENDA, CANTIDAD) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtInventarioID.getText()));
            ps.setInt(2, Integer.parseInt(txtIdTienda.getText()));
            ps.setInt(3, Integer.parseInt(txtCantidad.getText()));

            ps.executeUpdate();
            mostrarAlerta("Inventario registrado correctamente", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionInventario.fxml", "Gestión del Inventario");
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al registrar inventario: " + e.getMessage(), Alert.AlertType.ERROR);
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
