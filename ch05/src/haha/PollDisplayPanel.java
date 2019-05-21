package haha;

/**
 * A PollDisplayPanel holds the vote counts and
 * displays the numbers and the pie chart for
 * the current vote counts.
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class PollDisplayPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int count1, count2, count3;
	private String name1, name2, name3;
	// Declare the int fields count1, count2, count3:

	PollDisplayPanel(String nm1, String nm2, String nm3) { // Constructor
    setBackground(Color.WHITE);
    name1 = nm1;
    name2 = nm2;
    name3 = nm3;
    count1 = 0;   // optional
    count2 = 0;   // optional
    count3 = 0;   // optional
  }

  // Increments count1
  public void vote1()
  {
	  count1++;
  }

  // Increments count2
  public void vote2()
  {
	  count2++;
  }

  // Increments count3
  public void vote3()
  {
	  Random rand1 = new Random();
	  int rand = rand1.nextInt(2) + 1;
	  switch (rand) {
	  case 1:
		  count1++;
		  break;
	  case 2:
		  count2++;
		  break;
	  }
  }

  // Returns a string representation of this object
  public String toString()
  {
    return   name1 + ": " + count1 + " " +

        name2 + ": " + count2 + " " +

        name3 + ": " + count3 ;
	  
  }

  // Redefines JPanel's paintComponent to draw this pie chart
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    int w = getWidth();
    int h = getHeight();
    int x = w/2;
    int y = h/2;
    int r = Math.min(w, h) / 4;
    drawPieChart(g, x, y, r);
    drawLegend(g, x, y, r);
  }

  // Draws the pie chart.
  // To avoid gaps in the picture, the following algorithm is used:
  // 1. set fromDegree to 0;
  // 2. draw the red sector and increment fromDegree by its size
  // 3. draw the green sector and increment fromDegree by its size
  // 4. set the size of the blue sector to the remaining
  //    area, 360 - fromDegree, but not less than 0:
  //      degrees = Math.max(360 - fromDegree, 0);
  //    (Occasionally, due to rounding errors, fromDegree may become 361,
  //    for example, count1 = 5, count2 = 11, count3 = 0.)
  private void drawPieChart(Graphics g, int x, int y, int r)
  {
    int total = count1 + count2 + count3;
    int fromDegree = 0;

    if (total > 0)
    {
      int degrees;
      g.setColor(Color.RED);
      degrees = countToDegrees(count1, total);
      drawSector(g, x, y, r, fromDegree, degrees);
      fromDegree += degrees;
      
      g.setColor(Color.GREEN);
      degrees = countToDegrees(count2, total);
      drawSector(g, x, y, r, fromDegree, degrees);
      fromDegree += degrees;
      
      g.setColor(Color.BLUE);
      degrees = countToDegrees(count3, total);
      degrees = Math.max(360 - fromDegree, 0);
      drawSector(g, x, y, r, fromDegree, degrees);
      fromDegree += degrees;
    }
    else
    {
      g.setColor(Color.LIGHT_GRAY);
      drawSector(g, x, y, r, fromDegree, 360);
    }
  }

// Draws the vote counts and the corresponding color squares.
  private void drawLegend(Graphics g, int x, int y, int r)
  {
    // Display the counts:
    y += (r + 20);
    g.setColor(Color.BLACK);
    
    int a = count1;
    String b = String.valueOf(a);
    int c = count2;
    String d = String.valueOf(c);
    int e = count3;
    String f  = String.valueOf(e);

    g.drawString( b , x - r, y);

    g.drawString( d , x, y);

    g.drawString( f , x + r, y);


    // Display the color squares:
    y += 5;
    x -= 2;
    g.setColor(Color.RED);
    g.fillRect(x - r, y, 10, 10);
    g.setColor(Color.GREEN);
    g.fillRect(x, y, 10, 10);
    g.setColor(Color.BLUE);
    g.fillRect(x + r, y, 10, 10);
  }

  // Returns the number of degrees in a pie slice that
  // corresponds to count / total, rounded to the nearest integer.
  private int countToDegrees(int count, int total)
  {
	int t = (int)((double)count / total * 360 + 0/5);
	if (t >= 361) {
		t = 360;
	}
    return t;
  }

  // Draws a sector, centered at x, y, of radius r,
  // of angle measure degrees, starting at fromDegree.
  private void drawSector(Graphics g, int x, int y, int r, int fromDegree, int degrees)
  {
    if (degrees > 359)
      g.fillOval(x - r, y - r, 2 * r, 2 * r);
    else
      g.fillArc(x - r, y - r, 2 * r, 2 * r, fromDegree, degrees);
  }
}
