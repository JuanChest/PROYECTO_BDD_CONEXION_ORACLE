package DataAccessComponent;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.ConexionFactory;
import Util.TablaDistribuida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarVentas {
    public static void insertar(int ventaId, int tiendaId, int clienteId, Date fecha, double total) {
        String tabla = TablaDistribuida.obtenerNombre("VENTAS");
        String sql = "INSERT INTO " + tabla + " (VENTA_ID, ID_TIENDA, CLIENTE_ID, FECHA, TOTAL) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ventaId);
            stmt.setInt(2, tiendaId);
            stmt.setInt(3, clienteId);
            stmt.setDate(4, fecha);
            stmt.setDouble(5, total);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int ventaId, int tiendaId, int clienteId, Date fecha, double total) {
        String tabla = TablaDistribuida.obtenerNombre("VENTAS");
        String sql = "UPDATE " + tabla + " SET ID_TIENDA=?, CLIENTE_ID=?, FECHA=?, TOTAL=? WHERE VENTA_ID=?";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tiendaId);
            stmt.setInt(2, clienteId);
            stmt.setDate(3, fecha);
            stmt.setDouble(4, total);
            stmt.setInt(5, ventaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int ventaId) {
        String tabla = TablaDistribuida.obtenerNombre("VENTAS");
        String sql = "DELETE FROM " + tabla + " WHERE VENTA_ID=?";
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ventaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos () {
        String tabla = TablaDistribuida.obtenerNombre("VENTAS");
        String sql = "SELECT * FROM " + tabla + " ORDER BY VENTA_ID";
        ObservableList<ObservableList<String>> listaVentas = FXCollections.observableArrayList();
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(String.valueOf(rs.getInt("VENTA_ID")));
                fila.add(String.valueOf(rs.getInt("ID_TIENDA")));
                fila.add(String.valueOf(rs.getInt("CLIENTE_ID")));
                fila.add(rs.getString("FECHA"));
                fila.add(String.valueOf(rs.getDouble("TOTAL")));
                listaVentas.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVentas;
    }
}

