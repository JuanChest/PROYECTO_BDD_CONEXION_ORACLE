package GUI.UserControl;

import DataAccessComponent.AdministrarInventario;
import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FormularioInventarioController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtIdProducto;

    @FXML
    private TextField txtIdTienda;

    @FXML
    private TextField txtInventarioID;

    @FXML
    public void initialize() {
        txtInventarioID.setEditable(false);
        cargarSiguienteID();
    }

    private void cargarSiguienteID() {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "SELECT MAX(INVENTARIO_ID) AS MAX_ID FROM INVENTARIO";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    int maxId = rs.getInt("MAX_ID");
                    txtInventarioID.setText(String.valueOf(maxId + 1));
                } else {
                    txtInventarioID.setText("1");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            txtInventarioID.setText("1");
        }
    }

    @FXML
    void guardarInventario(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtInventarioID.getText());
            int idTienda = Integer.parseInt(txtIdTienda.getText());
            int productoId = Integer.parseInt(txtIdProducto.getText());
            int cantidad = Integer.parseInt(txtCantidad.getText());

            AdministrarInventario.insertar(id, idTienda, productoId, cantidad);
            mostrarAlerta("Inventario guardado correctamente.", Alert.AlertType.INFORMATION);
            regresar(event);
        } catch (NumberFormatException e) {
            mostrarAlerta("Por favor, ingrese valores válidos.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al guardar el inventario: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/GestionInventario.fxml", "Gestión del Inventario");
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
