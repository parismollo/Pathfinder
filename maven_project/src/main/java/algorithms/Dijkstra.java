package algorithms;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import model.Graph;
import model.Vertex;
import model.Vertex.Type;

public class Dijkstra {
    private static int distances[];
    private static Vertex previous[];

    private static class TupleComparator implements Comparator<Tuple> {
        @Override
        public int compare(Tuple t1, Tuple t2) {
            // Renvoie -1 si distance de t1 < distance de t2 sinon 1
            return t1.getDistance() < t2.getDistance()  ? -1 : 1;
        }
    }

    static PriorityQueue<Tuple> makeQueue(Graph graph) {
        PriorityQueue<Tuple> q = new PriorityQueue<Tuple>(new TupleComparator());
        for(Vertex[] ve : graph.getVertices())
            for(Vertex v : ve)
                q.add(new Tuple(v, v.isStart() ? 0 : Integer.MAX_VALUE));
        return q;
    }

    static void decreaseKey(PriorityQueue<Tuple> queue, Vertex v, int distance) {
        Tuple editTuple = null;
        for(Tuple t : queue) {
            if(t.getVertex() == v) { // Attention ici. Peut etre faut il utiliser equals ?
                editTuple = t;
                break;
            }
        }
        if(editTuple == null)
            return;
        editTuple.setDistance(distance);
        // On remove puis re-ajoute le tuple modifié
        queue.remove(editTuple);
        queue.add(editTuple);
    }

    static void setShortPath(Vertex[] previous, Vertex start, Vertex end) {
        Vertex next = end;
        
        while (next.getId() != start.getId()) {
            if(!next.isEnd() && !next.isStart())
                next.setType(Type.SHTPATH);
          next = previous[next.getId()];
        }
    }

    public static int[] run(Graph graph) {
        return run(graph, graph.getStart(), graph.getEnd());
    }

    // Return Graph ? On clone le graphe puis on le modifie
    public static int[] run(Graph graph, Vertex start, Vertex end) {
        distances = new int[graph.getNbCols() * graph.getNbRows()];
        previous = new Vertex[graph.getNbCols() * graph.getNbRows()];

        // On met toutes les distances à infini et le tableau de parent à null
        for (int i = 0; i < previous.length; i++) {
            distances[i] = Integer.MAX_VALUE;
            previous[i] = null;
        }
        distances[start.getId()] = 0;
        previous[start.getId()] = start;

        // File de priorité
        PriorityQueue<Tuple> queue = makeQueue(graph);
        // On remplit entièrement le tableau de distance
        while (!queue.isEmpty()) {
            Tuple current = queue.poll();
            for (Vertex neighbor : current.getVertex().getNeighbors(false)) {
                int totalCost = distances[current.getVertex().getId()];
                if(totalCost != Integer.MAX_VALUE) // Corrige un bug. Si d[u] = MAX_VALUE alors d[u]+1 = -MAX_VALUE
                    totalCost = distances[current.getVertex().getId()] + neighbor.getCost();
                if(distances[neighbor.getId()] > totalCost) {
                    distances[neighbor.getId()] = totalCost;
                    previous[neighbor.getId()] = current.getVertex();
                    decreaseKey(queue, neighbor, totalCost);
                }
            } 
        }

        setShortPath(previous, start, end);

        return distances;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(4, 4);
        Vertex start = graph.getVertex(0, 0);
        start.setType(Type.START);
    
        Vertex end = graph.getVertex(2, 2);
        end.setType(Type.END);
    
        Vertex mid = graph.getVertex(1, 1);
        mid.setType(Type.WALL);
        graph.getVertex(0, 1).setType(Type.WALL);

        System.out.println(graph);

        int distances[] = run(graph, start, end);
        System.out.println(Arrays.toString(distances));
    
        System.out.println(graph);
    }

}
