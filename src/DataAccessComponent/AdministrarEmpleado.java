package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdministrarEmpleado {
    public static void insertar(int idTienda, String nombre, String apellido, String cargo) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO EMPLEADO (ID_TIENDA, NOMBRE, APELLIDO, CARGO) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idTienda);
                stmt.setString(2, nombre);
                stmt.setString(3, apellido);
                stmt.setString(4, cargo);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, int idTienda, String nombre, String apellido, String cargo) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "UPDATE EMPLEADO SET ID_TIENDA=?, NOMBRE=?, APELLIDO=?, CARGO=? WHERE EMPLEADO_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idTienda);
                stmt.setString(2, nombre);
                stmt.setString(3, apellido);
                stmt.setString(4, cargo);
                stmt.setInt(5, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int id) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "DELETE FROM EMPLEADO WHERE EMPLEADO_ID=?";
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
            String sql = "SELECT * FROM EMPLEADO";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
                    Map<String, Object> fila = new HashMap<>();
                    fila.put("EMPLEADO_ID", rs.getInt("EMPLEADO_ID"));
                    fila.put("ID_TIENDA", rs.getInt("ID_TIENDA"));
                    fila.put("NOMBRE", rs.getString("NOMBRE"));
                    fila.put("APELLIDO", rs.getString("APELLIDO"));
                    fila.put("CARGO", rs.getString("CARGO"));
                    lista.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}

