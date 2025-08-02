package DataAccessComponent;

import java.sql.*;

public class TestConexionOracle {
    public static void main(String[] args) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            System.out.println("✅ Conexión exitosa con Oracle.");

            // Crear
            insertarCategoria(conn, 100, "Tecnología", "Categoría de artículos tecnológicos.");
            // Leer
            leerCategorias(conn);
            // Actualizar
            actualizarCategoria(conn, 100, "Tecnología y Gadgets", "Actualizado: artículos de alta tecnología.");
            // Leer otra vez
            leerCategorias(conn);
            // Eliminar
            eliminarCategoria(conn, 100);
            // Leer otra vez
            leerCategorias(conn);

        } catch (SQLException e) {
            System.out.println("❌ Error durante operaciones CRUD: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertarCategoria(Connection conn, int id, String nombre, String descripcion) throws SQLException {
        String sql = "INSERT INTO CATEGORIA (CATEGORIA_ID, NOMBRE_CATEGORIA, DESCRIPCION) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.setString(2, nombre);
            stmt.setString(3, descripcion);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Insertado correctamente." : "⚠️ No se insertó.");
        }
    }

    private static void leerCategorias(Connection conn) throws SQLException {
        String sql = "SELECT CATEGORIA_ID, NOMBRE_CATEGORIA, DESCRIPCION FROM CATEGORIA";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("📋 Categorías:");
            while (rs.next()) {
                int id = rs.getInt("CATEGORIA_ID");
                String nombre = rs.getString("NOMBRE_CATEGORIA");
                String descripcion = rs.getString("DESCRIPCION");
                System.out.println(" - ID: " + id + " | Nombre: " + nombre + " | Descripción: " + (descripcion != null ? descripcion : "N/A"));
            }
        }
    }

    private static void actualizarCategoria(Connection conn, int id, String nuevoNombre, String nuevaDescripcion) throws SQLException {
        String sql = "UPDATE CATEGORIA SET NOMBRE_CATEGORIA = ?, DESCRIPCION = ? WHERE CATEGORIA_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevaDescripcion);
            stmt.setInt(3, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Actualizado correctamente." : "⚠️ No se encontró el ID para actualizar.");
        }
    }

    private static void eliminarCategoria(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM CATEGORIA WHERE CATEGORIA_ID = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "✅ Eliminado correctamente." : "⚠️ No se encontró el ID para eliminar.");
        }
    }
}