package GUI.UserControl;

import DataAccessComponent.ConexionOracleMaster;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GestionCategoriaController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colDescripcion;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdCategoria;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableView<ObservableList<String>> tablaCategoria;

    public static ObservableList<String> categoriaSeleccionada;

    @FXML
    public void initialize() {
        cargarDatos();
    }

    private void cargarDatos() {
        ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
        try (Connection conn = ConexionOracleMaster.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT CATEGORIA_ID, NOMBRE_CATEGORIA, DESCRIPCION FROM CATEGORIA ORDER BY CATEGORIA_ID");) {

            while (rs.next()) {
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(rs.getString("CATEGORIA_ID"));
                fila.add(rs.getString("NOMBRE_CATEGORIA"));
                fila.add(rs.getString("DESCRIPCION"));
                data.add(fila);
            }

            colIdCategoria.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(0)));
            colNombre.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(1)));
            colDescripcion.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(2)));

            tablaCategoria.setItems(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void agregarNuevaCategoria() throws Exception {
        abrirVentana("GUI/Interfaz/FormularioCategoria.fxml", "Registrar Categoría");
    }

    @FXML
    void editarCategoria() throws Exception {
        categoriaSeleccionada = tablaCategoria.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada != null) {
            abrirVentana("GUI/Interfaz/ModificadorCategoria.fxml", "Modificar Categoría");
        } else {
            mostrarAlerta("Seleccione una categoría para editar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void eliminarCategoria() {
        ObservableList<String> seleccionada = tablaCategoria.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            try (Connection conn = ConexionOracleMaster.getConnection();
                 Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("DELETE FROM CATEGORIA WHERE CATEGORIA_ID=" + seleccionada.get(0));
                cargarDatos();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mostrarAlerta("Seleccione una categoría para eliminar.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    void regresar() {
        ((Stage) btnEliminar1.getScene().getWindow()).close();
    }

    private void mostrarAlerta(String mensaje, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
    private void abrirVentana(String fxml, String titulo) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(fxml));
        Stage stage = new Stage();
        stage.setTitle(titulo);
        stage.setScene(new Scene(root));
        stage.showAndWait();
        cargarDatos(); // refrescar tabla después de cerrar
    }

}
