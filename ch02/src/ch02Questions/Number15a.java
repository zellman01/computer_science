package ch02Questions;

// Chapter 2 Question 15 (a)

/**
 * This program displays a red cross on a white
 * background.
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Number15a extends JPanel {
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
    g.fillRect((xCenter - 5), yCenter, 10, 60);
    g.fillRect((xCenter - 5), (yCenter - 60), 10, 60);
    g.fillRect((xCenter - 60), (yCenter - 5), 60, 10);
    g.fillRect(xCenter, (yCenter - 5), 60, 10);
  }

  public static void main(String[] args) {
    JFrame window = new JFrame("Red Cross");
    window.setBounds(200, 200, 200, 200);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Number15a panel = new Number15a();
    panel.setBackground(Color.WHITE);
    Container c = window.getContentPane();
    c.add(panel);
    window.setVisible(true);
  }
}

