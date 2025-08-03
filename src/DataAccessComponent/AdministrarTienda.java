package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministrarTienda {
    public static void insertar(int regionId, String nombre, String direccion) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO TIENDA (REGION_ID, NOMBRE, DIRECCION) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, regionId);
                stmt.setString(2, nombre);
                stmt.setString(3, direccion);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int idTienda, int regionId, String nombre, String direccion) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE TIENDA SET REGION_ID=?, NOMBRE=?, DIRECCION=? WHERE ID_TIENDA=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, regionId);
                stmt.setString(2, nombre);
                stmt.setString(3, direccion);
                stmt.setInt(4, idTienda);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int idTienda) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "DELETE FROM TIENDA WHERE ID_TIENDA=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idTienda);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> obtenerTodas() {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT * FROM TIENDA";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    fila.put("ID_TIENDA", rs.getInt("ID_TIENDA"));
                    fila.put("REGION_ID", rs.getInt("REGION_ID"));
                    fila.put("NOMBRE", rs.getString("NOMBRE"));
                    fila.put("DIRECCION", rs.getString("DIRECCION"));
                    lista.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
