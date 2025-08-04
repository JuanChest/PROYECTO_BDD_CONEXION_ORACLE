package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.ConexionFactory;
import Util.TablaDistribuida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarInventario {
    public static void insertar(int id, int idTienda, int productoId, int cantidad) {
        String tabla = TablaDistribuida.obtenerNombre("INVENTARIO");
        String sql = "INSERT INTO " + tabla + " (INVENTARIO_ID, ID_TIENDA, PRODUCTO_ID, CANTIDAD) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setInt(2, idTienda);
            stmt.setInt(3, productoId);
            stmt.setInt(4, cantidad);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, int idTienda, int productoId, int cantidad) {
        String tabla = TablaDistribuida.obtenerNombre("INVENTARIO");
        String sql = "UPDATE " + tabla + " SET ID_TIENDA=?, PRODUCTO_ID=?, CANTIDAD=? WHERE INVENTARIO_ID=?";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idTienda);
            stmt.setInt(2, productoId);
            stmt.setInt(3, cantidad);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int inventarioId) {
        String tabla = TablaDistribuida.obtenerNombre("INVENTARIO");
        String sql = "DELETE FROM " + tabla + " WHERE INVENTARIO_ID=?";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, inventarioId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> inventarios = FXCollections.observableArrayList();
        String tabla = TablaDistribuida.obtenerNombre("INVENTARIO");
        String sql = "SELECT * FROM " + tabla;
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(String.valueOf(rs.getInt("INVENTARIO_ID")));
                fila.add(String.valueOf(rs.getInt("ID_TIENDA")));
                fila.add(String.valueOf(rs.getInt("PRODUCTO_ID")));
                fila.add(String.valueOf(rs.getInt("CANTIDAD")));
                inventarios.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inventarios;
    }
}
