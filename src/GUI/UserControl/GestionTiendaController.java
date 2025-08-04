package GUI.UserControl;

import DataAccessComponent.AdministrarTienda;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class GestionTiendaController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colDireccion;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdRegion;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdTienda;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableView<ObservableList<String>> tablaClientes;

    @FXML
    public void initialize() {
        colIdTienda.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        colIdRegion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        colDireccion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));

        tablaClientes.setItems(AdministrarTienda.obtenerTodos());
        ajustarInterfazPorConexion();
    }

    private void ajustarInterfazPorConexion() {
        System.out.println("Tipo de conexión actual: " + Util.ContextoConexion.getTipoConexion());

        if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
            System.out.println("Modo REMOTO: Ocultando botones");
            btnAgregar.setVisible(false);
            btnEditar.setVisible(false);
            btnEliminar.setVisible(false);
        } else {
            System.out.println("Modo MASTER: Mostrando botones");
            btnAgregar.setVisible(true);
            btnEditar.setVisible(true);
            btnEliminar.setVisible(true);
        }
    }

    @FXML
    void agregarNuevaTienda(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioTienda.fxml", "Formulario de Tienda");
    }

    @FXML
    void editarTienda(ActionEvent event) {
        ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            System.out.println("Por favor, seleccione una tienda para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorTienda.fxml"));
            Parent root = loader.load();

            ModificadorTiendaController controlador = loader.getController();

            controlador.recibirDatos(seleccion.get(0), seleccion.get(1), seleccion.get(2), seleccion.get(3));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificar Tienda");
            stage.show();
        } catch (Exception e) {
            System.out.println("Error al cargar la ventana de modificación de tienda.");
        }
    }

    @FXML
    void eliminarTienda(ActionEvent event) {
        ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            System.out.println("Por favor, seleccione una tienda para eliminar.");
            return;
        }

        int tiendaId = Integer.parseInt(seleccion.get(0));
        AdministrarTienda.eliminar(tiendaId);

        // Refrescar la tabla asignando directamente los datos
        tablaClientes.setItems(AdministrarTienda.obtenerTodos());
    }

    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
    }

}
