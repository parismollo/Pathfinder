package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import algorithms.*;
import model.Graph;

public class Window extends JFrame
{
	private GridView gridView;

	public Window(int w, int h)
	{
		System.out.println("\u001b[32mProjet Path\u001b[0m\nPour lancer un algortihme, appuyer sur les touches suivantes:\n\u001b[31mA\u001b[0m - Astart\n\u001b[31mD\u001b[0m - Dijkstra\n\u001b[31mG\u001b[0m - Greedy\n\u001b[36mEspace\u001b[0m - efface contenu grille");

		this.setMinimumSize(new Dimension(w, h));
		this.setTitle("Path project");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(w, h));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		setGridView(new Graph(20, 20));

		setKeyListener();

		this.setVisible(true);
	}

	public void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				if(gridView == null)
					return;
				Graph graph = gridView.getGraph();
				if(graph.getStart() == null || graph.getEnd() == null) {
					System.out.println("\u001b[33mAttention!\u001b[0m Aucun sommet de départ ou arrivé n'a été détecté");
					return;
				}
					
				graph.resetShortPath();
				switch(e.getKeyCode()) {
					case KeyEvent.VK_A:
						System.out.println("Choisie: \u001b[31mAStar\u001b[0m");
						Astar.run(graph);
						break;
					case KeyEvent.VK_D:
						System.out.println("Choisie: \u001b[31mDijkstra\u001b[0m");
						Dijkstra.run(graph);
						break;
					case KeyEvent.VK_G:
						System.out.println("Choisie: \u001b[31mGreedy\u001b[0m");
						Greedy.run(graph);
						break;
				}
				gridView.revalidate();
				gridView.repaint();
			}
		});
		this.requestFocus();
	}

	public void setGridView(Graph graph) {
		this.getContentPane().removeAll();
		this.setResizable(true);
		this.gridView = new GridView(graph);
		this.getContentPane().add(gridView);
		revalidate();
		repaint();
	}

    public void setupPan(JPanel pan) {
		pan.setLayout(new GridLayout());
    	Dimension screen = this.getSize();
        int w = 1920, h = 1010;
        int top = ((int)screen.getHeight() * 170) / h;
        int left = ((int)screen.getWidth() * 500) / w;
        pan.setBorder(new EmptyBorder(top, left, top, left));
	}

	public void quit() {
		this.dispose();
		System.exit(0);
	}

}
