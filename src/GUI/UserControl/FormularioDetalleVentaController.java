package GUI.UserControl;

import DataAccessComponent.AdministrarDetalleVenta;
import DataAccessComponent.ConexionOracleMaster;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioDetalleVentaController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtDetalleID;

    @FXML
    private TextField txtPrecioUnitario;

    @FXML
    private TextField txtProductoID;

    @FXML
    private TextField txtSubtotal;

    @FXML
    private TextField txtVentaID;

    @FXML
    public void initialize() {
        // Calcular subtotal cuando cambie cantidad o precio unitario
        ChangeListener<String> recalcularSubtotal = (ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            calcularSubtotal();
        };
        txtCantidad.textProperty().addListener(recalcularSubtotal);
        txtPrecioUnitario.textProperty().addListener(recalcularSubtotal);
        txtVentaID.setEditable(false);
        txtSubtotal.setEditable(false);

        cargarSiguienteID();
    }

    private void cargarSiguienteID() {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "SELECT MAX(VENTA_ID) AS MAX_ID FROM DETALLE_VENTA";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    txtVentaID.setText(String.valueOf(maxId + 1));
                } else {
                    txtVentaID.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtVentaID.setText("1");
        }
    }

    private void calcularSubtotal() {
        try {
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precio = Double.parseDouble(txtPrecioUnitario.getText());
            double subtotal = cantidad * precio;
            txtSubtotal.setText(String.format(java.util.Locale.US, "%.2f", subtotal));

        } catch (NumberFormatException e) {
            txtSubtotal.setText(""); // Si no hay valores válidos, limpiar
        }
    }

    @FXML
    void guardarDetallesVenta(ActionEvent event) {
        try {
            int ventaId = Integer.parseInt(txtVentaID.getText());
            int detalleId = Integer.parseInt(txtDetalleID.getText());
            int productoId = Integer.parseInt(txtProductoID.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());
            double precioUnitario = Double.parseDouble(txtPrecioUnitario.getText());
            double subtotal = Double.parseDouble(txtSubtotal.getText());

            if (cantidad <= 0 || precioUnitario <= 0) {
                mostrarAlerta("Cantidad y Precio Unitario deben ser mayores a cero", Alert.AlertType.WARNING);
                return;
            }

            AdministrarDetalleVenta.actualizar(ventaId, detalleId, productoId, cantidad, precioUnitario, subtotal);

            mostrarAlerta("Detalle de venta actualizado correctamente", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionDetalleVenta.fxml", "Gestión de Detalles de Venta");

        } catch (NumberFormatException e) {
            mostrarAlerta("Error en el formato de los campos numéricos", Alert.AlertType.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al actualizar el detalle de venta: " + e.getMessage(), Alert.AlertType.ERROR);
        }
        // try (Connection conn = ConexionOracleMaster.getConnection()) {
        //     String sql = "INSERT INTO DETALLE_VENTA(VENTA_ID, DETALLE_ID, PRODUCTO_ID, CANTIDAD, PRECIO_UNITARIO, SUB_TOTAL) VALUES (?,?,?,?,?,?)";
        //     PreparedStatement ps = conn.prepareStatement(sql);
        //     ps.setInt(1, Integer.parseInt(txtVentaID.getText()));
        //     ps.setInt(2, Integer.parseInt(txtDetalleID.getText()));
        //     ps.setInt(3, Integer.parseInt(txtProductoID.getText()));
        //     ps.setInt(4, Integer.parseInt(txtCantidad.getText()));
        //     ps.setDouble(5, Double.parseDouble(txtPrecioUnitario.getText()));
        //     ps.setDouble(6, Double.parseDouble(txtSubtotal.getText()));

        //     ps.executeUpdate(); // Ejecutar el INSERT

        //     mostrarAlerta("Detalle de venta registrado correctamente", Alert.AlertType.INFORMATION);
        //     Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        //     Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionDetalleVenta.fxml", "Gestión de Detalles de Venta");
        // } catch (Exception e) {
        //     e.printStackTrace();
        //     mostrarAlerta("Error al registrar el detalle de venta: " + e.getMessage(), Alert.AlertType.ERROR);
        // }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionDetalleVenta.fxml", "Gestión de Detalles de Venta");
        
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
