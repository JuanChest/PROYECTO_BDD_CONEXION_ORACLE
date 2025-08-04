package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.ConexionFactory;
import Util.ContextoConexion;
import Util.TablaDistribuida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarDetalleVenta {
    public static void insertar(int idVenta, int idDetalle, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        String tabla = TablaDistribuida.obtenerNombre("DETALLE_VENTA");
        String sql = "INSERT INTO " + tabla + " (VENTA_ID, DETALLE_ID, PRODUCTO_ID, CANTIDAD, PRECIO_UNITARIO, SUB_TOTAL) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idVenta);
            stmt.setInt(2, idDetalle);
            stmt.setInt(3, idProducto);
            stmt.setInt(4, cantidad);
            stmt.setDouble(5, precioUnitario);
            stmt.setDouble(6, subtotal);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int idVenta, int idDetalle, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite actualizar desde una conexión remota.");
            return;
        }

        String tabla = TablaDistribuida.obtenerNombre("DETALLE_VENTA");
        String sql = "UPDATE " + tabla + " SET CANTIDAD = ?, PRECIO_UNITARIO = ?, SUB_TOTAL = ? WHERE VENTA_ID = ? AND DETALLE_ID = ? AND PRODUCTO_ID = ?";
        try (Connection conn = ConexionFactory.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cantidad);
            stmt.setDouble(2, precioUnitario);
            stmt.setDouble(3, subtotal);
            stmt.setInt(4, idVenta);
            stmt.setInt(5, idDetalle);
            stmt.setInt(6, idProducto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int idVenta, int idDetalle, int idProducto) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite eliminar desde una conexión remota.");
            return;
        }
        String tabla = TablaDistribuida.obtenerNombre("DETALLE_VENTA");
        String sql = "DELETE FROM " + tabla + " WHERE VENTA_ID = ? AND DETALLE_ID = ? AND PRODUCTO_ID = ?";
        try (Connection conn = ConexionFactory.obtenerConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idVenta);
                stmt.setInt(2, idDetalle);
                stmt.setInt(3, idProducto);
                stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> resultado = FXCollections.observableArrayList();
        
        try (Connection conn = ConexionFactory.obtenerConexion()) {;
            String tabla = TablaDistribuida.obtenerNombre("DETALLE_VENTA");
            String sql = "SELECT * FROM " + tabla;
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("VENTA_ID")));
                    fila.add(String.valueOf(rs.getInt("DETALLE_ID")));
                    fila.add(String.valueOf(rs.getInt("PRODUCTO_ID")));
                    fila.add(String.valueOf(rs.getDouble("CANTIDAD")));
                    fila.add(String.valueOf(rs.getDouble("PRECIO_UNITARIO")));
                    fila.add(String.valueOf(rs.getDouble("SUB_TOTAL")));
                    resultado.add(fila);
                }
            } 
        } catch (SQLException e) {
                    e.printStackTrace();
        }
        return resultado;
    }

    
}
