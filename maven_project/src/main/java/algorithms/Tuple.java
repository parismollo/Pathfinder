package algorithms;

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

}
