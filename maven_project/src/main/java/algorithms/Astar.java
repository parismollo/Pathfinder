package algorithms;


import java.util.PriorityQueue;

import model.Graph;
import model.Vertex;
import model.Vertex.Type;

import java.lang.Math;

public class Astar {
  public static int distances[];
  public static Vertex previous[];

  public static void run(Graph graph) {
    // getStart(), getEnd()
    run(graph, null, null);
  }

  private static int heuristic(Vertex end, Vertex current) {
    // Method to compute "real distance" from neighbor to end vertex:
    return Math.abs(end.getX() - current.getX()) + Math.abs(end.getY() - current.getY());
  }

  public static int[] run(Graph graph, Vertex start, Vertex end) {
    // Create distances and previous array:
    distances = new int[graph.getNbCols() * graph.getNbRows()];
    previous = new Vertex[graph.getNbCols() * graph.getNbRows()];
    
    // Set all distances to infinity and previous to -1:
    for (int i = 0; i < previous.length; i++) {
      distances[i] = Integer.MAX_VALUE;
      previous[i] = null;
    }
    distances[start.getId()] = 0;
    previous[start.getId()] = start;

    // Create PQ:
    PriorityQueue<Tuple> pq = Dijkstra.makeQueue(graph);
    // Loop while not empty:
    while (!pq.isEmpty()) {
      // Get min value in PQ:
      Tuple current = pq.poll();
      // Stop if found end vertex:
      if(current.getVertex().getId() == end.getId()) {
        break;
      }
      // Loop over each neighbor (which not walls):
      for (Vertex neighbor : current.getVertex().getNeighbors(false)) {
        // Get total cost:
        int totalCost = distances[current.getVertex().getId()] + neighbor.getCost();
        // Only update if smaller cost:
        if(totalCost < distances[neighbor.getId()]) {
          distances[neighbor.getId()] = totalCost;
          previous[neighbor.getId()] = current.getVertex();
          // Cost for Astar is based on totalcost + heuristics:
          Dijkstra.decreaseKey(pq, neighbor, totalCost + heuristic(end, neighbor));
        }
      } 
    }

    Dijkstra.setShortPath(previous, start, end);
    return distances;
  }

  public static void main(String[] args) {
    Graph graph = new Graph(5, 5);
    Vertex start = graph.getVertex(0, 0);
    start.setType(Type.START);

    Vertex end = graph.getVertex(3, 3);
    end.setType(Type.END);

    Vertex mid = graph.getVertex(0, 1);
    mid.setType(Type.WALL);
    Vertex mid2 = graph.getVertex(2, 3);
    mid2.setType(Type.WALL);

    System.out.println(graph);
    Astar.run(graph, start, end);
    System.out.println(graph);

  }
}
