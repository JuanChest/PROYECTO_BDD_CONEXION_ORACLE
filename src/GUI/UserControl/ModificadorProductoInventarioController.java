package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModificadorProductoInventarioController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtIdInventario;

    @FXML
    public void initialize() {
        if (GestionProductoInventarioController.productoInventarioSeleccionado != null) {
            txtIdProducto.setText(GestionProductoInventarioController.productoInventarioSeleccionado.get(0));
            txtIdInventario.setText(GestionProductoInventarioController.productoInventarioSeleccionado.get(1));
            txtIdProducto.setDisable(true); // Clave primaria
            txtIdInventario.setDisable(true); // Clave primaria
        }
    }

    @FXML
    void guardarProductoInventario() {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE PRODUCTO_INVENTARIO SET PRODUCTO_ID = ?, INVENTARIO_ID = ? " +
                    "WHERE PRODUCTO_ID = ? AND INVENTARIO_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtIdProducto.getText()));
            ps.setInt(2, Integer.parseInt(txtIdInventario.getText()));
            ps.setInt(3, Integer.parseInt(GestionProductoInventarioController.productoInventarioSeleccionado.get(0)));
            ps.setInt(4, Integer.parseInt(GestionProductoInventarioController.productoInventarioSeleccionado.get(1)));

            int filas = ps.executeUpdate();
            if (filas > 0) {
                mostrarAlerta("Registro actualizado correctamente", Alert.AlertType.INFORMATION);
                regresar();
            } else {
                mostrarAlerta("No se pudo actualizar el registro", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al actualizar el registro", Alert.AlertType.ERROR);
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
