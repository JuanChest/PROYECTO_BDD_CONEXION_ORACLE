package DataAccessComponent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministrarProducto {

    public static void insertar(int id, int proveedorId, String nombre, double precio) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO PRODUCTO (PRODUCTO_ID, PROVEEDOR_ID, NOMBRE, PRECIO) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setInt(2, proveedorId);
                stmt.setString(3, nombre);
                stmt.setDouble(4, precio);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, int proveedorId, String nombre, double precio) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE PRODUCTO SET PROVEEDOR_ID = ?, NOMBRE = ?, PRECIO = ? WHERE PRODUCTO_ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, proveedorId);
                stmt.setString(2, nombre);
                stmt.setDouble(3, precio);
                stmt.setInt(4, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int productoId) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            conn.setAutoCommit(false); // Manejo manual de la transacción

            // 1. Eliminar registros de DETALLE_VENTA relacionados con este producto
            String eliminarDetalles = "DELETE FROM DETALLE_VENTA WHERE PRODUCTO_ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(eliminarDetalles)) {
                ps.setInt(1, productoId);
                ps.executeUpdate();
            }

            // 2. Finalmente, eliminar el PRODUCTO
            String eliminarProducto = "DELETE FROM PRODUCTO WHERE PRODUCTO_ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(eliminarProducto)) {
                ps.setInt(1, productoId);
                ps.executeUpdate();
            }

            conn.commit();
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT PRODUCTO_ID, PROVEEDOR_ID, NOMBRE, PRECIO FROM PRODUCTO";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("PRODUCTO_ID")));
                    fila.add(String.valueOf(rs.getInt("PROVEEDOR_ID")));
                    fila.add(rs.getString("NOMBRE"));
                    fila.add(String.valueOf(rs.getDouble("PRECIO"))); // Precio como String

                    datos.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
