package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FormularioCategoriaController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCategoriaID;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombre;

    @FXML
    void guardarCategoria() {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO CATEGORIA (CATEGORIA_ID, NOMBRE_CATEGORIA, DESCRIPCION) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtCategoriaID.getText()));
            ps.setString(2, txtNombre.getText());
            ps.setString(3, txtDescripcion.getText());
            ps.executeUpdate();

            mostrarAlerta("Categoría registrada correctamente.", Alert.AlertType.INFORMATION);
            regresar();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al registrar la categoría.", Alert.AlertType.ERROR);
        }

    }

    @FXML
    void regresar() {
        ((Stage) txtCategoriaID.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
