package ch02Questions;

// Chapter 2 Question 15 (b)

/**
 * This program displays a red cross on a white
 * background.
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Number15b extends JPanel {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void paintComponent(Graphics g) {
    super.paintComponent(g);  // Call JPanel's paintComponent method
                              //   to paint the background
    int xCenter = getWidth() / 2;
    int yCenter = getHeight() / 2;
    g.setColor(Color.RED);
    g.fillOval((xCenter - 99), (yCenter - 99), 199, 199);
    g.setColor(Color.WHITE);
    g.fillOval((xCenter - 49), (yCenter - 49), 99, 99);
    g.setColor(Color.RED);
    g.fillOval((xCenter - 24), (yCenter - 24), 49, 49);
  }

  public static void main(String[] args) {
    JFrame window = new JFrame("Red Cross");
    window.setBounds(200, 200, 200, 200);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Number15b panel = new Number15b();
    panel.setBackground(Color.WHITE);
    Container c = window.getContentPane();
    c.add(panel);
    window.setVisible(true);
  }
}

