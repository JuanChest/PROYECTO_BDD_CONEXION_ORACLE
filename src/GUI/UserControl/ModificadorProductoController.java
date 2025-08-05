package GUI.UserControl;

import DataAccessComponent.AdministrarProducto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ModificadorProductoController {

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

    public void recibirDatos(String id, String proveedorId, String nombre, String precio) {
        txtProductoID.setText(id);
        txtProductoID.setEditable(false);
        txtProveedorID.setText(proveedorId);
        txtNombre.setText(nombre);
        txtPrecio.setText(precio);
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

            AdministrarProducto.actualizar(id, proveedorId, nombre, precio);

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
