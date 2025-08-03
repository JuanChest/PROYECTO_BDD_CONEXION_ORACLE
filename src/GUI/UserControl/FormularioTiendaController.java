package GUI.UserControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DataAccessComponent.AdministrarTienda;
import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioTiendaController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtRegionID;

    @FXML
    private TextField txtTiendaID;

    @FXML
    public void initialize() {
        txtTiendaID.setEditable(false);
        cargarSiguienteID();
    }

    private void cargarSiguienteID() {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "SELECT MAX(ID_TIENDA) AS MAX_ID FROM TIENDA";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    txtTiendaID.setText(String.valueOf(maxId + 1));
                } else {
                    txtTiendaID.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtTiendaID.setText("1");
        }
    }

    @FXML
    void guardarTienda(ActionEvent event) {
        try {
            int idTienda = Integer.parseInt(txtTiendaID.getText());
            String idRegionStr = txtRegionID.getText();
            String nombre = txtNombre.getText();
            String Direccion = txtDireccion.getText();

            if (idRegionStr.isEmpty() || nombre.isEmpty() || Direccion.isEmpty()) {
                System.out.println("Por favor, complete todos los campos.");
                return;
            }

            int idRegion = Integer.parseInt(idRegionStr);

            AdministrarTienda.insertar(idTienda, idRegion, nombre, Direccion);
            System.out.println("Tienda guardada correctamente.");

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
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionTienda.fxml", "Gestión de Tiendas");
    }

}
