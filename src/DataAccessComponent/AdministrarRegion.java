package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AdministrarRegion {
    public static List<Map<String, Object>> obtenerTodas() {
        List<Map<String, Object>> lista = new ArrayList<>();

        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT * FROM REGION";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    fila.put("REGION_ID", rs.getInt("REGION_ID"));
                    fila.put("NOMBRE_REGION", rs.getString("NOMBRE_REGION"));
                    lista.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }


    public static void insertar(String nombreRegion) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO REGION (NOMBRE_REGION) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombreRegion);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, String nombreRegion) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE REGION SET NOMBRE_REGION=? WHERE REGION_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombreRegion);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int id) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "DELETE FROM REGION WHERE REGION_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
