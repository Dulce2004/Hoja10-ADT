package uvg.edu.gt;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GraphAlgorithm {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("guategrafo.txt"));

        int n = scanner.nextInt(); // Number of cities
        scanner.nextLine(); // Consume newline
        Graph graph = new Graph(n);

        List<String> cities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            cities.add(parts[0]);
            cities.add(parts[1]);
            int distance = Integer.parseInt(parts[2]);
            graph.addEdge(i, i + 1, distance); // Assuming directed graph
        }
        graph.setCities(cities);

        // Perform Floyd's Algorithm
        graph.floydAlgorithm();

        // Interaction loop
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Options:");
            System.out.println("1. Find shortest path between two cities");
            System.out.println("2. Calculate center of the graph");
            System.out.println("3. Modify the graph");
            System.out.println("4. Exit");

            int choice = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("Enter source city:");
                    String source = inputScanner.nextLine();
                    System.out.println("Enter destination city:");
                    String destination = inputScanner.nextLine();
                    int sourceIndex = cities.indexOf(source);
                    int destIndex = cities.indexOf(destination);
                    int distance = graph.getShortestDistance(sourceIndex, destIndex);
                    System.out.println("Shortest distance: " + distance);
                    break;
                case 2:
                    int centerIndex = graph.calculateCenter();
                    System.out.println("Center city: " + cities.get(centerIndex));
                    break;
                case 3:
                    System.out.println("Enter 1 to remove an edge, 2 to add a new edge:");
                    int modifyChoice = inputScanner.nextInt();
                    inputScanner.nextLine(); // Consume newline
                    if (modifyChoice == 1) {
                        System.out.println("Enter source city:");
                        String removeSource = inputScanner.nextLine();
                        System.out.println("Enter destination city:");
                        String removeDest = inputScanner.nextLine();
                        int removeSourceIndex = cities.indexOf(removeSource);
                        int removeDestIndex = cities.indexOf(removeDest);
                        graph.removeEdge(removeSourceIndex, removeDestIndex);
                    } else if (modifyChoice == 2) {
                        System.out.println("Enter source city:");
                        String addSource = inputScanner.nextLine();
                        System.out.println("Enter destination city:");
                        String addDest = inputScanner.nextLine();
                        System.out.println("Enter distance:");
                        int addDistance = inputScanner.nextInt();
                        inputScanner.nextLine(); // Consume newline
                        int addSourceIndex = cities.indexOf(addSource);
                        int addDestIndex = cities.indexOf(addDest);
                        graph.addEdge(addSourceIndex, addDestIndex, addDistance);
                    }
                    graph.floydAlgorithm(); // Recalculate shortest paths
                    break;
                case 4:
                    System.out.println("Exiting program.");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}