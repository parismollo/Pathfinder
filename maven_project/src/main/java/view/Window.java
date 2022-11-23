package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;

	public Window(int w, int h)
	{
		this.setMinimumSize(new Dimension(w, h));
		this.setTitle("Path project");
		this.setLayout(new BorderLayout());
		this.setMinimumSize(new Dimension(w, h));
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.requestFocus();

		this.setVisible(true);
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
