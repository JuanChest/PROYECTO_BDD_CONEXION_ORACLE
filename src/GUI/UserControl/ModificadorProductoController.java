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

public class ModificadorProductoController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtId1;

    @FXML
    private TextField txtId2;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    public void initialize() {
        if(GestionProductoController.productoSeleccionado != null) {
            txtId.setText(GestionProductoController.productoSeleccionado.get(0));
            txtId1.setText(GestionProductoController.productoSeleccionado.get(1));
            txtId2.setText(GestionProductoController.productoSeleccionado.get(2));
            txtNombre.setText(GestionProductoController.productoSeleccionado.get(3));
            txtPrecio.setText(GestionProductoController.productoSeleccionado.get(4));
            txtId.setDisable(true);
        }
    }

    @FXML
    void guardarProducto() {
        try(Connection conn = ConexionOracleMaster.getConnection()){
            String sql = "UPDATE PRODUCTO SET PROVEEDOR_ID = ?, CATEGORIA_ID = ?, NOMBRE = ?, PRECIO = ? WHERE PRODUCTO_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,Integer.parseInt(txtId1.getText()));
            ps.setInt(2,Integer.parseInt(txtId2.getText()));
            ps.setString(3,txtNombre.getText());
            ps.setString(4,txtPrecio.getText());
            ps.setInt(5,Integer.parseInt(txtId.getText()));

            int filas = ps.executeUpdate();
            if(filas > 0) {
                mostrarAlerta("Producto actualizado", Alert.AlertType.INFORMATION);
                regresar();
            } else {
                mostrarAlerta("No se pudo actualizar producto", Alert.AlertType.ERROR);
            }
        } catch (Exception e){
            e.printStackTrace();
            mostrarAlerta("Error al actualizar producto", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar() {
        ((Stage)btnGuardar1.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
