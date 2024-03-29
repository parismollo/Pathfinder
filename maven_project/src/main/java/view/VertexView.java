package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import model.Vertex;
import model.Vertex.Type;

public class VertexView extends JPanel {
    
    private GridView gridView;
    private Vertex vertex;

    public VertexView(GridView gridView, Vertex vertex) {
        this.gridView = gridView;
        this.vertex = vertex;
        setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setMouseListener();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(getBackground() != vertex.getColor())
            setBackground(vertex.getColor());
    }

    private void setMouseListener() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Type state = gridView.getState();
                if(!isStartOrEnd(state))
                    if(vertex.isWall() || vertex.isPath())
                        gridView.setItemToDrag(vertex.getType() == Type.WALL ? Type.PATH : Type.WALL); // WALL OU PATH
                refreshType();
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                gridView.setItemToDrag(Type.END);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                Type dragItem = gridView.getItemToDrag();
                if(!(vertex.isShorterPath()) && dragItem != Type.END && !isStartOrEnd(vertex.getType())) {
                    vertex.setType(dragItem);
                    repaint();
                }  
            }

        });
    }

    public boolean isStartOrEnd(Type type) {
        return type == Type.START || type == Type.END;
    }

    private void refreshType() {
        Type state = gridView.getState(),
             vType = vertex.getType();
        // Condition qui force l'utilisateur à re-poser le départ ou l'arrivée
        // Si elle a été retiré, avant de pouvoir faire une autre action.
        if(gridView.putStartOrEnd() && vType != Type.PATH)
            return;
        if(vertex.isShorterPath())   
            return;
        gridView.setPutStartEnd(vType == Type.START || vType == Type.END);
        vertex.setType(vType == Type.PATH ? state : Type.PATH);
        gridView.setState(vType == Type.PATH ? (gridView.isFirstSteps() ? state : Type.WALL) : vType);              

        if(vType != Type.START && vType != Type.END)
            gridView.nextState(); // Oblige l'utilisateur à commencer par poser une entrée et une sortie        
    }

}
