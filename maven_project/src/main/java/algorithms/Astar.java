package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import model.Graph;
import model.Vertex;

import java.lang.Math;

public class Astar {
  public static int distances[];
  public static int previous[];

  private static class Tuple {
    private Vertex vertex;
    private int weight;
    
    private Tuple(Vertex v, int w) {
      this.vertex = v;
      this.weight = w;
    }
  }

  private static class TupleComparator implements Comparator<Tuple> {
    @Override
    public int compare(Tuple arg0, Tuple arg1) {
      // returns a negative integer, zero, or a positive integer
      // as the first argument is less than, equal to, or greater than the second.
      return arg0.weight < arg1.weight  ? -1 : 1;
    }
  }

  private static int heuristic(Vertex end, Vertex current) {
    return Math.abs(end.getX() - current.getX()) + Math.abs(end.getY() - current.getY());
  }

  public static void run(Graph graph, Vertex start, Vertex end) {
    distances = new int[graph.getNbCols() * graph.getNbRows()];
    previous = new int[graph.getNbCols() * graph.getNbRows()];

    for (int i = 0; i < previous.length; i++) {
      distances[i] = Integer.MAX_VALUE;
      previous[i] = -1;
    }
    distances[start.getId()] = 0;
    previous[start.getId()] = start.getId();

    PriorityQueue<Tuple> pq = new PriorityQueue<>(new TupleComparator());
    pq.add(new Tuple(start, distances[start.getId()]));

    while (!pq.isEmpty()) {
      Tuple current = pq.poll();
      // System.out.println("Vertex: "+current+" cost: " + current.weight);

      if(current.vertex.getId() == end.getId()) {
        // System.out.println("Found it!");
        // TODO: Return distances, previous?
        break;
      }
      for (Vertex neighbor : current.vertex.getNeighbors(false)) {
        int totalCost = distances[current.vertex.getId()] + neighbor.getCost();
        if(totalCost < distances[neighbor.getId()]) {
          distances[neighbor.getId()] = totalCost;
          // TODO: optimize by updating key instead of adding a new one:
          Tuple newTuple = new Tuple(neighbor, totalCost + heuristic(end, neighbor));
          pq.add(newTuple);
          previous[neighbor.getId()] = current.vertex.getId();
        }
      } 
    }
  }

  public static void main(String[] args) {
    Graph graph = new Graph(3, 3);
    Astar.run(graph, graph.getVertex(0, 0), graph.getVertex(2, 2));
    for (int p : previous) {
      System.out.print(p+" ");
    }
    for (int d : distances) {
      System.out.print(d+" ");
    }
  }
}
