package uvg.edu.gt;

import java.util.*;

class Graph {
    private final int INF = 9999999;
    private int[][] adjacencyMatrix;
    private List<String> cities;

    public Graph(int size) {
        adjacencyMatrix = new int[size][size];
        cities = new ArrayList<>();
    }

    public void addEdge(int from, int to, int distance) {
        adjacencyMatrix[from][to] = distance;
    }

    public void removeEdge(int from, int to) {
        adjacencyMatrix[from][to] = 0;
    }

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

    public int getShortestDistance(int from, int to) {
        return adjacencyMatrix[from][to];
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getCities() {
        return cities;
    }

    public void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int[] row : adjacencyMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }

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