package game2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;

public class GameGUIMain extends JFrame {
	public GameGUIMain() {
		super("Test");
		
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		
		GameGUICP controls = new GameGUICP();
	    Game game = new Game(2, false);
		c.add(controls, BorderLayout.SOUTH);
		//c.add(game, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		GameGUIMain main1 = new GameGUIMain();
		main1.setBounds(300, 300, 400, 400);
		main1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		main1.setVisible(true);
	}
}
