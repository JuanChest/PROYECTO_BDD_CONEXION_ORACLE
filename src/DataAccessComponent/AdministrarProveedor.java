package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarProveedor {
    public static void insertar(int id, String nombre, String email, String telefono) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO PROVEEDOR (PROVEEDOR_ID, NOMBRE, EMAIL, TELEFONO) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, nombre);
                stmt.setString(3, email);
                stmt.setString(4, telefono);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, String nombre, String email, String telefono) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE PROVEEDOR SET NOMBRE=?, EMAIL=?, TELEFONO=? WHERE PROVEEDOR_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, email);
                stmt.setString(3, telefono);
                stmt.setInt(4, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int proveedorId) {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            conn.setAutoCommit(false); // Transacción manual

            // 1. Obtener PRODUCTO_IDs del proveedor
            String obtenerProductos = "SELECT PRODUCTO_ID FROM PRODUCTO WHERE PROVEEDOR_ID = ?";
            PreparedStatement psProductos = conn.prepareStatement(obtenerProductos);
            psProductos.setInt(1, proveedorId);
            ResultSet rsProductos = psProductos.executeQuery();

            List<Integer> productos = new ArrayList<>();
            while (rsProductos.next()) {
                productos.add(rsProductos.getInt("PRODUCTO_ID"));
            }
            rsProductos.close();
            psProductos.close();

            // 2. Eliminar relaciones con INVENTARIO (PRODUCTO_INVENTARIO)
            String eliminarProdInventario = "DELETE FROM PRODUCTO_INVENTARIO WHERE PRODUCTO_ID = ?";
            PreparedStatement psInv = conn.prepareStatement(eliminarProdInventario);
            for (int prodId : productos) {
                psInv.setInt(1, prodId);
                psInv.executeUpdate();
            }
            psInv.close();

            // 3. Eliminar DETALLE_VENTA asociados a esos productos (opcional si el sistema lo requiere)
            String eliminarDetalleVenta = "DELETE FROM DETALLE_VENTA WHERE PRODUCTO_ID = ?";
            PreparedStatement psDetalle = conn.prepareStatement(eliminarDetalleVenta);
            for (int prodId : productos) {
                psDetalle.setInt(1, prodId);
                psDetalle.executeUpdate();
            }
            psDetalle.close();

            // 4. Eliminar productos del proveedor
            String eliminarProductos = "DELETE FROM PRODUCTO WHERE PROVEEDOR_ID = ?";
            PreparedStatement psDelProductos = conn.prepareStatement(eliminarProductos);
            psDelProductos.setInt(1, proveedorId);
            psDelProductos.executeUpdate();
            psDelProductos.close();

            // 5. Finalmente, eliminar proveedor
            String eliminarProveedor = "DELETE FROM PROVEEDOR WHERE PROVEEDOR_ID = ?";
            PreparedStatement psProveedor = conn.prepareStatement(eliminarProveedor);
            psProveedor.setInt(1, proveedorId);
            psProveedor.executeUpdate();
            psProveedor.close();

            conn.commit();
            System.out.println("Proveedor eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT * FROM PROVEEDOR";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("PROVEEDOR_ID")));
                    fila.add(rs.getString("NOMBRE"));
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

