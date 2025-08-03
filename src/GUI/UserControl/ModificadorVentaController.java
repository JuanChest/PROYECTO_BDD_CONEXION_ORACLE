package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

public class ModificadorVentaController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtId1;

    @FXML
    private TextField txtId2;

    @FXML
    private TextField txtTotal;

    @FXML
    public void initialize(){
        if(GestionVentaController.ventaSeleccionada != null){
            txtId.setText(GestionVentaController.ventaSeleccionada.get(0));
            txtId1.setText(GestionVentaController.ventaSeleccionada.get(1));
            txtId2.setText(GestionVentaController.ventaSeleccionada.get(2));
            txtFecha.setText(GestionVentaController.ventaSeleccionada.get(3));
            txtTotal.setText(GestionVentaController.ventaSeleccionada.get(4));
            txtId.setDisable(true);
        }

    }

    @FXML
    void guardarVenta(ActionEvent event) {
        try(Connection conn = ConexionOracleMaster.getConnection()){
            String sql = "UPDATE VENTAS SET ID_TIENDA = ?, CLIENTE_ID = ?, FECHA = ?, TOTAL = ? WHERE VENTA_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(txtId.getText()));
            ps.setInt(2, Integer.parseInt(txtId1.getText()));
            ps.setDate(3, Date.valueOf(txtFecha.getText()));
            ps.setDouble(4, Double.parseDouble(txtTotal.getText()));
            ps.setInt(5, Integer.parseInt(txtId.getText()));

            int filas = ps.executeUpdate();
            if(filas > 0){
                mostrarAlerta("Venta actualizada correctamente", Alert.AlertType.INFORMATION);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionVenta.fxml", "Gestión de Ventas");
            } else {
                mostrarAlerta("Error al actualizar venta", Alert.AlertType.ERROR);
            }
        } catch (Exception e){
            e.printStackTrace();
            mostrarAlerta("Error al actualizar: " + e.getMessage(), Alert.AlertType.ERROR);
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

}
