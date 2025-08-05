package Util;

public class ContextoModulo {
    private static String provinciaActual;

    public static void setProvinciaActual(String provincia) {
        provinciaActual = provincia;
    }

    public static String getProvinciaActual() {
        return provinciaActual;
    }
}
