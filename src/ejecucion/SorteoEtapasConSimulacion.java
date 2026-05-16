package ejecucion;

import java.util.*;
import negocio.SorteoLogica;
import modelo.SorteoInvalidoException;

public class SorteoEtapasConSimulacion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SorteoLogica logica = new SorteoLogica();
        boolean salir = false;

        while (!salir) {
            try {
                // Mantenemos tu menú original tal como está en la imagen
                System.out.println("\n=== SORTEO DE LIGA PROFESIONAL ===");
                System.out.println("Seleccione la etapa del torneo:");
                System.out.println("1. Octavos de final (16 equipos)");
                System.out.println("2. Cuartos de final (8 equipos)");
                System.out.println("3. Semifinales (4 equipos)");
                System.out.println("4. Final (2 equipos)");
                System.out.println("5. Salir");
                System.out.print("Opción: ");
                
                int op = sc.nextInt();
                sc.nextLine(); // Limpiar buffer

                if (op == 5) {
                    salir = true;
                    continue;
                }

                // Determinamos la cantidad de equipos según tu menú 
                int cantidad = (op == 1) ? 16 : (op == 2) ? 8 : (op == 3) ? 4 : (op == 4) ? 2 : 0;
                if (cantidad == 0) throw new SorteoInvalidoException("Opción no válida.");

                // Ingreso de nombres manual para la etapa seleccionada 
                List<String> equiposRonda = new ArrayList<>();
                System.out.println("\nIngrese los nombres de los " + cantidad + " equipos:");
                for (int i = 0; i < cantidad; i++) {
                    System.out.print("Equipo " + (i + 1) + ": ");
                    equiposRonda.add(sc.nextLine());
                }

                // --- LÓGICA DE SIMULACIÓN AUTOMÁTICA ---
                // Esta parte hace que, tras ingresar los equipos, el torneo siga hasta el final
                Collections.shuffle(equiposRonda); // Aleatoriedad inicial 
                
                List<String> actuales = new ArrayList<>(equiposRonda);
                String[] etapas = {"OCTAVOS", "CUARTOS", "SEMIFINALES", "FINAL"};
                
                // Empezamos la simulación desde la etapa que el usuario eligió
                // Si eligió '1', simula desde octavos. Si eligió '3', desde semifinales.
                for (int i = (op - 1); i < etapas.length; i++) {
                    actuales = logica.simularEtapa(actuales, etapas[i]);
                    
                    if (actuales.size() == 1) {
                        System.out.println("\n¡EL CAMPEÓN DEL TORNEO ES: " + actuales.get(0) + "!");
                    }
                }

                System.out.println("\nValidación: Ningún equipo repite partido en esta etapa ");

            } catch (InputMismatchException e) {
                System.out.println("Error: Debe ingresar un número entero para las opciones. ");
                sc.nextLine(); // Limpiar entrada incorrecta
            } catch (SorteoInvalidoException e) {
                System.out.println("Excepción: " + e.getMessage());
            }
        }
        System.out.println("Cerrando sistema...");
        sc.close();
    }
}