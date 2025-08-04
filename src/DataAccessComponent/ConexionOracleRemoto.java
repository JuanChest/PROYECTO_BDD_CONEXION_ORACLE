package DataAccessComponent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracleRemoto {
    // OPCION 1: utilizando la IP local
    private static final String URL_LOCAL = "jdbc:oracle:thin:@localhost:1521:orcl";

    // OPCION 2: utilizar la IP de Radmin VPN
    private static final String URL_VPN = "jdbc:oracle:thin:@26.1.77.168:1521:orcl";

    // URL por defecto (Configurable)
    private static final String URL = URL_LOCAL;
    // private static final String URL = URL_VPN;

    private static final String USER = "tiendasNorte";
    private static final String PASSWORD = "tiendasnorte";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
