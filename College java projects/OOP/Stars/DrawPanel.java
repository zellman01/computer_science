import javax.swing.JPanel;
import java.util.Vector;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DrawPanel extends JPanel implements DrawPanelSize {
	// III
	private Vector<LivingObject> livingObjects;
	
	public DrawPanel(Vector<LivingObject> livingObjects) {
		this.livingObjects = livingObjects;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		for (LivingObject lo : livingObjects) {
			lo.draw((Graphics2D)g);
		}
	}
	
	public double getPanelWidth() {
		return getSize().getWidth();
	}
	
	public double getPanelHeight() {
		return getSize().getHeight();
	}
}