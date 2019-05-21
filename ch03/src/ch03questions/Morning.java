/**
 * This program displays a disk that moves from one side of the screen to the other side of
 * the screen representing the sun.
 * @author Zach Wellman
 * 10/17/2017
 */

package ch03questions;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Morning extends JPanel
                  implements ActionListener {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//@SuppressWarnings("unused")
	private int skyColor = 1;
	private EasySound rooster, cow;

  public Morning() {
	rooster = new EasySound("roost.wav");
	cow = new EasySound("Cow.wav");
    Timer clock = new Timer(5000, this);
    clock.start();
  }

  public void paintComponent(Graphics g) {
    Color sky;
    if (skyColor == 1) sky = Color.	BLACK;
    else sky = Color.WHITE;
    setBackground(sky);
    super.paintComponent(g);
  }

  public void actionPerformed(ActionEvent e) {
	  /**
	   * This will play a sound clip and change what the background color is.
	   */
    skyColor = -skyColor;
    switch (skyColor) {
    case 1:
    	repaint();
    	rooster.play();
    	break;
    case -1:
    	repaint();
    	cow.play();
    	break;
    default:
    	System.out.println("The program has had an unexpected error. Skycolor is set to \"" + skyColor + "\".");
    	System.exit(1);
    	break;
    }
  }

  public static void main(String [] args) {
    JFrame w = new JFrame("Moving Disk");
    w.setSize(300, 150);

    Container c = w.getContentPane();
    c.add(new Morning());

    w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    w.setBackground(Color.WHITE);
    w.setResizable(false);
    w.setVisible(true);
  }
}

