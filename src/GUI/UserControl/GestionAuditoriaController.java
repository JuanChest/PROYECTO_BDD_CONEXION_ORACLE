package GUI.UserControl;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GestionAuditoriaController {

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colFecha;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdAuditoria;

    @FXML
    private TableColumn<ObservableList<String>, String> colInfo;

    @FXML
    private TableColumn<ObservableList<String>, String> colOperacion;

    @FXML
    private TableColumn<ObservableList<String>, String> colTabla;
    
    @FXML
    private TableColumn<ObservableList<String>, String> colUsuario;

    @FXML
    private TableView<ObservableList<String>> tablaAuditoria;

    @FXML
    void initialize() {
        colIdAuditoria.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        colTabla.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        colOperacion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        colUsuario.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
        colFecha.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(4)));
        colInfo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(5)));

        tablaAuditoria.setItems(DataAccessComponent.AdministrarAuditoria.obtenerTodos());
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml","Menu Principal");
    }

}
