package Util;

import java.sql.Connection;
import java.sql.SQLException;

import DataAccessComponent.ConexionOracleMaster;
import DataAccessComponent.ConexionOracleRemoto;

public class ConexionFactory {
    public static Connection obtenerConexion() throws SQLException {
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.MASTER) {
            return ConexionOracleMaster.getConnection();
        } else {
            return ConexionOracleRemoto.getConnection();
        }
    }
}
