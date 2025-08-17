package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.TablaDistribuida;
import Util.ConexionFactory;
import Util.ContextoConexion;
import Util.ContextoModulo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarCliente {

    public static void insertar(int idCliente, int idProvincia, String nombre, String apellido, String cedula, String email, String telefono) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite insertar desde una conexión remota.");
            return;
        }

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String provincia = ContextoModulo.getProvinciaActual();
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE", provincia);
            String sql = "INSERT INTO " + tabla + " (CLIENTE_ID, PROVINCIA_ID, NOMBRE, APELLIDO, CEDULA, EMAIL, TELEFONO) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, idCliente);
                stmt.setInt(2, idProvincia);
                stmt.setString(3, nombre);
                stmt.setString(4, apellido);
                stmt.setString(5, cedula);
                stmt.setString(6, email);
                stmt.setString(7, telefono);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void actualizar(int id, int provincia_id, String nombre, String apellido, String cedula, String email, String telefono) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite actualizar desde una conexión remota.");
            return;
        }

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String provincia = ContextoModulo.getProvinciaActual();
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE", provincia);
            String sql = "UPDATE " + tabla + " SET NOMBRE=?, APELLIDO=?, CEDULA=?, EMAIL=?, TELEFONO=? WHERE CLIENTE_ID=?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, apellido);
                stmt.setString(3, cedula);
                stmt.setString(4, email);
                stmt.setString(5, telefono);
                stmt.setInt(6, id);

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void eliminar(int clienteId, int provincia_id) {
        if (ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            System.out.println("No se permite eliminar desde una conexión remota.");
            return;
        }
        String provincia = ContextoModulo.getProvinciaActual();
        String tabla = TablaDistribuida.obtenerNombre("CLIENTE", provincia);
        String sql = "DELETE FROM " + tabla + " WHERE CLIENTE_ID = ?";
        System.out.println(sql);
        try (Connection conn = ConexionFactory.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            System.out.println(clienteId);
            stmt.setInt(1, clienteId);
            //stmt.setInt(2, provincia_id);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos(String provincia) {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = ConexionFactory.obtenerConexion()) {
            String tabla = TablaDistribuida.obtenerNombre("CLIENTE", provincia);
            System.out.println("Tabla calculada dinámicamente: " + tabla);

            String sql = "SELECT * FROM " + tabla;
            System.out.println("Ejecutando consulta: " + sql);
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("CLIENTE_ID")));
                    fila.add(rs.getString("PROVINCIA_ID"));
                    fila.add(rs.getString("NOMBRE"));
                    fila.add(rs.getString("APELLIDO"));
                    fila.add(rs.getString("CEDULA"));
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
