package GUI.UserControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessComponent.AdministrarCliente;
import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioClientesController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtClienteID;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtTelefono;

    @FXML
    public void initialize() {
        txtClienteID.setEditable(false);
        cargarSiguienteID();
    }

    private void cargarSiguienteID() {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "SELECT MAX(CLIENTE_ID) AS MAX_ID FROM CLIENTE";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    txtClienteID.setText(String.valueOf(maxId + 1));
                } else {
                    txtClienteID.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtClienteID.setText("1");
        }
    }



    @FXML
    void guardarCliente(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtClienteID.getText());
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();

            if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            AdministrarCliente.insertar(id, nombre, apellido, correo, telefono);
            System.out.println("Cliente guardado correctamente.");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionClientes.fxml", "Gestión de Clientes");

        } catch (NumberFormatException e) {
            System.out.println("ID inválido:" + e.getMessage());
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
