package view;

import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Graph;
import model.Vertex;
import model.Vertex.Type;

public class GridView extends JPanel {
 
    private Graph graph;

    private VertexView verticesView[][];
    private Vertex.Type state = Type.START; // Time to put a Wall or start, end points

    private boolean firstSteps = true, putStartEnd;
    private Type itemToDrag = Type.END; // END par défaut. Si END, on considère qu'il n'y a rien.

    public GridView(Graph graph) {
        this.graph = graph;
        this.setLayout(new GridLayout(graph.getNbRows(), graph.getNbCols()));
        setAndAddVerticesView();
    }

    private void setAndAddVerticesView() {
        this.removeAll();
        Vertex vertices[][] = graph.getVertices();
        verticesView = new VertexView[graph.getNbRows()][graph.getNbCols()];
        for(int j=0;j<vertices.length;j++){
            for(int i=0;i<vertices[0].length;i++) {
                verticesView[j][i] = new VertexView(this, vertices[j][i]);
                this.add(verticesView[j][i]);
            }
        }
    }

    public Type getState() {
        return state;
    }

    public void nextState() {
        if(!firstSteps)
            return;
        switch(state) {
            case START:
                state = Type.END;
                break;
            case END:
                state = Type.WALL;
                firstSteps = false;
                break;
            default:
                break;
        }
    }

    public boolean isFirstSteps() {
        return firstSteps;
    }

    public void setPutStartEnd(boolean value) {
        this.putStartEnd = value;
    }

    public boolean putStartOrEnd() {
        return putStartEnd;
    }

    public void setState(Type state) {
        this.state = state;
    }

    public Graph getGraph() {
        return this.graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Type getItemToDrag() {
        return itemToDrag;
    }

    public void setItemToDrag(Type item) {
        this.itemToDrag = item;
    }

}
