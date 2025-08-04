package GUI.UserControl;

import DataAccessComponent.AdministrarProducto;
import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioProductosController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtProductoID;

    @FXML
    private TextField txtProveedorID;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    public void initialize() {
        txtProductoID.setEditable(false);
        cargarSiguienteID();
    }

    private void cargarSiguienteID() {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT MAX(PRODUCTO_ID) AS MAX_ID FROM PRODUCTO";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    txtProductoID.setText(String.valueOf(maxId + 1));
                } else {
                    txtProductoID.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtProductoID.setText("1");
        }
    }

    @FXML
    void guardarProducto(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtProductoID.getText());
            int proveedorId = Integer.parseInt(txtProveedorID.getText());
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText().replace(",", "."));

            if (nombre.isEmpty()) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            AdministrarProducto.insertar(id, proveedorId, nombre, precio);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionProducto.fxml", "Gestión de Productos");

        } catch (NumberFormatException e) {
            System.out.println("Datos numéricos inválidos.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionProducto.fxml", "Gestión de Productos");
    }
}
