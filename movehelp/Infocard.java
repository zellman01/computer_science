import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.table.*;

public class Infocard extends JDialog {
	private JLabel typeLabel;
	private JLabel nameLabel;
	private JLabel accuracyLabel;
	private JLabel criticalLabel;
	private JLabel damagePoolLabel;
	private JLabel effectLabel;
	
	private JLabel type;
	private JLabel name;
	private JLabel accuracy;
	private JLabel critical;
	private JLabel damagePool;
	private JLabel effect;
	
	public Infocard(Moves m) {
		typeLabel = new JLabel("Type: ");
		nameLabel = new JLabel("Name: ");
		accuracyLabel = new JLabel("Accuracy: ");
		criticalLabel = new JLabel("Critical: ");
		damagePoolLabel = new JLabel("Damage pool: ");
		effectLabel = new JLabel("Effect: ");
		
		type = new JLabel(m.getType());
		name = new JLabel(m.getName());
		String accuracyText = "";
		if (m.getAccuracy() < 0) accuracyText += "--";
		else accuracyText += m.getAccuracy();
		accuracy = new JLabel(accuracyText);
		String criticalText = "";
		if (m.getAccuracy() == -1) {
			criticalText += m.getCritical() + "-100";
		} else {
			criticalText += m.getCritical() + "-" + m.getAccuracy();
		}
		
		// Input Accuracy mod
		critical = new JLabel(criticalText);
		damagePool = new JLabel(m.getDamagePool());
		effect = new JLabel(m.getEffect());
		
		JPanel panel = new JPanel();
		panel.add(typeLabel);
		panel.add(type);
		panel.add(nameLabel);
		panel.add(name);
		panel.add(accuracyLabel);
		panel.add(accuracy);
		panel.add(criticalLabel);
		panel.add(critical);
		panel.add(damagePoolLabel);
		panel.add(damagePool);
		panel.add(effectLabel);
		panel.add(effect);
		
		add(panel);
		
		start();
	}
	
	private void start() {
		Toolkit tk;
		Dimension d;
		
		setTitle("Move info");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/2, d.height/2);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setVisible(true);
	}
}