package Util;

import java.util.HashMap;
import java.util.Map;

public class TablaDistribuida {

    private static final Map<String, Map<String, String>> nombresFragmentados = new HashMap<>();

    static {
        // Tablas replicadas (materialized views)
        Map<String, String> replicadas = new HashMap<>();
        //replicadas.put("CLIENTE", "CLIENTE");
        replicadas.put("PRODUCTO", "PRODUCTO_REP");
        replicadas.put("PROVEEDOR", "PROVEEDOR_REP");
        nombresFragmentados.put("REPLICADAS", replicadas);

        // Tablas fragmentadas por provincia
        Map<String, String> detalleVenta = new HashMap<>();
        detalleVenta.put("Cotopaxi", "DETALLE_VENTA_COTOPAXI");
        detalleVenta.put("Pichincha", "DETALLE_VENTA_PICHINCHA");
        detalleVenta.put("Tungurahua", "DETALLE_VENTA_TUNGURAHUA");
        detalleVenta.put("Manabi", "DETALLE_VENTA_MANABI");
        detalleVenta.put("Guayas", "DETALLE_VENTA_GUAYAS");
        detalleVenta.put("Esmeraldas", "DETALLE_VENTA_ESMERALDAS");

        Map<String, String> ventas = new HashMap<>();
        ventas.put("Cotopaxi", "VENTAS_COTOPAXI");
        ventas.put("Pichincha", "VENTAS_PICHINCHA");
        ventas.put("Tungurahua", "VENTAS_TUNGURAHUA");
        ventas.put("Manabi", "VENTAS_MANABI");
        ventas.put("Guayas", "VENTAS_GUAYAS");
        ventas.put("Esmeraldas", "VENTAS_ESMERALDAS");

        // Agrega más mapas para INVENTARIO, TIENDA, etc.
        Map <String, String> clientes = new HashMap<>();
        clientes.put("Pichincha", "CLIENTE_PICHINCHA");
        clientes.put("Cotopaxi", "CLIENTE_COTOPAXI");
        clientes.put("Tungurahua", "CLIENTE_TUNGURAHUA");
        clientes.put("Manabi", "CLIENTE_MANABI");
        clientes.put("Guayas", "CLIENTE_GUAYAS");
        clientes.put("Esmeraldas", "CLIENTE_ESMERALDAS");
        clientes.put("Global", "CLIENTE");
        clientes.put("Replica", "CLIENTE_REPLICA");


        nombresFragmentados.put("DETALLE_VENTA", detalleVenta);
        nombresFragmentados.put("VENTAS", ventas);
        nombresFragmentados.put("CLIENTE", clientes);
        // ... otras tablas fragmentadas
    }

    public static String obtenerNombre(String base, String provincia) {
    // Si quieres que tanto MASTER como remotos usen los nombres replicados/fragmentados:
        // Primero verifica si la tabla es replicada:
        if (nombresFragmentados.containsKey("REPLICADAS") && nombresFragmentados.get("REPLICADAS").containsKey(base) && ContextoConexion.getTipoConexion() != ContextoConexion.TipoConexion.MASTER) {
            return nombresFragmentados.get("REPLICADAS").get(base);
        }
        
        // Luego, si es tabla fragmentada, devuelve el fragmento por provincia:
        if (nombresFragmentados.containsKey(base)) {
            Map<String, String> porProvincia = nombresFragmentados.get(base);
            return porProvincia.getOrDefault(provincia, base);
        }
        
        // En caso no aplique, devuelve base
        return base;
    }

}
