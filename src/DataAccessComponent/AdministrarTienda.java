package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.ConexionFactory;
import Util.TablaDistribuida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarTienda {
    public static void insertar(int tiendaId, int regionId, String nombre, String direccion) {
        String tabla = TablaDistribuida.obtenerNombre("TIENDA");
        String sql = "INSERT INTO " + tabla + " (ID_TIENDA, REGION_ID, NOMBRE, DIRECCION) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tiendaId);
            stmt.setInt(2, regionId);
            stmt.setString(3, nombre);
            stmt.setString(4, direccion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int idTienda, int regionId, String nombre, String direccion) {
        String tabla = TablaDistribuida.obtenerNombre("TIENDA");
        String sql = "UPDATE " + tabla + " SET REGION_ID=?, NOMBRE=?, DIRECCION=? WHERE ID_TIENDA=?";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, regionId);
            stmt.setString(2, nombre);
            stmt.setString(3, direccion);
            stmt.setInt(4, idTienda);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int idTienda) {
        try (Connection conn = ConexionFactory.obtenerConexion()) {
            conn.setAutoCommit(false);

            String tablaVentas = TablaDistribuida.obtenerNombre("VENTAS");
            String tablaDetalleVenta = TablaDistribuida.obtenerNombre("DETALLE_VENTA"); // Asumo esta tabla no está fragmentada ni replicada
            String tablaInventario = TablaDistribuida.obtenerNombre("INVENTARIO");
            String tablaTienda = TablaDistribuida.obtenerNombre("TIENDA");

            // 1. Obtener ventas de la tienda
            String obtenerVentas = "SELECT VENTA_ID FROM " + tablaVentas + " WHERE ID_TIENDA = ?";
            List<Integer> ventas = new ArrayList<>();
            try (PreparedStatement psVentas = conn.prepareStatement(obtenerVentas)) {
                psVentas.setInt(1, idTienda);
                ResultSet rsVentas = psVentas.executeQuery();
                while (rsVentas.next()) {
                    ventas.add(rsVentas.getInt("VENTA_ID"));
                }
                rsVentas.close();
            }

            // 2. Eliminar DETALLE_VENTA por cada venta
            String eliminarDetalles = "DELETE FROM " + tablaDetalleVenta + " WHERE VENTA_ID = ?";
            try (PreparedStatement psDetalles = conn.prepareStatement(eliminarDetalles)) {
                for (int ventaId : ventas) {
                    psDetalles.setInt(1, ventaId);
                    psDetalles.executeUpdate();
                }
            }

            // 3. Eliminar VENTAS
            String eliminarVentas = "DELETE FROM " + tablaVentas + " WHERE ID_TIENDA = ?";
            try (PreparedStatement psElimVentas = conn.prepareStatement(eliminarVentas)) {
                psElimVentas.setInt(1, idTienda);
                psElimVentas.executeUpdate();
            }

            // 4. Eliminar INVENTARIO
            String eliminarInventario = "DELETE FROM " + tablaInventario + " WHERE ID_TIENDA = ?";
            try (PreparedStatement psInv = conn.prepareStatement(eliminarInventario)) {
                psInv.setInt(1, idTienda);
                psInv.executeUpdate();
            }

            // 6. Eliminar TIENDA
            String eliminarTienda = "DELETE FROM " + tablaTienda + " WHERE ID_TIENDA = ?";
            try (PreparedStatement psTienda = conn.prepareStatement(eliminarTienda)) {
                psTienda.setInt(1, idTienda);
                psTienda.executeUpdate();
            }

            conn.commit();
            System.out.println("Tienda eliminada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();
        String tabla = TablaDistribuida.obtenerNombre("TIENDA");
        String sql = "SELECT * FROM " + tabla;

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(String.valueOf(rs.getInt("ID_TIENDA")));
                fila.add(rs.getString("REGION_ID"));
                fila.add(rs.getString("NOMBRE"));
                fila.add(rs.getString("DIRECCION"));
                datos.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
