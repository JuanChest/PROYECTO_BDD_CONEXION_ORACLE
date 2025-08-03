package GUI.UserControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessComponent.AdministrarProveedor;
import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioProveedoresController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtProveedorID;

    @FXML
    private TextField txtTelefono;

    @FXML
    public void initialize() {
        txtProveedorID.setEditable(false);
        cargarSiguienteID();
    }

    private void cargarSiguienteID() {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "SELECT MAX(PROVEEDOR_ID) AS MAX_ID FROM PROVEEDOR";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    txtProveedorID.setText(String.valueOf(maxId + 1));
                } else {
                    txtProveedorID.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtProveedorID.setText("1");
        }
    }

    
    @FXML
    void guardarProveedor(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtProveedorID.getText());
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String telefono = txtTelefono.getText();

            if (nombre.isEmpty() || correo.isEmpty() || telefono.isEmpty()) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            AdministrarProveedor.insertar(id, nombre, correo, telefono);
            System.out.println("Proveedor guardado correctamente.");

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionProveedor.fxml", "Gestión de Proveedores");

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
