package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

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
    void guardarVentas() {
        try(Connection conn = ConexionOracleMaster.getConnection()){
            String sql = "INSERT INTO VENTAS(VENTA_ID, ID_TIENDA, CLIENTE_ID, FECHA, TOTAL) VALUES(?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, Integer.parseInt(txtVentaID.getText()));
            ps.setInt(2, Integer.parseInt(txtTiendaID.getText()));
            ps.setInt(3, Integer.parseInt(txtClienteID.getText()));
            ps.setDate(4, Date.valueOf(txtFecha.getText()));
            ps.setDouble(5, Double.parseDouble(txtTotal.getText()));
            ps.executeUpdate();

            mostrarAlerta("Venta registrada correctamente", Alert.AlertType.INFORMATION);
            regresar();
        } catch (Exception e){
            e.printStackTrace();
            mostrarAlerta("Error al registrar la venta", Alert.AlertType.ERROR);
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