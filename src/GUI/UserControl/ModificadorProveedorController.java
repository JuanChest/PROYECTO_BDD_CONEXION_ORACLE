package GUI.UserControl;

import DataAccessComponent.AdministrarProveedor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificadorProveedorController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    public void recibirDatos(String id, String nombre, String correo, String telefono) {
        txtId.setText(id);
        txtId.setEditable(false);
        txtNombre.setText(nombre);
        txtCorreo.setText(correo);
        txtTelefono.setText(telefono);
    }
    
    @FXML
    void guardarProveedor(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();

            if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            AdministrarProveedor.actualizar(id, nombre, correo, telefono);

            System.out.println("Proveedor actualizado correctamente.");
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionProveedor.fxml", "Gestionar Proveedores");
        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionProveedor.fxml", "Gestionar Proveedores");
    }

}
