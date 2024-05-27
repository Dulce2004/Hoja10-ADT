package uvg.edu.gt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * La clase GraphAlgorithm proporciona el punto de entrada principal para el programa de algoritmo de grafos.
 * Lee los datos de ciudades y distancias desde un archivo, construye un grafo y permite al usuario interactuar con el grafo
 * para encontrar el camino más corto, calcular el centro del grafo y modificar el grafo.
 */
public class GraphAlgorithm {

    /**
     * El método principal que ejecuta el programa de algoritmo de grafos.
     *
     * @param args Argumentos de la línea de comandos.
     * @throws FileNotFoundException Si no se encuentra el archivo de entrada.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("guategrafo.txt"));

        int n = scanner.nextInt(); // Número de ciudades
        scanner.nextLine(); // Consumir el salto de línea
        Graph graph = new Graph(n);

        List<String> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            cities.add(parts[0]);
            cities.add(parts[1]);
            int distance = Integer.parseInt(parts[2]);
            graph.addEdge(i, i + 1, distance); // Suponiendo un grafo dirigido
        }
        graph.setCities(cities);

        // Ejecutar el algoritmo de Floyd
        graph.floydAlgorithm();

        // Bucle de interacción
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Opciones:");
            System.out.println("1. Encontrar el camino más corto entre dos ciudades");
            System.out.println("2. Calcular el centro del grafo");
            System.out.println("3. Modificar el grafo");
            System.out.println("4. Salir");

            int choice = inputScanner.nextInt();
            inputScanner.nextLine(); // Consumir el salto de línea

            switch (choice) {
                case 1:
                    System.out.println("Ingrese la ciudad de origen:");
                    String source = inputScanner.nextLine();
                    System.out.println("Ingrese la ciudad de destino:");
                    String destination = inputScanner.nextLine();
                    int sourceIndex = cities.indexOf(source);
                    int destIndex = cities.indexOf(destination);
                    int distance = graph.getShortestDistance(sourceIndex, destIndex);
                    System.out.println("Distancia más corta: " + distance);
                    break;
                case 2:
                    int centerIndex = graph.calculateCenter();
                    System.out.println("Ciudad central: " + cities.get(centerIndex));
                    break;
                case 3:
                    System.out.println("Ingrese 1 para eliminar una arista, 2 para agregar una nueva arista:");
                    int modifyChoice = inputScanner.nextInt();
                    inputScanner.nextLine(); // Consumir el salto de línea
                    if (modifyChoice == 1) {
                        System.out.println("Ingrese la ciudad de origen:");
                        String removeSource = inputScanner.nextLine();
                        System.out.println("Ingrese la ciudad de destino:");
                        String removeDest = inputScanner.nextLine();
                        int removeSourceIndex = cities.indexOf(removeSource);
                        int removeDestIndex = cities.indexOf(removeDest);
                        graph.removeEdge(removeSourceIndex, removeDestIndex);
                    } else if (modifyChoice == 2) {
                        System.out.println("Ingrese la ciudad de origen:");
                        String addSource = inputScanner.nextLine();
                        System.out.println("Ingrese la ciudad de destino:");
                        String addDest = inputScanner.nextLine();
                        System.out.println("Ingrese la distancia:");
                        int addDistance = inputScanner.nextInt();
                        inputScanner.nextLine(); // Consumir el salto de línea
                        int addSourceIndex = cities.indexOf(addSource);
                        int addDestIndex = cities.indexOf(addDest);
                        graph.addEdge(addSourceIndex, addDestIndex, addDistance);
                    }
                    graph.floydAlgorithm(); // Recalcular los caminos más cortos
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}
