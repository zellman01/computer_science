import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUIMain extends JFrame implements ActionListener {
	private GUIMain b;
	@SuppressWarnings("unused")
	private HOLO battle;
	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar;
	private JMenu menu, subMenu;
	private JMenuItem menuItem;
	private JTextField commands;
	private JButton confirm;

	public GUIMain() {
		super("Test");
		addMenus();
		
		Container c = getContentPane();
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,3));
		commands = new JTextField(2);
		p.add(commands, BorderLayout.NORTH);
		confirm = new JButton("Confirm");
		confirm.addActionListener(this);
		
		c.add(p, BorderLayout.CENTER);
		c.add(confirm, BorderLayout.SOUTH);
		
	}

	private void addMenus() {
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("Primary menu area");
		menuBar.add(menu);
		
		

		subMenu = new JMenu("Creators");
		menuItem = new JMenuItem("Character Creator");
		menuItem.getAccessibleContext().setAccessibleDescription("");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CCGUI c = new CCGUI();
				c.start();
			}

		});
		subMenu.add(menuItem);

		menuItem = new JMenuItem("Special Creator");
		menuItem.getAccessibleContext().setAccessibleDescription("");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				SCGUI s = new SCGUI();
				s.start();	
			}
		});
		subMenu.add(menuItem);
		
		menu.add(subMenu);
		menu.addSeparator();
		subMenu = new JMenu("Battle");
		
		menuItem = new JMenuItem("New Battle");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		subMenu.add(menuItem);
		
		menu.add(subMenu);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	private void start() {
		b = new GUIMain();
		b.pack();
		b.setBounds(300,300,300,150);
		b.setLocationRelativeTo(null);
		b.setDefaultCloseOperation(EXIT_ON_CLOSE);
		b.setJMenuBar(menuBar);
		b.setVisible(true);
	}

	public static void main(String[] args) {
		GUIMain s = new GUIMain();
		s.start();
	}

}
