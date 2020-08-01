package game.bom.deck;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicArrowButton;

import game.bom.global.Globals;
import game.bom.utilities.Loader;

/**
 * GUI for creating decks
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class DeckCreatorGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel p;
	private ArrayList<Integer> possibleCards, selectedCards;
	private int[][] pages;
	private int pageNum;
	private DeckCreatorGUI w;

	private JCheckBox generateCheckbox(int pos) {
		JCheckBox b = new JCheckBox(Loader.card(Integer.toString(possibleCards.get(pages[pageNum][pos]))).getName());
		int d = pages[pageNum][pos]+1;
		b.setSelected(selectedCards.contains(d));
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(b.isSelected()) {
					addCard(d, b);
				} else {
					removeCard(d, b);
				}
			}

		});
		return b;
	}

	private void addCard(int idNum, JCheckBox box) {
		Object[] options = {0, 1, 2, 3, 4};
		int s = (int) JOptionPane.showInputDialog(
				this,
				"How many of the card " + box.getText() + " would you like to add?",
				"Add Card",
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				0);
		if (selectedCards.size() + s > Globals.MAX_CARDS) {
			JOptionPane.showMessageDialog(this,
					"This amount would put your deck over the limit.",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			s = -1;
		}
		switch (s) {
		case 1:
		case 2:
		case 3:
		case 4:
			for (int i = 0; i < s; i++) {
				selectedCards.add(idNum);
			}
			break;
		default:
			box.setSelected(false);
		}

	}

	private void removeCard(int idNum, JCheckBox box) {
		Object[] options = {0, 1, 2, 3, 4, 5};
		int s = (int) JOptionPane.showInputDialog(
				this,
				"How many of the card " + box.getText() + " would you like to add?",
				"Add Card",
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				0);
		if (!selectedCards.contains(idNum)) { // Should literally never happen, but there just in case
			JOptionPane.showMessageDialog(this,
					"This card is not in your deck",
					"Error",
					JOptionPane.ERROR_MESSAGE);
			s = -1;
		}
		switch (s) {
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			try {
				for (int i = 0; i < s; i++) {
					selectedCards.remove(idNum);
				}
			} catch(Exception e) {

			}
			break;
		default:
			box.setSelected(true);
		}
	}

	private void addCheckboxes() {
		try {
			for (int i = 0; i < pages[pageNum].length; i++) {
				p.add(generateCheckbox(i));
			}
		} catch(Exception e) {

		}
	}

	public DeckCreatorGUI() {
		super("Deck Creator");
		possibleCards = new ArrayList<>();
		selectedCards = new ArrayList<>();
		pages = new int[(int)Math.ceil(Globals.NUM_CARDS/5.0)][5];
		pageNum = 0;
		p = new JPanel();

		for (int i = 1; i <= Globals.NUM_CARDS; i++) {
			possibleCards.add(i);
		}

		int cardPage = 0;
		for (int i = 0; i < pages.length; i++) {
			for (int j = 0; j < pages[i].length; j++) {
				pages[i][j] = cardPage;
				cardPage++;
			}
		}

		addCheckboxes();

		Container c = getContentPane();
		p.setLayout(new GridLayout(2,2));


		JButton finalize = new JButton("Create Deck");
		finalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object[] options = null;
				String name = (String)JOptionPane.showInputDialog(
						w,
						"What is the deck name?",
						"Deck Name",
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						"Default");
				DeckCreator dc = new DeckCreator();
				dc.setCardArray(selectedCards);
				try {
					dc.createDeck(name);
					System.out.println("Deck created");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});


		JPanel page = new JPanel();

		JLabel pageNumb = new JLabel(Integer.toString(pageNum));

		JButton prevPage = new BasicArrowButton(BasicArrowButton.WEST);
		prevPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNum > 0) {
					pageNum--;
					c.remove(p);
					p.removeAll();
					pageNumb.setText(Integer.toString(pageNum));
					addCheckboxes();
					c.add(p, BorderLayout.CENTER);
				}
			}
		});

		JButton nextPage = new BasicArrowButton(BasicArrowButton.EAST);
		nextPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pageNum < pages.length) {
					pageNum++;
					c.remove(p);
					p.removeAll();
					pageNumb.setText(Integer.toString(pageNum));
					addCheckboxes();
					c.add(p, BorderLayout.CENTER);
				}
			}
		});

		page.setLayout(new GridLayout(0,2));
		page.add(prevPage); page.add(pageNumb); page.add(nextPage);
		page.add(finalize);

		c.add(page, BorderLayout.SOUTH);
		c.add(p, BorderLayout.CENTER);

		w = this;
	}

	public static void main(String[] args) {
		DeckCreatorGUI s = new DeckCreatorGUI();
		s.pack();
		s.setBounds(0, 0, 250, 250);
		s.setLocationRelativeTo(null);
		s.setDefaultCloseOperation(EXIT_ON_CLOSE);
		s.setVisible(true);
	}
}
