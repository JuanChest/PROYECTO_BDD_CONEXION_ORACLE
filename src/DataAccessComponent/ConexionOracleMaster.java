package DataAccessComponent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracleMaster {
    // OPCION 1: utilizando la IP local
    private static final String URL_LOCAL = "jdbc:oracle:thin:@localhost:1521:orcl";

    // OPCION 2: utilizar la IP de Radmin VPN
    private static final String URL_VPN = "jdbc:oracle:thin:@26.5.30.208:1521:orcl";

    // URL por defecto (Configurable)
    private static final String URL = URL_LOCAL;
    // private static final String URL = URL_VPN;

    private static final String USER = "nodo1";
    private static final String PASSWORD = "nodo1";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
