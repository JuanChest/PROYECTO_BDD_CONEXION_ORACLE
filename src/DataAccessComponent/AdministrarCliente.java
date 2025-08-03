package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarCliente {
    public static void insertar(int id, String nombre, String apellido, String email, String telefono) {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            String sql = "INSERT INTO CLIENTE (CLIENTE_ID, NOMBRE, APELLIDO, EMAIL, TELEFONO) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.setString(2, nombre);
                stmt.setString(3, apellido);
                stmt.setString(4, email);
                stmt.setString(5, telefono);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, String nombre, String apellido, String email, String telefono) {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
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

    public static void eliminar(int clienteId) {
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            conn.setAutoCommit(false); // Transacción manual

            // 1. Obtener VENTA_IDs del cliente
            String obtenerVentas = "SELECT VENTA_ID FROM VENTAS WHERE CLIENTE_ID = ?";
            PreparedStatement psVentas = conn.prepareStatement(obtenerVentas);
            psVentas.setInt(1, clienteId);
            ResultSet rsVentas = psVentas.executeQuery();

            List<Integer> ventasCliente = new ArrayList<>();
            while (rsVentas.next()) {
                ventasCliente.add(rsVentas.getInt("VENTA_ID"));
            }
            rsVentas.close();
            psVentas.close();

            // 2. Eliminar DETALLE_VENTA por cada venta
            String eliminarDetalles = "DELETE FROM DETALLE_VENTA WHERE VENTA_ID = ?";
            PreparedStatement psDetalles = conn.prepareStatement(eliminarDetalles);
            for (int ventaId : ventasCliente) {
                psDetalles.setInt(1, ventaId);
                psDetalles.executeUpdate();
            }
            psDetalles.close();

            // 3. Eliminar VENTAS del cliente
            String eliminarVentas = "DELETE FROM VENTAS WHERE CLIENTE_ID = ?";
            PreparedStatement psElimVentas = conn.prepareStatement(eliminarVentas);
            psElimVentas.setInt(1, clienteId);
            psElimVentas.executeUpdate();
            psElimVentas.close();

            // 4. Finalmente, eliminar CLIENTE
            String eliminarCliente = "DELETE FROM CLIENTE WHERE CLIENTE_ID = ?";
            PreparedStatement psCliente = conn.prepareStatement(eliminarCliente);
            psCliente.setInt(1, clienteId);
            psCliente.executeUpdate();
            psCliente.close();

            conn.commit();
            System.out.println("Cliente eliminado correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT CLIENTE_ID, NOMBRE, APELLIDO, EMAIL, TELEFONO FROM CLIENTE";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("CLIENTE_ID")));
                    fila.add(rs.getString("NOMBRE"));
                    fila.add(rs.getString("APELLIDO"));
                    fila.add(rs.getString("EMAIL"));
                    fila.add(rs.getString("TELEFONO"));

                    datos.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}

