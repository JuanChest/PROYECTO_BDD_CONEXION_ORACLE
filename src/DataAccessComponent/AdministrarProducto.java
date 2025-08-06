package DataAccessComponent;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.ContextoModulo;
import Util.TablaDistribuida;

public class AdministrarProducto {

    public static void insertar(int id, int proveedorId, String nombre, double precio) {
        String provincia = ContextoModulo.getProvinciaActual();
        String tabla = TablaDistribuida.obtenerNombre("PRODUCTO", provincia);
        String sql = "INSERT INTO "+ tabla + " (PRODUCTO_ID, PROVEEDOR_ID, NOMBRE, PRECIO) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionOracleMaster.getConnection()) {
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
        String provincia = ContextoModulo.getProvinciaActual();
        String tabla = TablaDistribuida.obtenerNombre("PRODUCTO", provincia);
        String sql = "UPDATE "+ tabla +" SET PROVEEDOR_ID = ?, NOMBRE = ?, PRECIO = ? WHERE PRODUCTO_ID = ?";
        try (Connection conn = ConexionOracleMaster.getConnection()) {
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
        String provincia = ContextoModulo.getProvinciaActual();
        String tablaProducto = TablaDistribuida.obtenerNombre("PRODUCTO", provincia);
        String tablaDetalleVenta = TablaDistribuida.obtenerNombre("DETALLE_VENTA", provincia);
        String tablaProductoInventario = TablaDistribuida.obtenerNombre("PRODUCTO_INVENTARIO", provincia);

        try (Connection conn = ConexionOracleMaster.getConnection()) {
            conn.setAutoCommit(false); // Iniciar transacción manual

            // 1. Eliminar de DETALLE_VENTA
            String eliminarDetalles = "DELETE FROM " + tablaDetalleVenta + " WHERE PRODUCTO_ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(eliminarDetalles)) {
                ps.setInt(1, productoId);
                ps.executeUpdate();
            }

            // 2. Eliminar de PRODUCTO_INVENTARIO
            String eliminarInventario = "DELETE FROM " + tablaProductoInventario + " WHERE PRODUCTO_ID = ?";
            try (PreparedStatement ps = conn.prepareStatement(eliminarInventario)) {
                ps.setInt(1, productoId);
                ps.executeUpdate();
            }

            // 3. Eliminar de PRODUCTO
            String eliminarProducto = "DELETE FROM " + tablaProducto + " WHERE PRODUCTO_ID = ?";
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


    public static ObservableList<ObservableList<String>> obtenerTodos(String provincia) {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();
        String tabla = TablaDistribuida.obtenerNombre("PRODUCTO", provincia);
        String sql = "SELECT * FROM " + tabla;

        try (Connection conn = ConexionOracleMaster.getConnection()) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("PRODUCTO_ID")));
                    fila.add(String.valueOf(rs.getInt("PROVEEDOR_ID")));
                    fila.add(rs.getString("NOMBRE"));
                    fila.add(String.valueOf(rs.getDouble("PRECIO")));
                    datos.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
