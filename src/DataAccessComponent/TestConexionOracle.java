package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestConexionOracle {
    public static void main(String[] args) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            System.out.println("✅ Conexión exitosa con la base de datos MASTER.");

            // INSERT de prueba en tabla PRODUCTO
            String sql = "INSERT INTO PRODUCTO (PRODUCTO_ID, PROVEEDOR_ID, CATEGORIA_ID, NOMBRE, PRECIO) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, 5); // ID de prueba (asegúrate de que no exista ya)
                stmt.setInt(2, 1);   // Asume que existe PROVEEDOR con ID 1
                stmt.setInt(3, 1);   // Asume que existe CATEGORIA con ID 1
                stmt.setString(4, "TestProd");
                stmt.setDouble(5, 9.99);

                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    System.out.println("✅ Registro insertado correctamente en la tabla PRODUCTO.");
                } else {
                    System.out.println("⚠️ No se insertó ningún registro.");
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ Error al conectar o insertar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}