package Util;

import java.util.HashMap;
import java.util.Map;

public class TablaDistribuida {

    private static final Map<String, String> nombresRemotos = new HashMap<>();

    static {
        // Tablas replicadas (materialized views)
        nombresRemotos.put("CLIENTE", "CLIENTE_REPLICA");
        nombresRemotos.put("PRODUCTO", "PRODUCTO_REPLICA");
        nombresRemotos.put("PROVEEDOR", "PROVEEDOR_REPLICA");

        // Tablas fragmentadas horizontalmente
        nombresRemotos.put("VENTAS", "VENTAS_NORTE");
        nombresRemotos.put("TIENDA", "TIENDA_NORTE");
        nombresRemotos.put("INVENTARIO", "INVENTARIO_NORTE");
        nombresRemotos.put("DETALLE_VENTA", "DETALLE_VENTA_NORTE");
    }

    public static String obtenerNombre(String base) {
        if (ContextoConexion.getTipoConexion() == ContextoConexion.TipoConexion.MASTER) {
            return base;
        } else {
            return nombresRemotos.getOrDefault(base, base);
        }
    }
}
