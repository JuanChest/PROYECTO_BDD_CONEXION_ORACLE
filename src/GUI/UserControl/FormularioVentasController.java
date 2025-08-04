package GUI.UserControl;

import DataAccessComponent.AdministrarVentas;
import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormularioVentasController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtClienteID;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtTiendaID;

    @FXML
    private TextField txtTotal;

    @FXML
    private TextField txtVentaID;

    @FXML
    private DatePicker fecha;

    @FXML
    public void initialize() {
        txtVentaID.setEditable(false);
        cargarSiguienteID();
    }

    private void cargarSiguienteID() {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "SELECT MAX(VENTA_ID) AS MAX_ID FROM VENTAS";
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


    @FXML
    void guardarVentas(ActionEvent event) {
        try {
            int idVenta = Integer.parseInt(txtVentaID.getText());
            int idTienda = Integer.parseInt(txtTiendaID.getText());
            int idCliente = Integer.parseInt(txtClienteID.getText());
            //Date fecha = Date.valueOf(txtFecha.getText());
            Date fecha = Date.valueOf(getDate());
            double total = Double.parseDouble(txtTotal.getText());

            AdministrarVentas.insertar(idVenta, idTienda, idCliente, fecha, total);

            mostrarAlerta("Venta registrada correctamente", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionVenta.fxml", "Gestión de Ventas");
        } catch (Exception e){
            e.printStackTrace();
            mostrarAlerta("Error al registrar la venta", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionVenta.fxml", "Gestión de Ventas");
    }
    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    @FXML
    public String getDate(){
        LocalDate fechaSeleccionada = fecha.getValue();
        String fechaFormateada = fechaSeleccionada.format(DateTimeFormatter.ofPattern("YYYY-MM-dd"));
        txtFecha.setText(fechaFormateada);
        return fechaFormateada;
    }

}