package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.TablaDistribuida;
import Util.ConexionFactory;
import Util.ContextoConexion;
import Util.ContextoModulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarCliente {

    public static void insertar(int idCliente, int idProvincia, String nombre, String apellido, String cedula, String email, String telefono) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite insertar desde una conexión remota.");
            return;
        }

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String provincia = ContextoModulo.getProvinciaActual();
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE", provincia);
            String sql = "INSERT INTO " + tabla + " (CLIENTE_ID, PROVINCIA_ID, NOMBRE, APELLIDO, CEDULA, EMAIL, TELEFONO) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idCliente);
                stmt.setInt(2, idProvincia);
                stmt.setString(3, nombre);
                stmt.setString(4, apellido);
                stmt.setString(5, cedula);
                stmt.setString(6, email);
                stmt.setString(7, telefono);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, String nombre, String apellido, String cedula, String email, String telefono) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite actualizar desde una conexión remota.");
            return;
        }

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String provincia = ContextoModulo.getProvinciaActual();
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE", provincia);
            String sql = "UPDATE " + tabla + " SET NOMBRE=?, APELLIDO=?, CEDULA=?, EMAIL=?, TELEFONO=? WHERE CLIENTE_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido);
                stmt.setString(3, cedula);
                stmt.setString(4, email);
                stmt.setString(5, telefono);
                stmt.setInt(6, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int clienteId) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite eliminar desde una conexión remota.");
            return;
        }

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            conn.setAutoCommit(false); // Iniciar transacción manual

            String provincia = ContextoModulo.getProvinciaActual();

            // Obtener nombre tabla VENTAS según provincia
            String tablaVentas = TablaDistribuida.obtenerNombre("VENTAS", provincia);
            // Obtener nombre tabla DETALLE_VENTA según provincia
            String tablaDetalleVenta = TablaDistribuida.obtenerNombre("DETALLE_VENTA", provincia);
            // Obtener nombre tabla CLIENTE según provincia
            String tablaCliente = TablaDistribuida.obtenerNombre("CLIENTE", provincia);

            // Obtener todas las ventas del cliente
            String sqlObtenerVentas = "SELECT VENTA_ID FROM " + tablaVentas + " WHERE CLIENTE_ID = ?";
            List<Integer> ventasCliente = new ArrayList<>();

            try (PreparedStatement psVentas = conn.prepareStatement(sqlObtenerVentas)) {
                psVentas.setInt(1, clienteId);
                try (ResultSet rsVentas = psVentas.executeQuery()) {
                    while (rsVentas.next()) {
                        ventasCliente.add(rsVentas.getInt("VENTA_ID"));
                    }
                }
            }

            // Eliminar detalles de venta para cada venta del cliente
            String sqlEliminarDetalles = "DELETE FROM " + tablaDetalleVenta + " WHERE VENTA_ID = ?";
            try (PreparedStatement psDetalles = conn.prepareStatement(sqlEliminarDetalles)) {
                for (int ventaId : ventasCliente) {
                    psDetalles.setInt(1, ventaId);
                    psDetalles.executeUpdate();
                }
            }

            // Eliminar ventas del cliente
            String sqlEliminarVentas = "DELETE FROM " + tablaVentas + " WHERE CLIENTE_ID = ?";
            try (PreparedStatement psEliminarVentas = conn.prepareStatement(sqlEliminarVentas)) {
                psEliminarVentas.setInt(1, clienteId);
                psEliminarVentas.executeUpdate();
            }

            // Finalmente eliminar cliente
            String sqlEliminarCliente = "DELETE FROM " + tablaCliente + " WHERE CLIENTE_ID = ?";
            try (PreparedStatement psEliminarCliente = conn.prepareStatement(sqlEliminarCliente)) {
                psEliminarCliente.setInt(1, clienteId);
                psEliminarCliente.executeUpdate();
            }

            conn.commit();
            System.out.println("Cliente eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            // En caso de error podrías hacer rollback (si quieres manejarlo explícitamente)
        }
    }


    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String provincia = ContextoModulo.getProvinciaActual();
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE", provincia);
            String sql = "SELECT * FROM " + tabla;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("CLIENTE_ID")));
                    fila.add(rs.getString("PROVINCIA_ID"));
                    fila.add(rs.getString("NOMBRE"));
                    fila.add(rs.getString("APELLIDO"));
                    fila.add(rs.getString("CEDULA"));
                    fila.add(rs.getString("EMAIL"));
                    fila.add(rs.getString("TELEFONO"));

                    datos.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
