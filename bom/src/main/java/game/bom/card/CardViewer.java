package game.bom.card;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import game.bom.global.Globals;
import game.bom.utilities.CardGUIViewer;
import game.bom.utilities.Loader;

/**
 * GUI for viewing individual cards
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class CardViewer extends JFrame {
	private static final long serialVersionUID = 5211072213416027189L;
	private ArrayList<JButton> cardButton;

	public CardViewer() {
		super("Card Viewer");

		cardButton = new ArrayList<JButton>();
		addButtons();

		Container c = getContentPane();

		JPanel p = new JPanel();
		System.out.println(cardButton.size());
		for (JButton i : cardButton)
			p.add(i);
		c.add(p, BorderLayout.CENTER);
	}

		// TODO: Check account collection, and add only cards in said collection
		private void addButtons() {
			for (int i = 0; i < Globals.NUM_CARDS; i++) {
				final int id = i+1;
				final JButton a = new JButton(Loader.card(Integer.toString(id)).getName());
				a.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						final int x = 400, y = 400, width = 400, height = 400;
						
						JOptionPane b = new JOptionPane(CardGUIViewer.viewCard(Loader.card(Integer.toString(id))));
						
						JDialog c = new JDialog();
						c.setTitle("Card: " + a.getText());
						c.setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
						c.setContentPane(b);
						c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
						c.pack();
						c.setBounds(x, y, width, height);
						c.setVisible(true);
					}
				});
				cardButton.add(a);
			}
		}
		
		public void start(CardViewer a) {
			final int x = 400, y = 400, width = 400, height = 400;
			a.setBounds(x, y, width, height);
			a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			a.pack();
			a.setLocationRelativeTo(null);
			a.setVisible(true);
		}
	}
