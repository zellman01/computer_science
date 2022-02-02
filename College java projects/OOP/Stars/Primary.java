import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;
import java.util.Random;
import java.util.Hashtable;
import java.awt.BorderLayout;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JSlider;
import javax.swing.JLabel;

public class Primary extends JFrame implements ActionListener, LifeEventListener {
	private Vector<LivingObject> livingObjects;
	private Timer updateFire;
	private JButton addLivingObject;
	private JButton addMultipleObjects;
	private JButton pause;
	private JCheckBox drawTrace;
	private JButton clearStars;
	private JButton mode;
	private JSlider lifeTime;
	private JLabel lifeTimeLabel;
	private DrawPanel dp;
	private boolean paused;
	private boolean idle;
	public static final Random rand = new Random();
	
	public Primary() {
		JPanel buttonPanel = new JPanel();
		livingObjects = new Vector<LivingObject>();
		updateFire = new Timer(1000, this);
		updateFire.setActionCommand("UPDATE");
		addLivingObject = createButton("Add a living object", "CREATEONE", this, "Creates a new LivingObject");
		addMultipleObjects = createButton("Add living objects", "CREATEMANY", this, "Creates many LivingObjects");
		clearStars = createButton("Clear objects", "CLEAR", this, "Clears all LivingObjects");
		pause = createButton("Pause", "PAUSE", this, "Pauses/resumes the animation");
		lifeTimeLabel = new JLabel("LivingObject's lifetime:");
		mode = createButton("Idle", "IDLE", this, "Sets mode to the one shown");
		lifeTime = new JSlider(5, 15, 10);
		drawTrace = new JCheckBox("Draw trace");
		
		dp = new DrawPanel(livingObjects);
		
		Hashtable<Integer, JLabel> d = new Hashtable<Integer, JLabel>();
		for (int i = 0; i < 11; i++) {
			d.put(i+5, new JLabel((i+5)+""));
		}
		
		lifeTime.setLabelTable(d);
		lifeTime.setPaintLabels(true);
		
		buttonPanel.add(addLivingObject);
		buttonPanel.add(addMultipleObjects);
		buttonPanel.add(clearStars);
		buttonPanel.add(pause);
		buttonPanel.add(mode);
		buttonPanel.add(drawTrace);
		buttonPanel.add(lifeTimeLabel);
		buttonPanel.add(lifeTime);
		
		add(dp);
		add(buttonPanel, BorderLayout.SOUTH);
		updateFire.start();
		
		paused = false;
		idle = false;
		
		showJFrame();
	}
	
	private void showJFrame() {
		Toolkit tk;
		Dimension d;
		
		setTitle("Stars Project");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize((d.width/2)+35, d.height/2);
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
			if (!drawTrace.isSelected()) {
				dp.removeAll();
				revalidate();
				repaint();
			} else dp.repaint();
		}
		
		if (e.getActionCommand().equals("CREATEONE")) {
			LivingSquare.getRandom(this, Primary.rand, dp, lifeTime.getValue(), idle);
		}
		
		if (e.getActionCommand().equals("CREATEMANY")) {
			for (int i = 0; i < 15; i++) {
				LivingSquare.getRandom(this, Primary.rand, dp, lifeTime.getValue(), idle);
			}
		}
		
		if (e.getActionCommand().equals("CLEAR")) {
			livingObjects.clear();
			dp.removeAll();
			revalidate();
			repaint();
		}
		
		if (e.getActionCommand().equals("PAUSE")) {
			if (!paused) {
				updateFire.stop();
				paused = true;
				pause.setText("Resume");
			} else {
				paused = false;
				updateFire.start();
				pause.setText("Pause");
			}
		}
		
		if (e.getActionCommand().equals("IDLE")) {
			if (!idle) {
				for (int i = 0; i < livingObjects.size(); i++) {
					livingObjects.get(i).changeIdleState(true);
				}
				idle = true;
				mode.setText("Random");
			} else {
				for (int i = 0; i < livingObjects.size(); i++) {
					livingObjects.get(i).changeIdleState(false);
				}
				idle = false;
				mode.setText("Idle");
			}
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