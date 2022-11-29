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
				if(graph.getStart() == null || graph.getEnd() == null)
					return;
				graph.resetShortPath();
				switch(e.getKeyCode()) {
					case KeyEvent.VK_A:
						Astar.run(graph);
						break;
					case KeyEvent.VK_D:
						// Dijkstra
						Dijkstra.run(graph);
						break;
					case KeyEvent.VK_G:
						Greedy.run(graph);
						break;
					case KeyEvent.VK_B:
						BestGreedy.run(graph);
						break;
					case KeyEvent.VK_C:
					case KeyEvent.VK_R:
					case KeyEvent.VK_DELETE:
						graph.resetShortPath();
						gridView.repaint();
						break;
				}
				gridView.revalidate();
				gridView.repaint();
				System.out.println("Taille du chemin: "+graph.getShorterPathSize());
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
