package uvg.edu.gt;

import java.util.*;

/**
 * La clase Graph representa un grafo dirigido utilizando una matriz de adyacencia para almacenar las distancias entre nodos (ciudades).
 */
class Graph {
    private final int INF = 9999999; // Valor que representa el infinito
    private int[][] adjacencyMatrix; // Matriz de adyacencia que almacena las distancias entre los nodos
    private List<String> cities; // Lista de nombres de las ciudades

    /**
     * Constructor para la clase Graph.
     * 
     * @param size Tamaño del grafo (número de nodos).
     */
    public Graph(int size) {
        adjacencyMatrix = new int[size][size];
        cities = new ArrayList<>();
    }

    /**
     * Agrega una arista al grafo.
     * 
     * @param from Nodo de origen.
     * @param to Nodo de destino.
     * @param distance Distancia entre los nodos.
     */
    public void addEdge(int from, int to, int distance) {
        adjacencyMatrix[from][to] = distance;
    }

    /**
     * Elimina una arista del grafo.
     * 
     * @param from Nodo de origen.
     * @param to Nodo de destino.
     */
    public void removeEdge(int from, int to) {
        adjacencyMatrix[from][to] = 0;
    }

    /**
     * Implementa el algoritmo de Floyd-Warshall para encontrar las distancias más cortas entre todos los pares de nodos en el grafo.
     */
    public void floydAlgorithm() {
        int size = adjacencyMatrix.length;

        for (int k = 0; k < size; k++) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (adjacencyMatrix[i][k] + adjacencyMatrix[k][j] < adjacencyMatrix[i][j]) {
                        adjacencyMatrix[i][j] = adjacencyMatrix[i][k] + adjacencyMatrix[k][j];
                    }
                }
            }
        }
    }

    /**
     * Obtiene la distancia más corta entre dos nodos.
     * 
     * @param from Nodo de origen.
     * @param to Nodo de destino.
     * @return La distancia más corta entre los nodos.
     */
    public int getShortestDistance(int from, int to) {
        return adjacencyMatrix[from][to];
    }

    /**
     * Establece la lista de ciudades del grafo.
     * 
     * @param cities Lista de nombres de las ciudades.
     */
    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    /**
     * Obtiene la lista de ciudades del grafo.
     * 
     * @return La lista de nombres de las ciudades.
     */
    public List<String> getCities() {
        return cities;
    }

    /**
     * Imprime la matriz de adyacencia del grafo.
     */
    public void printAdjacencyMatrix() {
        System.out.println("Matriz de Adyacencia:");
        for (int[] row : adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

    /**
     * Calcula el nodo central del grafo. El nodo central es aquel que tiene la distancia máxima mínima a cualquier otro nodo.
     * 
     * @return El índice del nodo central.
     */
    public int calculateCenter() {
        int size = adjacencyMatrix.length;
        int minDistance = INF;
        int centerNode = -1;

        for (int i = 0; i < size; i++) {
            int maxDistance = 0;
            for (int j = 0; j < size; j++) {
                maxDistance = Math.max(maxDistance, adjacencyMatrix[i][j]);
            }
            if (maxDistance < minDistance) {
                minDistance = maxDistance;
                centerNode = i;
            }
        }

        return centerNode;
    }
}
