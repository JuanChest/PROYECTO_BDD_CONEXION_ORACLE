package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.ConexionFactory;
import Util.ContextoModulo;
import Util.TablaDistribuida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarProveedor {

    public static void insertar(int id, String nombre, String email, String telefono) {
        String provincia = ContextoModulo.getProvinciaActual();
        String tabla = TablaDistribuida.obtenerNombre("PROVEEDOR", provincia);
        String sql = "INSERT INTO " + tabla + " (PROVEEDOR_ID, NOMBRE, EMAIL, TELEFONO) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setString(3, email);
            stmt.setString(4, telefono);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, String nombre, String email, String telefono) {
        String provincia = ContextoModulo.getProvinciaActual();
        String tabla = TablaDistribuida.obtenerNombre("PROVEEDOR", provincia);
        String sql = "UPDATE " + tabla + " SET NOMBRE=?, EMAIL=?, TELEFONO=? WHERE PROVEEDOR_ID=?";

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, telefono);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int proveedorId) {
        try (Connection conn = ConexionFactory.obtenerConexion()) {
            conn.setAutoCommit(false);

            String provincia = ContextoModulo.getProvinciaActual();
            String tablaProducto = TablaDistribuida.obtenerNombre("PRODUCTO", provincia);
            String tablaProductoInventario = "PRODUCTO_INVENTARIO"; // Asumiendo que esta tabla no cambia de nombre
            String tablaDetalleVenta = "DETALLE_VENTA"; // Asumiendo fija

            // 1. Obtener PRODUCTO_IDs del proveedor
            String obtenerProductos = "SELECT PRODUCTO_ID FROM " + tablaProducto + " WHERE PROVEEDOR_ID = ?";
            try (PreparedStatement psProductos = conn.prepareStatement(obtenerProductos)) {
                psProductos.setInt(1, proveedorId);
                ResultSet rsProductos = psProductos.executeQuery();

                List<Integer> productos = new ArrayList<>();
                while (rsProductos.next()) {
                    productos.add(rsProductos.getInt("PRODUCTO_ID"));
                }
                rsProductos.close();

                // 2. Eliminar relaciones con INVENTARIO
                try (PreparedStatement psInv = conn.prepareStatement("DELETE FROM " + tablaProductoInventario + " WHERE PRODUCTO_ID = ?")) {
                    for (int prodId : productos) {
                        psInv.setInt(1, prodId);
                        psInv.executeUpdate();
                    }
                }

                // 3. Eliminar DETALLE_VENTA asociados a esos productos
                try (PreparedStatement psDetalle = conn.prepareStatement("DELETE FROM " + tablaDetalleVenta + " WHERE PRODUCTO_ID = ?")) {
                    for (int prodId : productos) {
                        psDetalle.setInt(1, prodId);
                        psDetalle.executeUpdate();
                    }
                }
            }

            // 4. Eliminar productos del proveedor
            try (PreparedStatement psDelProductos = conn.prepareStatement("DELETE FROM " + TablaDistribuida.obtenerNombre("PRODUCTO", provincia) + " WHERE PROVEEDOR_ID = ?")) {
                psDelProductos.setInt(1, proveedorId);
                psDelProductos.executeUpdate();
            }

            // 5. Finalmente, eliminar proveedor
            try (PreparedStatement psProveedor = conn.prepareStatement("DELETE FROM " + TablaDistribuida.obtenerNombre("PROVEEDOR", provincia) + " WHERE PROVEEDOR_ID = ?")) {
                psProveedor.setInt(1, proveedorId);
                psProveedor.executeUpdate();
            }

            conn.commit();
            System.out.println("Proveedor eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos(String provincia) {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();
        String tabla = TablaDistribuida.obtenerNombre("PROVEEDOR", provincia);
        String sql = "SELECT * FROM " + tabla;

        try (Connection conn = ConexionFactory.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(String.valueOf(rs.getInt("PROVEEDOR_ID")));
                fila.add(rs.getString("NOMBRE"));
                fila.add(rs.getString("EMAIL"));
                fila.add(rs.getString("TELEFONO"));
                datos.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }
}
