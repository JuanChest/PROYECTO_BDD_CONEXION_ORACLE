package GUI.UserControl;

import DataAccessComponent.AdministrarVentas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private DatePicker fecha;

    public void recibirDatos(String id, String idTienda, String idCliente, String fecha, String total) {
        txtId.setText(id);
        txtId.setEditable(false);
        txtId1.setText(idTienda);
        txtId2.setText(idCliente);
        txtFecha.setText(fecha);
        // Si el string viene como "2022-03-02 00:00:00", cortamos solo la parte de la fecha

        if (fecha.contains(" ")) {
            txtFecha.setText(fecha.split(" ")[0]); // Queda solo "2022-03-02"
        } else {
            txtFecha.setText(fecha); // Por si ya viene solo con la fecha
        }

        txtTotal.setText(total);
    }

    // @FXML
    // public void initialize(){
    //     if(GestionVentaController.ventaSeleccionada != null){
    //         txtId.setText(GestionVentaController.ventaSeleccionada.get(0));
    //         txtId1.setText(GestionVentaController.ventaSeleccionada.get(1));
    //         txtId2.setText(GestionVentaController.ventaSeleccionada.get(2));
    //         txtFecha.setText(GestionVentaController.ventaSeleccionada.get(3));
    //         txtTotal.setText(GestionVentaController.ventaSeleccionada.get(4));
    //         txtId.setDisable(true);
    //     }

    // }

    @FXML
    void guardarVenta(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            int idTienda = Integer.parseInt(txtId1.getText());
            int idCliente = Integer.parseInt(txtId2.getText());
            System.out.println("Fecha ingresada: '" + txtFecha.getText() + "'");
            Date fecha = Date.valueOf(getDate());
            double total = Double.parseDouble(txtTotal.getText());

            // Validación opcional si quieres evitar campos en blanco totalmente
            if (txtFecha.getText().isEmpty() || txtTotal.getText().isEmpty()) {
                mostrarAlerta("Por favor, complete todos los campos.", Alert.AlertType.WARNING);
                return;
            }

            AdministrarVentas.actualizar(id, idTienda, idCliente, fecha, total);

            mostrarAlerta("Venta actualizada correctamente.", Alert.AlertType.INFORMATION);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionVenta.fxml", "Gestión de Ventas");
        } catch (Exception e) {
            mostrarAlerta("Error al actualizar: " + e.getMessage(), Alert.AlertType.ERROR);
            System.out.println("Error al actualizar la venta: " + e.getMessage());
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
