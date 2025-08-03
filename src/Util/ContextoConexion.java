package Util;

public class ContextoConexion {
    public enum TipoConexion {
        MASTER,
        REMOTO
    }

    private static TipoConexion tipoConexion;

    public static void setTipoConexion(TipoConexion tipo) {
        tipoConexion = tipo;
    }

    public static TipoConexion getTipoConexion() {
        return tipoConexion;
    }
}
