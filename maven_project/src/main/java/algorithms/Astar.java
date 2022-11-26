package algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

import model.Graph;
import model.Vertex;
import model.Vertex.Type;

import java.lang.Math;

public class Astar {
  public static int distances[];
  public static Vertex previous[];

  private static class Tuple {
    private Vertex vertex;
    private int totalCostFrmStart;
    
    private Tuple(Vertex v, int w) {
      this.vertex = v;
      // This represents distance from source + weight + heuristics:
      this.totalCostFrmStart = w;
    }

    public String toString() {
      return String.format("[id: %d, distance:%d]",this.vertex.getId(), this.totalCostFrmStart);
    }
  }

  private static class TupleComparator implements Comparator<Tuple> {
    @Override
    public int compare(Tuple arg0, Tuple arg1) {
      // returns a negative integer, zero, or a positive integer
      // as the first argument is less than, equal to, or greater than the second.
      return arg0.totalCostFrmStart < arg1.totalCostFrmStart  ? -1 : 1;
    }
  }

  private static int heuristic(Vertex end, Vertex current) {
    // Method to compute "real distance" from neighbor to end vertex:
    return Math.abs(end.getX() - current.getX()) + Math.abs(end.getY() - current.getY());
  }

  public static void run(Graph graph, Vertex start, Vertex end) {

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
    PriorityQueue<Tuple> pq = new PriorityQueue<>(new TupleComparator());
    pq.add(new Tuple(start, distances[start.getId()]));
    // Loop while not empty:
    while (!pq.isEmpty()) {
      // Get min value in PQ:
      Tuple current = pq.poll();
      // Stop if found end vertex:
      if(current.vertex.getId() == end.getId()) {
        System.out.println("Found it!");
        // TODO: Return distances, previous?
        break;
      }
      // Loop over each neighbor (which not walls):
      for (Vertex neighbor : current.vertex.getNeighbors(false)) {
        // Get total cost:
        int totalCost = distances[current.vertex.getId()] + neighbor.getCost();
        // Only update if smaller cost:
        if(totalCost < distances[neighbor.getId()]) {
          distances[neighbor.getId()] = totalCost;
          // Updates PQ
          Tuple newTuple = new Tuple(neighbor, totalCost + heuristic(end, neighbor));
          // TODO: optimize by updating key instead of adding a new one:
          pq.add(newTuple);
          previous[neighbor.getId()] = current.vertex;
          // String sdebug = String.format("[current vertex:%d, weight:%d, neighbor: %d, nghb weight: %d, nghb_heuristic: %d]  ", current.vertex.getId(), current.vertex.getCost(), neighbor.getId(), neighbor.getCost(), heuristic(end, neighbor));
          // System.out.print(sdebug);
          // System.out.println("PQ: "+Arrays.toString(pq.toArray()));
        }
      } 
    }
  }

  public static void main(String[] args) {
    // TODO: organize testing and run method
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
   
    ////////// Hey Paris. Maybe you can look Dijkstra.java

    //////////////// This is the setShortPath defined in Dijkstra.java
    ArrayList<Vertex> path = new ArrayList<>();
    Vertex next = end;
    while (next.getId() != start.getId()) {
      path.add(next);
      next = previous[next.getId()];
    }

    path.add(start);
    Collections.reverse(path);
    System.out.println("PATH: "+path+"\n\n");

    for (Vertex vertex : path) {
      if(vertex.getType() != Type.END && vertex.getType() != Type.START) {
        vertex.setType(Type.SHTPATH);
      }
    }
    System.out.println(graph);
  }
}
