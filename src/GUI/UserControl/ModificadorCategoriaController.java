package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ModificadorCategoriaController {

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnGuardar1;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtNombre;


    @FXML
    public void initialize() {
        if (GestionCategoriaController.categoriaSeleccionada != null) {
            txtId.setText(GestionCategoriaController.categoriaSeleccionada.get(0));
            txtNombre.setText(GestionCategoriaController.categoriaSeleccionada.get(1));
            txtDescripcion.setText(GestionCategoriaController.categoriaSeleccionada.get(2));
            txtId.setDisable(true); // no permitir cambiar el ID
        }
    }

    @FXML
    void guardarCategoria(ActionEvent event) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE CATEGORIA SET NOMBRE_CATEGORIA = ?, DESCRIPCION = ? WHERE CATEGORIA_ID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, txtNombre.getText());
            ps.setString(2, txtDescripcion.getText());
            ps.setInt(3, Integer.parseInt(txtId.getText()));

            int filas = ps.executeUpdate();
            if (filas > 0) {
                mostrarAlerta("Categoría actualizada correctamente.", Alert.AlertType.INFORMATION);
                regresar();
            } else {
                mostrarAlerta("No se pudo actualizar la categoría.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mostrarAlerta("Error al actualizar: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void regresar() {
        ((Stage) txtId.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}
