package GUI.UserControl;

import DataAccessComponent.AdministrarTienda;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificadorTiendaController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtId1;

    @FXML
    private TextField txtNombre;

    public void recibirDatos(String idTienda, String idRegion,  String nombre, String direccion) {
        txtId.setText(idTienda);
        txtId.setEditable(false);
        txtId1.setText(idRegion);
        txtNombre.setText(nombre);
        txtDireccion.setText(direccion);
    }

    @FXML
    void guardarTienda(ActionEvent event) {
        try {
            int idTienda = Integer.parseInt(txtId.getText());
            int idRegion = Integer.parseInt(txtId1.getText());
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();

            if (nombre.isEmpty() || direccion.isEmpty()) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            AdministrarTienda.actualizar(idTienda, idRegion, nombre, direccion);
            System.out.println("Tienda actualizada correctamente.");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionTienda.fxml", "Gestión de Tiendas");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionTienda.fxml", "Gestionar Tiendas");
    }

}
