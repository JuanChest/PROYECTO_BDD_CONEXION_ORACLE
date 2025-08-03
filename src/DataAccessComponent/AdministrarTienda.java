package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarTienda {
    public static void insertar(int tiendaId, int regionId, String nombre, String direccion) {
        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "INSERT INTO TIENDA (ID_TIENDA, REGION_ID, NOMBRE, DIRECCION) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, tiendaId);
                stmt.setInt(2, regionId);
                stmt.setString(3, nombre);
                stmt.setString(4, direccion);
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
        try {
            Connection conn = ConexionOracleMaster.getConnection();
            conn.setAutoCommit(false); // Inicio de transacción

            // 1. Obtener ventas de la tienda
            String obtenerVentas = "SELECT VENTA_ID FROM VENTAS WHERE ID_TIENDA = ?";
            PreparedStatement psVentas = conn.prepareStatement(obtenerVentas);
            psVentas.setInt(1, idTienda);
            ResultSet rsVentas = psVentas.executeQuery();

            List<Integer> ventas = new ArrayList<>();
            while (rsVentas.next()) {
                ventas.add(rsVentas.getInt("VENTA_ID"));
            }
            rsVentas.close();
            psVentas.close();

            // 2. Eliminar DETALLE_VENTA por cada venta
            String eliminarDetalles = "DELETE FROM DETALLE_VENTA WHERE VENTA_ID = ?";
            PreparedStatement psDetalles = conn.prepareStatement(eliminarDetalles);
            for (int ventaId : ventas) {
                psDetalles.setInt(1, ventaId);
                psDetalles.executeUpdate();
            }
            psDetalles.close();

            // 3. Eliminar VENTAS
            String eliminarVentas = "DELETE FROM VENTAS WHERE ID_TIENDA = ?";
            PreparedStatement psElimVentas = conn.prepareStatement(eliminarVentas);
            psElimVentas.setInt(1, idTienda);
            psElimVentas.executeUpdate();
            psElimVentas.close();

            // 4. Eliminar INVENTARIO
            String eliminarInventario = "DELETE FROM INVENTARIO WHERE ID_TIENDA = ?";
            PreparedStatement psInv = conn.prepareStatement(eliminarInventario);
            psInv.setInt(1, idTienda);
            psInv.executeUpdate();
            psInv.close();

            // 5. Eliminar EMPLEADO
            String eliminarEmpleado = "DELETE FROM EMPLEADO WHERE ID_TIENDA = ?";
            PreparedStatement psEmp = conn.prepareStatement(eliminarEmpleado);
            psEmp.setInt(1, idTienda);
            psEmp.executeUpdate();
            psEmp.close();

            // 6. Finalmente, eliminar TIENDA
            String eliminarTienda = "DELETE FROM TIENDA WHERE ID_TIENDA = ?";
            PreparedStatement psTienda = conn.prepareStatement(eliminarTienda);
            psTienda.setInt(1, idTienda);
            psTienda.executeUpdate();
            psTienda.close();

            conn.commit();
            System.out.println("Tienda eliminada correctamente.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = ConexionOracleMaster.getConnection()) {
            String sql = "SELECT * FROM TIENDA";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("ID_TIENDA")));
                    fila.add(rs.getString("REGION_ID"));
                    fila.add(rs.getString("NOMBRE"));
                    fila.add(rs.getString("DIRECCION"));
                    datos.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
    
}
