package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.ConexionFactory;
import Util.ContextoConexion;
import Util.ContextoModulo;
import Util.TablaDistribuida;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarTienda {

    public static void insertar(int tiendaId, int regionId, String nombre, String direccion) {
        String provincia = ContextoModulo.getProvinciaActual();
        String tabla = TablaDistribuida.obtenerNombre("TIENDA", provincia);
        String sql = "INSERT INTO " + tabla + " (ID_TIENDA, REGION_ID, NOMBRE, DIRECCION) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tiendaId);
            stmt.setInt(2, regionId);
            stmt.setString(3, nombre);
            stmt.setString(4, direccion);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int tiendaId, int regionId, String nombre, String direccion) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite actualizar desde una conexión remota.");
            return;
        }

        String provincia = ContextoModulo.getProvinciaActual();
        String tabla = TablaDistribuida.obtenerNombre("TIENDA", provincia);
        String sql = "UPDATE " + tabla + " SET REGION_ID = ?, NOMBRE = ?, DIRECCION = ? WHERE ID_TIENDA = ?";

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, regionId);
            stmt.setString(2, nombre);
            stmt.setString(3, direccion);
            stmt.setInt(4, tiendaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void eliminar(int tiendaId) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite eliminar desde una conexión remota.");
            return;
        }

        String provincia = ContextoModulo.getProvinciaActual();
        String tablaTienda = TablaDistribuida.obtenerNombre("TIENDA", provincia);
        String sql = "DELETE FROM " + tablaTienda + " WHERE ID_TIENDA = ?";

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, tiendaId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos(String provincia) {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();
        String tabla = TablaDistribuida.obtenerNombre("TIENDA", provincia);
        String sql = "SELECT * FROM " + tabla;

        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ObservableList<String> fila = FXCollections.observableArrayList();
                fila.add(String.valueOf(rs.getInt("ID_TIENDA")));
                fila.add(String.valueOf(rs.getInt("REGION_ID")));
                fila.add(rs.getString("NOMBRE"));
                fila.add(rs.getString("DIRECCION"));
                datos.add(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
