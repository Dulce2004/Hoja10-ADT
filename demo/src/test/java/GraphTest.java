import org.junit.Before;
import org.junit.Test;

import com.google.common.graph.Graph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class GraphTest {

    private Graph graph;

    @Before
    public void setUp() {
        graph = new Graph(4);
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 2);
        graph.addEdge(2, 3, 1);
    }

    @Test
    public void testAddEdge() {
        graph.addEdge(1, 3, 5);
        assertEquals(5, graph.getShortestDistance(1, 3));
    }

    @Test
    public void testRemoveEdge() {
        graph.removeEdge(0, 1);
        assertEquals(0, graph.getShortestDistance(0, 1));
    }

    @Test
    public void testFloydAlgorithm() {
        graph.floydAlgorithm();
        assertEquals(3, graph.getShortestDistance(0, 3));
        assertEquals(2, graph.getShortestDistance(1, 3));
    }

    @Test
    public void testCalculateCenter() {
        graph.floydAlgorithm();
        int center = graph.calculateCenter();
        assertEquals(2, center);  // Asumiendo que el nodo 2 es el centro en este caso
    }

    @Test
    public void testSetAndGetCities() {
        List<String> cities = Arrays.asList("City1", "City2", "City3", "City4");
        graph.setCities(cities);
        assertEquals(cities, graph.getCities());
    }

    @Test
    public void testGetShortestDistance() {
        graph.floydAlgorithm();
        assertEquals(3, graph.getShortestDistance(0, 3));
        assertEquals(2, graph.getShortestDistance(1, 3));
    }
}
