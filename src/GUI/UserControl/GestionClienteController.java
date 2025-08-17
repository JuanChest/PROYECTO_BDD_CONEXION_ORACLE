package GUI.UserControl;

import DataAccessComponent.AdministrarCliente;
import DataAccessComponent.AdministrarDetalleVenta;
import Util.ContextoConexion;
import Util.ContextoModulo;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionClienteController {

    @FXML
    private TextField apellidoField;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnGuardar;

    @FXML
    private TextField cedulaField;

    @FXML
    private TextField clienteIdField;

    @FXML
    private TableColumn<ObservableList<String>, String> colApellido;

    @FXML
    private TableColumn<ObservableList<String>, String> colCedula;

    @FXML
    private TableColumn<ObservableList<String>, String> colEmail;

    @FXML
    private TableColumn<ObservableList<String>, String> colIdCliente;

    @FXML
    private TableColumn<ObservableList<String>, String> colNombre;

    @FXML
    private TableColumn<ObservableList<String>, String> colProvinciaId;

    @FXML
    private TableColumn<ObservableList<String>, String> colTelefono;

    @FXML
    private TextField emailField;

    @FXML
    private MenuItem menuClientesCotopaxi;

    @FXML
    private MenuItem menuClientesPichincha;

    @FXML
    private MenuItem menuClientesTungurahua;

    @FXML
    private MenuItem menuDetalleVentaCotopaxi;

    @FXML
    private MenuItem menuDetalleVentaPichincha;

    @FXML
    private MenuItem menuDetalleVentaTungurahua;

    @FXML
    private MenuItem menuInventarioCotopaxi;

    @FXML
    private MenuItem menuInventarioPichincha;

    @FXML
    private MenuItem menuInventarioTungurahua;

    @FXML
    private MenuItem menuTiendasCotopaxi;

    @FXML
    private MenuItem menuTiendasPichincha;

    @FXML
    private MenuItem menuTiendasTungurahua;

    @FXML
    private MenuItem menuVentasCotopaxi;

    @FXML
    private MenuItem menuVentasPichincha;

    @FXML
    private MenuItem menuVentasTungurahua;

    @FXML
    private TextField nombreField;

    @FXML
    private TextField provinciaIdField;

    @FXML
    private TableView<ObservableList<String>> tablaClientes;

    @FXML
    private TextField telefonoField;

    @FXML
    public void initialize() {
        String provincia = ContextoModulo.getProvinciaActual();
        System.out.println("Provincia: " + provincia);
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            menuClientesPichincha.setText("ClientesGuayas");
            menuClientesCotopaxi.setText("ClientesManabi");
            menuClientesTungurahua.setText("ClientesEsmeraldas");
            menuDetalleVentaPichincha.setText("DetalleVentaGuayas");
            menuDetalleVentaCotopaxi.setText("DetalleVentaManabi");
            menuDetalleVentaTungurahua.setText("DetalleVentaEsmeraldas");
            menuInventarioPichincha.setText("InventarioGuayas");
            menuInventarioCotopaxi.setText("InventarioManabi");
            menuInventarioTungurahua.setText("InventarioEsmeraldas");
            menuTiendasPichincha.setText("TiendasGuayas");
            menuTiendasCotopaxi.setText("TiendasManabi");
            menuTiendasTungurahua.setText("TiendasEsmeraldas");
            menuVentasPichincha.setText("VentasGuayas");
            menuVentasCotopaxi.setText("VentasManabi");
            menuVentasTungurahua.setText("VentasEsmeraldas");
        }
        colIdCliente.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(0)));
        colProvinciaId.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(1)));
        colNombre.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(2)));
        colApellido.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(3)));
        colCedula.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(4)));
        colEmail.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(5)));
        colTelefono.setCellValueFactory(param -> new javafx.beans.property.SimpleStringProperty(param.getValue().get(6)));
        tablaClientes.setItems(AdministrarCliente.obtenerTodos(provincia));

        tablaClientes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                clienteIdField.setText(newSelection.get(0));
                provinciaIdField.setText(newSelection.get(1));
                nombreField.setText(newSelection.get(2));
                apellidoField.setText(newSelection.get(3));
                cedulaField.setText(newSelection.get(4));
                emailField.setText(newSelection.get(5));
                telefonoField.setText(newSelection.get(6));

                clienteIdField.setDisable(true);
                clienteIdField.setEditable(false);
                provinciaIdField.setDisable(true);
                provinciaIdField.setEditable(false);
                nombreField.setEditable(true);
                apellidoField.setEditable(true);
                cedulaField.setEditable(true);
                emailField.setEditable(true);
                telefonoField.setEditable(true);
            } else {
                // Limpiar los campos
                clienteIdField.clear();
                provinciaIdField.clear();
                nombreField.clear();
                apellidoField.clear();
                cedulaField.clear();
                emailField.clear();
                telefonoField.clear();

                // Opcional: volver editables si quieres permitir inserción nueva
                clienteIdField.setEditable(true);
                clienteIdField.setDisable(false);
                provinciaIdField.setEditable(true);
                provinciaIdField.setDisable(false);
                nombreField.setEditable(true);
                apellidoField.setEditable(true);
                emailField.setEditable(true);
                cedulaField.setEditable(true);
                telefonoField.setEditable(true);
            }
        });

        tablaClientes.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                ObservableList<String> selectedItem = tablaClientes.getSelectionModel().getSelectedItem();
                if (selectedItem != null) {
                    tablaClientes.getSelectionModel().clearSelection();

                    clienteIdField.clear();
                    provinciaIdField.clear();
                    nombreField.clear();
                    apellidoField.clear();
                    cedulaField.clear();
                    emailField.clear();
                    telefonoField.clear();

                    clienteIdField.setEditable(true);
                    provinciaIdField.setEditable(true);
                    nombreField.setEditable(true);
                    apellidoField.setEditable(true);
                    cedulaField.setEditable(true);
                    emailField.setEditable(true);
                    telefonoField.setEditable(true);
                }
            }
        });

    }



    @FXML
    void editarCliente(ActionEvent event) {
        if (clienteIdField.getText().isEmpty() || provinciaIdField.getText().isEmpty() || nombreField.getText().isEmpty() ||
                apellidoField.getText().isEmpty () || cedulaField.getText().isEmpty() || emailField.getText().isEmpty() || telefonoField.getText().isEmpty())  {
            System.out.println("Error: Todos los campos deben estar llenos.");
            return;
        }
        try {
            int clienteId = Integer.parseInt(clienteIdField.getText());
            int provinciaId = Integer.parseInt(provinciaIdField.getText());
            String nombre = nombreField.getText();
            String apellido = apellidoField.getText();
            String cedula = cedulaField.getText();
            String email = emailField.getText();
            String telefono = telefonoField.getText();


            AdministrarCliente.actualizar(clienteId, provinciaId, nombre, apellido, cedula, email, telefono);

            tablaClientes.setItems(AdministrarCliente.obtenerTodos(ContextoModulo.getProvinciaActual()));
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar datos válidos para actualizar.");
        }

    }

    @FXML
    void eliminarCliente(ActionEvent event) {
        ObservableList<String> lista = tablaClientes.getSelectionModel().getSelectedItem();
        if (lista.isEmpty()) {
            System.out.println("Debe seleccionar un detalle de venta para eliminar.");
            return;
        }
        int clienteid = Integer.parseInt(lista.get(0));
        int provinciaid = Integer.parseInt(lista.get(1));

        AdministrarCliente.eliminar(clienteid, provinciaid);
        tablaClientes.setItems(AdministrarCliente.obtenerTodos(ContextoModulo.getProvinciaActual()));
    }

    @FXML
    void guardarCliente(ActionEvent event) {
        int clienteId = Integer.parseInt(clienteIdField.getText());
        int provinciaId = Integer.parseInt(provinciaIdField.getText());
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String cedula = cedulaField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();

        if (clienteIdField.getText().isEmpty() || provinciaIdField.getText().isEmpty() || nombreField.getText().isEmpty() ||
                apellidoField.getText().isEmpty () || cedulaField.getText().isEmpty() || emailField.getText().isEmpty() || telefonoField.getText().isEmpty())  {
            System.out.println("Error: Todos los campos deben estar llenos.");
            return;
        }


        /*
        if (AdministrarDetalleVenta.existeDetalle(idVenta, idDetalle, idProducto)) {
            System.out.println("Error: Ya existe un detalle de venta con esa clave primaria.");
            return;
        }*/


        AdministrarCliente.insertar(clienteId, provinciaId, nombre, apellido, cedula, email, telefono);

        tablaClientes.setItems(AdministrarCliente.obtenerTodos(ContextoModulo.getProvinciaActual()));
    }

    @FXML
    void irAuditoria(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaAuditoria.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaAuditoria.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Cotopaxi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesGlobal(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Replica");;
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Global");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Guayas");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Pichincha");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irClientesTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            ContextoModulo.setProvinciaActual("Manabi");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            ContextoModulo.setProvinciaActual("Tungurahua");
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaClientes.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irDetalleVentaTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaDetalleVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irInventarioTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaInventario.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irProductos(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProductos.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProductos.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irProveedores(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProveedor.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProveedor.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irProvincia(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProvincia.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaProvincia.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irTiendasTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaTiendas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasCotopaxi(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasPichincha(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

    @FXML
    void irVentasTungurahua(ActionEvent event) {
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.REMOTO) {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Remoto)");
        } else {
            Ventana.cambiarEscena(stage, "/GUI/Interfaz/PantallaVentas.fxml", "Proyecto: Menu Principal (Master)");
        }
    }

}


// package GUI.UserControl;

// import java.io.IOException;

// import DataAccessComponent.AdministrarCliente;
// import javafx.beans.property.SimpleStringProperty;
// import javafx.collections.ObservableList;
// import javafx.event.ActionEvent;
// import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.scene.control.Button;
// import javafx.scene.control.TableColumn;
// import javafx.scene.control.TableView;
// import javafx.stage.Stage;

// public class GestionClientesController {

//     @FXML
//     private Button btnAgregar;

//     @FXML
//     private Button btnEditar;

//     @FXML
//     private Button btnEliminar;

//     @FXML
//     private Button btnEliminar1;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colApellido;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colCorreo;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colIDCliente;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colNombre;

//     @FXML
//     private TableColumn<ObservableList<String>, String> colTelefono;

//     @FXML
//     private TableView<ObservableList<String>> tablaClientes;

//     @FXML
//     public void initialize() {
//         colIDCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
//         colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(1)));
//         colApellido.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(2)));
//         colCorreo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(3)));
//         colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(4)));

//         tablaClientes.setItems(AdministrarCliente.obtenerTodos());
//         ajustarInterfazPorConexion();
//     }

//     private void ajustarInterfazPorConexion() {
//         System.out.println("Tipo de conexión actual: " + Util.ContextoConexion.getTipoConexion());

//         if (Util.ContextoConexion.getTipoConexion() == Util.ContextoConexion.TipoConexion.REMOTO) {
//             System.out.println("Modo REMOTO: Ocultando botones");
//             btnAgregar.setVisible(false);
//             btnEditar.setVisible(false);
//             btnEliminar.setVisible(false);
//         } else {
//             System.out.println("Modo MASTER: Mostrando botones");
//             btnAgregar.setVisible(true);
//             btnEditar.setVisible(true);
//             btnEliminar.setVisible(true);
//         }
//     }



//     @FXML
//     void agregarNuevoCliente(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/FormularioClientes.fxml", "Agregar Nuevo Cliente");
//     }

//     @FXML
//     void editarCliente(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione un cliente para editar.");
//             return;
//         }

//         try {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/GUI/Interfaz/ModificadorCliente.fxml"));
//             Parent root = loader.load();

//             // Obtener controlador del modificador
//             ModificadorClienteController controladorEditar = loader.getController();

//             // Enviar datos a la ventana de modificación
//             controladorEditar.recibirDatos(
//                     seleccion.get(0), // id
//                     seleccion.get(1), // nombre
//                     seleccion.get(2), // apellido
//                     seleccion.get(3), // correo
//                     seleccion.get(4)  // teléfono
//             );

//             // Cambiar de escena
//             Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
//             stage.setScene(new Scene(root));
//             stage.setTitle("Modificación de Cliente");
//             stage.show();

//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }


//     @FXML
//     void eliminarCliente(ActionEvent event) {
//         ObservableList<String> seleccion = tablaClientes.getSelectionModel().getSelectedItem();

//         if (seleccion == null) {
//             System.out.println("Por favor, seleccione un cliente para eliminar.");
//             return;
//         }

//         int clienteId = Integer.parseInt(seleccion.get(0));
//         AdministrarCliente.eliminar(clienteId);

//         // Refrescar la tabla asignando directamente los datos
//         tablaClientes.setItems(AdministrarCliente.obtenerTodos());
//     }



//     @FXML
//     void regresar(ActionEvent event) {
//         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         Ventana.cambiarEscena(stage, "/GUI/Interfaz/MenuPrincipal.fxml", "Proyecto: Menu Principal");
//     }

// }
