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

public class FormularioProductosController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCategoriaID;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtProductoID;

    @FXML
    private TextField txtProveedorID;

    @FXML
    void guardarProducto() {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO PRODUCTO (PRODUCTO_ID, PROVEEDOR_ID, CATEGORIA_ID, NOMBRE, PRECIO) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtProductoID.getText()));
            ps.setInt(2, Integer.parseInt(txtProveedorID.getText()));
            ps.setInt(3, Integer.parseInt(txtCategoriaID.getText()));
            ps.setString(4, txtNombre.getText());
            ps.setDouble(5, Double.parseDouble(txtPrecio.getText()));
            ps.executeUpdate();

            mostrarAlerta("Producto ingresado correctamente", Alert.AlertType.INFORMATION);
            regresar();
        } catch (Exception e){
            e.printStackTrace();
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
