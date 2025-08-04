package DataAccessComponent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Util.ContextoConexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdministrarAuditoria {

    // Método para obtener conexión dependiendo del tipo de conexión
    private static Connection obtenerConexion() throws SQLException {
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.MASTER) {
            return ConexionOracleMaster.getConnection();
        } else {
            return ConexionOracleRemoto.getConnection();
        }
    }

    public static ObservableList<ObservableList<String>> obtenerTodos() {
        ObservableList<ObservableList<String>> datos = FXCollections.observableArrayList();

        try (Connection conn = obtenerConexion()) {
            String sql = "SELECT * FROM AUDITORIA_OPERACIONES";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    ObservableList<String> fila = FXCollections.observableArrayList();
                    fila.add(String.valueOf(rs.getInt("ID_AUDIT")));
                    fila.add(rs.getString("TABLA"));
                    fila.add(rs.getString("OPERACION"));
                    fila.add(rs.getString("USUARIO"));
                    fila.add(rs.getString("FECHA"));
                    fila.add(rs.getString("INFO"));

                    datos.add(fila);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datos;
    }
}
