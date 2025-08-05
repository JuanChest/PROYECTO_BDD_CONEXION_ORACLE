package GUI.UserControl;

import DataAccessComponent.AdministrarCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificadorClienteController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    public void recibirDatos(String id, String nombre, String apellido, String correo, String telefono) {
        txtId.setText(id);
        txtId.setEditable(false);
        txtNombre.setText(nombre);
        txtApellido.setText(apellido);
        txtCorreo.setText(correo);
        txtTelefono.setText(telefono);
    }

    @FXML
    void guardarCliente(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            AdministrarCliente.actualizar(id, nombre, apellido, correo, telefono);

            System.out.println("Cliente actualizado correctamente.");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionClientes.fxml", "Gestión de Clientes");

        } catch (NumberFormatException e) {
            System.out.println("ID inválido.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionClientes.fxml", "Gestión de Clientes");
    }

}
