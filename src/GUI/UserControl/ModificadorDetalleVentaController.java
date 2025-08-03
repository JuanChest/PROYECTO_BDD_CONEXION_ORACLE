package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModificadorDetalleVentaController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtId; // VENTA_ID

    @FXML
    private TextField txtId1; // DETALLE_ID

    @FXML
    private TextField txtId2; // PRODUCTO_ID

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    private TextField txtSubtotal;

    @FXML
    public void initialize(){
        if(GestionDetalleVentaController.detalleSeleccionado != null){
            txtId.setText(GestionDetalleVentaController.detalleSeleccionado.get(0));
            txtId1.setText(GestionDetalleVentaController.detalleSeleccionado.get(1));
            txtId2.setText(GestionDetalleVentaController.detalleSeleccionado.get(2));
            txtCantidad.setText(GestionDetalleVentaController.detalleSeleccionado.get(3));
            txtPrecioUnitario.setText(GestionDetalleVentaController.detalleSeleccionado.get(4));
            txtSubtotal.setText(GestionDetalleVentaController.detalleSeleccionado.get(5));

            txtId.setDisable(true);
            txtId1.setDisable(true);
        }

        // Calcular subtotal automáticamente al cambiar cantidad o precio
        txtCantidad.textProperty().addListener((obs, oldVal, newVal) -> calcularSubtotal());
        txtPrecioUnitario.textProperty().addListener((obs, oldVal, newVal) -> calcularSubtotal());
    }

    private void calcularSubtotal() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecioUnitario.getText());
            double subtotal = cantidad * precio;
            txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", subtotal));
        } catch (NumberFormatException e) {
            txtSubtotal.setText("");
        }
    }

    @FXML
    void guardarDetalleVenta() {
        try (Connection conn = ConexionOracleMaster.getConnection()){
            String sql = "UPDATE DETALLE_VENTA SET PRODUCTO_ID = ?, CANTIDAD = ?, PRECIO_UNITARIO = ?, SUB_TOTAL = ? " +
                    "WHERE VENTA_ID = ? AND DETALLE_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtId2.getText()));
            ps.setInt(2, Integer.parseInt(txtCantidad.getText()));
            ps.setDouble(3, Double.parseDouble(txtPrecioUnitario.getText()));
            ps.setDouble(4, Double.parseDouble(txtSubtotal.getText()));
            ps.setInt(5, Integer.parseInt(txtId.getText()));   // VENTA_ID
            ps.setInt(6, Integer.parseInt(txtId1.getText())); // DETALLE_ID

            int filas = ps.executeUpdate();
            if(filas > 0){
                mostrarAlerta("Detalle de venta actualizado correctamente", Alert.AlertType.INFORMATION);
                regresar();
            } else {
                mostrarAlerta("No se pudo actualizar el detalle de venta", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al actualizar el detalle de venta", Alert.AlertType.ERROR);
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
