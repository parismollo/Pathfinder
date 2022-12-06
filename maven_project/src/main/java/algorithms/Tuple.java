package algorithms;

import java.util.Comparator;

import model.Vertex;

public class Tuple {
    private Vertex vertex;
    private int distance;

    public Tuple(Vertex v, int w) {
        this.vertex = v;
        this.distance = w; // distance en partant de la source
    }
    
    public String toString() {
        return String.format("[id: %d, distance:%d]",this.vertex.getId(), this.distance);
    }

    public Vertex getVertex() {
        return vertex;
    }

    public void setVertex(Vertex vertex) {
        this.vertex = vertex;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


    public static class TupleComparator implements Comparator<Tuple> {
        @Override
        public int compare(Tuple t1, Tuple t2) {
            // Renvoie -1 si distance de t1 < distance de t2 sinon 1
            return t1.getDistance() < t2.getDistance()  ? -1 : 1;
        }
    }

}
