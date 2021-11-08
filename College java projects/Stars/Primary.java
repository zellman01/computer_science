import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import java.util.Random;
import java.awt.BorderLayout;
import javax.swing.Timer;
import javax.swing.JButton;

public class Primary extends JFrame implements ActionListener, LifeEventListener {
	private Vector<LivingObject> livingObjects;
	private Timer updateFire;
	private JButton addLivingObject;
	DrawPanel dp;
	public static final Random rand = new Random();
	
	public Primary() {
		JPanel buttonPanel = new JPanel();
		livingObjects = new Vector<LivingObject>();
		updateFire = new Timer(1000, this);
		updateFire.setActionCommand("UPDATE");
		addLivingObject = createButton("Add living object", "CREATE", this, "Creates a new LivingObject");
		dp = new DrawPanel(livingObjects);
		
		buttonPanel.add(addLivingObject);
		
		add(dp);
		add(buttonPanel, BorderLayout.SOUTH);
		updateFire.start();
		
		showJFrame();
	}
	
	private void showJFrame() {
		Toolkit tk;
		Dimension d;
		
		setTitle("Stars Project");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/2, d.height/2);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	private JButton createButton(String label, String cmd, ActionListener listener, String toolTip) {
		JButton b = new JButton(label);
		b.setActionCommand(cmd);
		b.addActionListener(listener);
		b.setToolTipText(toolTip);
		
		return b;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("UPDATE")) {
			for (int i = 0; i < livingObjects.size(); i++) {
				livingObjects.get(i).update();
			}
			dp.removeAll();
			revalidate();
			repaint();
		}
		
		if (e.getActionCommand().equals("CREATE")) {
			LivingSquare.getRandom(this, Primary.rand, dp);
		}
	}
	
	public void deathOccured(LifeEvent e) {
		livingObjects.removeElement(e.getSource());
	}
	
	public void lifeOccured(LifeEvent e) {
		livingObjects.add(e.getSource());
	}
	
	public static void main(String[] args) {
		new Primary();
	}
}