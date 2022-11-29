package algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import model.Graph;
import model.Vertex;
import model.Vertex.Type;


import java.lang.Math;

public class Greedy2 {
    private static int distances[];
    private static Vertex previous[];
    //http://ijadis.org/index.php/IJADIS/article/view/greedy-a-star-and-dijkstras-algorithms-in-finding-shortest-path
    //"Greedy est rapide mais ne trouve pas toujours la solution optimale"

    //Math.abs(x1 - x2) + Math.abs(y1 - y2) pour avoir distance "heuristic"/"à vole d'oiseau" entre (x1,y1), ici 
    //là où on en est dans algo, donc le sommet courant, et l'arrivée. Et Greedy va trier prochains sommets à 
    //explorer en commançant par ceux ayant la distance heuristic à l'arrivée minimale.

    //https://www.redblobgames.com/pathfinding/a-star/introduction.html

    //Commenter en écrivant fct
    private static class TupleComparator implements Comparator<Tuple> {
        @Override
        public int compare(Tuple t1, Tuple t2) {
            // Renvoie -1 si distance de t1 < distance de t2 sinon 1
            return t1.getDistance() < t2.getDistance()  ? -1 : 1;
        }
    }

    private static int distAtoB(Vertex a, Vertex b){
        return Math.abs(a.getX()-b.getX()) + Math.abs(a.getY() - b.getY());
    }

    private static void setShortestPath(Vertex[] previous, Vertex start, Vertex end) {
        Vertex next = previous[end.getId()];
        System.out.println("/n/n");
        while(!next.isStart()){//S'arrête si erreur ou si on arrive à start car previous[start.getId()] vaut null
            System.out.println(next.getId());
            next.setType(Type.SHTPATH);
            next = previous[next.getId()];
        }
    }

     public static void run(Graph graph, Vertex start, Vertex end) {
        distances = new int[graph.getNbCols() * graph.getNbRows()];
        previous = new Vertex[graph.getNbCols() * graph.getNbRows()];

        //On mets dans le tableau distance la distance entre chaque sommet qui n'est pas un mur et le point d'arrivée
        //Si c'est un mur on considère la distance comme infinie
        for(Vertex[] ve : graph.getVertices()){
            for(Vertex v : ve){
                if(!v.isWall()){
                    distances[v.getId()] = distAtoB(v, end);
                } else {
                    distances[v.getId()] = Integer.MAX_VALUE;
                }
                previous[v.getId()] = null;
            }
        }
        //previous[start.getId()] = start;

        PriorityQueue<Tuple> queue = new PriorityQueue<Tuple>(new TupleComparator()); 
        queue.add(new Tuple(start, Integer.MAX_VALUE));       
        //Vertex prev = start;

        while(!queue.isEmpty()){
            Vertex current = queue.poll().getVertex();
            if(current.isEnd())
                break;
            //previous[current.getId()] = prev;
            //prev = current;
            
            //Il y aura cas à gérer comme le cas ou pas ne voisins qui ne sont pas des murs -> renvoie impossible

            for(Vertex neighbor : current.getNeighbors(false)){//Tous les voisins de current qui ne sont pas des murs 
                if(previous[neighbor.getId()] == null){//sont pris en compte et ajoutés dans queue avec current pour precedent
                    queue.add(new Tuple(neighbor, distAtoB(neighbor, end)));
                    System.out.println(neighbor.getId());
                    previous[neighbor.getId()] = current;
                }
            }
        }
        setShortestPath(previous, start, end);
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

        run(graph, start, end);
    
        System.out.println(graph);
    }
}

