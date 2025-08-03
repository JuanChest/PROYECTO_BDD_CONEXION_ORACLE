package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministrarCliente {
    public static void insertar(int id, String nombre, String apellido, String email, String telefono) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO CLIENTE (, NOMBRE, APELLIDO, EMAIL, TELEFONO) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido);
                stmt.setString(3, email);
                stmt.setString(4, telefono);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, String nombre, String apellido, String email, String telefono) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE CLIENTE SET NOMBRE=?, APELLIDO=?, EMAIL=?, TELEFONO=? WHERE CLIENTE_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido);
                stmt.setString(3, email);
                stmt.setString(4, telefono);
                stmt.setInt(5, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int id) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "DELETE FROM CLIENTE WHERE CLIENTE_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Object>> obtenerTodas() {
        List<Map<String, Object>> lista = new ArrayList<>();
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT * FROM CLIENTE";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    fila.put("CLIENTE_ID", rs.getInt("CLIENTE_ID"));
                    fila.put("NOMBRE", rs.getString("NOMBRE"));
                    fila.put("APELLIDO", rs.getString("APELLIDO"));
                    fila.put("EMAIL", rs.getString("EMAIL"));
                    fila.put("TELEFONO", rs.getString("TELEFONO"));
                    lista.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

