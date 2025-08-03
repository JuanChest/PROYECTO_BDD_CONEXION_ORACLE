package GUI.UserControl;

import java.io.IOException;

import DataAccessComponent.AdministrarCliente;
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

public class GestionClientesController {

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnEliminar1;

    @FXML
    private TableColumn<ObservableList<String>, String> colApellido;

    @FXML
    private TableColumn<ObservableList<String>, String> colCorreo;

    @FXML
    private TableColumn<ObservableList<String>, String> colIDCliente;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableColumn<ObservableList<String>, String> colTelefono;

    @FXML
    private TableView<ObservableList<String>> tablaClientes;

    @FXML
    public void initialize() {
        colIDCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
        colApellido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
        colCorreo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(4)));

        tablaClientes.setItems(AdministrarCliente.obtenerTodos());
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
    void agregarNuevoCliente(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioClientes.fxml", "Agregar Nuevo Cliente");
    }

    @FXML
    void editarCliente(ActionEvent event) {
        ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            System.out.println("Por favor, seleccione un cliente para editar.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorCliente.fxml"));
            Parent root = loader.load();

            // Obtener controlador del modificador
            ModificadorClienteController controladorEditar = loader.getController();

            // Enviar datos a la ventana de modificación
            controladorEditar.recibirDatos(
                    seleccion.get(0), // id
                    seleccion.get(1), // nombre
                    seleccion.get(2), // apellido
                    seleccion.get(3), // correo
                    seleccion.get(4)  // teléfono
            );

            // Cambiar de escena
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Modificación de Cliente");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void eliminarCliente(ActionEvent event) {
        ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccion == null) {
            System.out.println("Por favor, seleccione un cliente para eliminar.");
            return;
        }

        int clienteId = Integer.parseInt(seleccion.get(0));
        AdministrarCliente.eliminar(clienteId);

        // Refrescar la tabla asignando directamente los datos
        tablaClientes.setItems(AdministrarCliente.obtenerTodos());
    }



    @FXML
    void regresar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
    }

}
