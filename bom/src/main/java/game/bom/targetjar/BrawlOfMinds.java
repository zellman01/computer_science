package game.bom.targetjar;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import game.bom.utilities.Update;
import game.bom.deck.DeckCreatorGUI;
import game.bom.deck.DeckViewer;
import game.bom.card.CardViewer;

/**
 * Primary jar file
 * @author zellman01
 * @version 0.1.0
 * @since 0.1.0
 */
public class BrawlOfMinds extends JFrame {
	private static final long serialVersionUID = 5727027099384772746L;
	private JMenuBar menuBar;
	private boolean cardSuccessUpdate;
	
	public BrawlOfMinds() {
		super("Brawl of Minds");
		addMenu();
		
		Container c = getContentPane();
		
		c.add(menuBar, BorderLayout.NORTH);
	}
	
	private void addMenu() {
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription("File operations");
		menuBar.add(menu);
		
		JMenu subMenu = new JMenu("Creator");
		JMenuItem menuItem = new JMenuItem("Deck creator");
		menuItem.getAccessibleContext().setAccessibleDescription("GUI for creating a deck");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeckCreatorGUI a = new DeckCreatorGUI();
				a.start();
			}
		});
		
		subMenu.add(menuItem);
		
		menu.add(subMenu);
		
		subMenu = new JMenu("Viewers");
		menuItem = new JMenuItem("View deck");
		menuItem.getAccessibleContext().setAccessibleDescription("GUI for viewing the deck");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DeckViewer a = new DeckViewer();
				a.start();
			}
		});
		
		subMenu.add(menuItem);
		// TODO: Set this to an account-specific card collection once made (account system needs to be done first)
		menuItem = new JMenuItem("View collection");
		menuItem.getAccessibleContext().setAccessibleDescription("GUI for viewing account card collection");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardViewer a = new CardViewer();
				a.start(cardSuccessUpdate);
			}
		});
		
		subMenu.add(menuItem);
		
		menu.add(subMenu);
	}
	
	public static void main(String[] args) {
		Update u = new Update();
		BrawlOfMinds bom = new BrawlOfMinds();
		bom.cardSuccessUpdate = u.updateCards(true);
		bom.setBounds(400,400,400,400);
		bom.setDefaultCloseOperation(EXIT_ON_CLOSE);
		bom.setVisible(true);
	}
}
