package game2;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameGUICP extends JPanel
implements ActionListener {
	private JTextField text1;
	private JButton submit;
	
	public GameGUICP() {
		submit = new JButton("Submit");
		submit.setPreferredSize(new Dimension(80, 30));
		submit.setToolTipText("Submit your selection for the selected row.");
		submit.addActionListener(this);
		
		text1 = new JTextField(2);
		text1.setToolTipText("Input the number you want to take from the selected row.");
		text1.setHorizontalAlignment(JTextField.RIGHT);
		
		add(text1);
		add(submit);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
