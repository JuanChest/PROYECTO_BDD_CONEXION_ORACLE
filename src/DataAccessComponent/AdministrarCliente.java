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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarCliente {

    public static void insertar(int id, String nombre, String apellido, String email, String telefono) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite insertar desde una conexión remota.");
            return;
        }

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE");
            String sql = "INSERT INTO " + tabla + " (CLIENTE_ID, NOMBRE, APELLIDO, EMAIL, TELEFONO) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, nombre);
                stmt.setString(3, apellido);
                stmt.setString(4, email);
                stmt.setString(5, telefono);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, String nombre, String apellido, String email, String telefono) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite actualizar desde una conexión remota.");
            return;
        }

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE");
            String sql = "UPDATE " + tabla + " SET NOMBRE=?, APELLIDO=?, EMAIL=?, TELEFONO=? WHERE CLIENTE_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido);
                stmt.setString(3, email);
                stmt.setString(4, telefono);
                stmt.setInt(5, id);
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
            conn.setAutoCommit(false); // Transacción manual

            String tablaVentas = TablaDistribuida.obtenerNombre("VENTAS");
            String sqlObtenerVentas = "SELECT VENTA_ID FROM " + tablaVentas + " WHERE CLIENTE_ID = ?";
            try (PreparedStatement psVentas = conn.prepareStatement(sqlObtenerVentas)) {
                psVentas.setInt(1, clienteId);
                ResultSet rsVentas = psVentas.executeQuery();

                List<Integer> ventasCliente = new ArrayList<>();
                while (rsVentas.next()) {
                    ventasCliente.add(rsVentas.getInt("VENTA_ID"));
                }
                rsVentas.close();

                String sqlEliminarDetalles = "DELETE FROM DETALLE_VENTA WHERE VENTA_ID = ?";
                try (PreparedStatement psDetalles = conn.prepareStatement(sqlEliminarDetalles)) {
                    for (int ventaId : ventasCliente) {
                        psDetalles.setInt(1, ventaId);
                        psDetalles.executeUpdate();
                    }
                }

                String sqlEliminarVentas = "DELETE FROM " + tablaVentas + " WHERE CLIENTE_ID = ?";
                try (PreparedStatement psElimVentas = conn.prepareStatement(sqlEliminarVentas)) {
                    psElimVentas.setInt(1, clienteId);
                    psElimVentas.executeUpdate();
                }

                String tablaCliente = TablaDistribuida.obtenerNombre("CLIENTE");
                String sqlEliminarCliente = "DELETE FROM " + tablaCliente + " WHERE CLIENTE_ID = ?";
                try (PreparedStatement psCliente = conn.prepareStatement(sqlEliminarCliente)) {
                    psCliente.setInt(1, clienteId);
                    psCliente.executeUpdate();
                }
            }

            conn.commit();
            System.out.println("Cliente eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE");
            String sql = "SELECT CLIENTE_ID, NOMBRE, APELLIDO, EMAIL, TELEFONO FROM " + tabla;
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("CLIENTE_ID")));
                    fila.add(rs.getString("NOMBRE"));
                    fila.add(rs.getString("APELLIDO"));
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
