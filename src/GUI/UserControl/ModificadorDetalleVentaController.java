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
            String txtCant = txtCantidad.getText();
            String txtPrec = txtPrecioUnitario.getText();
            // System.out.println("txtCantidad: '" + txtCant + "', txtPrecioUnitario: '" + txtPrec + "'");
            
            double cantidad = Double.parseDouble(txtCant);
            double precio = Double.parseDouble(txtPrec);
            double subtotal = cantidad * precio;
            // System.out.println("Subtotal calculado: " + subtotal);
            txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", subtotal));
        } catch (NumberFormatException e) {
            // System.out.println("Error de formato en cantidad o precio");
            txtSubtotal.setText("");
        }
    }

    @FXML
    void guardarDetalleVenta(ActionEvent event) {
        try (Connection conn = ConexionOracleMaster.getConnection()){
            String sql = "UPDATE DETALLE_VENTA SET PRODUCTO_ID = ?, CANTIDAD = ?, PRECIO_UNITARIO = ?, SUB_TOTAL = ? " +
                    "WHERE VENTA_ID = ? AND DETALLE_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtId2.getText()));
            ps.setDouble(2, Double.parseDouble(txtCantidad.getText()));
            ps.setDouble(3, Double.parseDouble(txtPrecioUnitario.getText()));
            ps.setDouble(4, Double.parseDouble(txtSubtotal.getText()));
            ps.setInt(5, Integer.parseInt(txtId.getText()));   // VENTA_ID
            ps.setInt(6, Integer.parseInt(txtId1.getText())); // DETALLE_ID

            int filas = ps.executeUpdate();
            if(filas > 0){
                mostrarAlerta("Detalle de venta actualizado correctamente", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionDetalleVenta.fxml", "Gestión de Detalles de Venta");
            } else {
                mostrarAlerta("No se pudo actualizar el detalle de venta", Alert.AlertType.ERROR);
            }

        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al actualizar el detalle de venta", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionDetalleVenta.fxml", "Gestión de Detalles de Venta");
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void recibirDatos(String string, String string2, String string3, String string4, String string5, String string6) {
        txtId.setText(string);
        txtId1.setText(string2);
        txtId2.setText(string3);
        txtCantidad.setText(string4);
        txtPrecioUnitario.setText(string5);
        txtSubtotal.setText(string6);

        // Deshabilitar campos de ID para evitar modificaciones
        txtId.setDisable(true);
        txtId1.setDisable(true);
        txtId2.setDisable(true);
        txtSubtotal.setEditable(false);

        calcularSubtotal();
    }

}
