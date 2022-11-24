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
      this.totalCostFrmStart = w;// This represents distance from source + weight + heuristics
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
    return Math.abs(end.getX() - current.getX()) + Math.abs(end.getY() - current.getY());
  }

  public static void run(Graph graph, Vertex start, Vertex end) {

    // 1. Create distances and previous array:
    distances = new int[graph.getNbCols() * graph.getNbRows()];
    previous = new Vertex[graph.getNbCols() * graph.getNbRows()];

    // 2. Set all distances to infinity and previous to -1
    for (int i = 0; i < previous.length; i++) {
      distances[i] = Integer.MAX_VALUE;
      previous[i] = null;
    }
    distances[start.getId()] = 0;
    previous[start.getId()] = start;

    // 3. Create PQ
    PriorityQueue<Tuple> pq = new PriorityQueue<>(new TupleComparator());
    pq.add(new Tuple(start, distances[start.getId()]));

    while (!pq.isEmpty()) {
      Tuple current = pq.poll();
      if(current.vertex.getId() == end.getId()) {
        System.out.println("Found it!");
        // TODO: Return distances, previous?
        break;
      }
      for (Vertex neighbor : current.vertex.getNeighbors(false)) {
        int totalCost = distances[current.vertex.getId()] + neighbor.getCost();
        if(totalCost < distances[neighbor.getId()]) {
          distances[neighbor.getId()] = totalCost;
          // TODO: optimize by updating key instead of adding a new one:
          // BUG: I think there is a bug on the heuristics 
          Tuple newTuple = new Tuple(neighbor, totalCost + heuristic(end, neighbor));
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
    Graph graph = new Graph(4, 4);
    Vertex start = graph.getVertex(0, 0);
    start.setType(Type.START);

    Vertex end = graph.getVertex(2, 2);
    end.setType(Type.END);

    System.out.println(graph);
    Astar.run(graph, start, end);
   
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
    // System.out.println("");
    // for (int d : distances) {
    //   System.out.print(d+" ");
    // }
    // System.out.println("");


    System.out.println(graph);
  }
}
