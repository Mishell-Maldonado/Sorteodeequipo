package negocio;

import java.util.ArrayList;
import java.util.List;
import modelo.SorteoInvalidoException;

public class SorteoLogica {

    // Ahora devuelve una lista de ganadores para la siguiente etapa
    public List<String> simularEtapa(List<String> equipos, String titulo) throws SorteoInvalidoException {
        System.out.println("\n--- Etapa " + titulo + " ---");
        List<String> ganadoresRonda = new ArrayList<>();
        
        // Ejecutamos la recursividad para emparejar
        ejecutarRecursion(equipos, ganadoresRonda);
        
        return ganadoresRonda;
    }

    private void ejecutarRecursion(List<String> equipos, List<String> ganadores) throws SorteoInvalidoException {
        if (equipos.isEmpty()) return; 
        if (equipos.size() < 2) {
            throw new SorteoInvalidoException("Error: Faltan equipos para el sorteo."); 
        }

        // Sacamos pareja
        String e1 = equipos.remove(0);
        String e2 = equipos.remove(0);
        
        // Simulación: El primero avanza a la siguiente fase
        System.out.println("Partido: " + e1 + " vs " + e2);
        ganadores.add(e1);

        // Llamada recursiva 
        ejecutarRecursion(equipos, ganadores);
    }
}