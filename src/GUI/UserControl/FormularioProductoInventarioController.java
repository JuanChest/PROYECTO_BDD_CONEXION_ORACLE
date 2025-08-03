package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FormularioProductoInventarioController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtInventarioID;

    @FXML
    private TextField txtProductoID;

    @FXML
    void guardarProductoInventario() {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO PRODUCTO_INVENTARIO (PRODUCTO_ID, INVENTARIO_ID) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtProductoID.getText()));
            ps.setInt(2, Integer.parseInt(txtInventarioID.getText()));
            ps.executeUpdate();

            mostrarAlerta("Registro insertado correctamente", Alert.AlertType.INFORMATION);
            regresar();
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al insertar el registro", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar() {
        ((Stage) btnGuardar1.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
