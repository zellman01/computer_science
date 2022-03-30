package server.gui;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.event.*;

public class ServerGUI extends JFrame implements MouseListener {
	private CustomTable dlmHandlers;
	private JTable table;
	private TableRowSorter<CustomTable> trs;
	private JButton shutDown;
	private Server server;
	private JPopupMenu popupMenu;
	private Banlist bans;
	
	public ServerGUI(Server server, Banlist bans) {
		super("BOM Server");
		this.server = server;
		this.bans = bans;
		dlmHandlers = new CustomTable();
		
		JScrollPane scroll;
		
		table = new JTable(dlmHandlers);
		trs = new TableRowSorter<CustomTable>(dlmHandlers);
		table.setRowSorter(trs);
		
		shutDown = new JButton("Shutdown");
		shutDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				server.serverShutdown();
			}
		});
		
		popupMenu = newPopupMenu();
		
		table.addMouseListener(this);
		
		table.setMinimumSize(new Dimension(200,200));
		
		table.setColumnModel(getColumnModel());
		
		dlmHandlers.addTableModelListener(table);
		
		scroll = new JScrollPane(table);
		
		JPanel scrollPanel = new JPanel();
		scrollPanel.add(scroll);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(shutDown);
		
		add(scrollPanel, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.SOUTH);
	}
	
	public void setupWindow() {
		Toolkit tk;
		Dimension d;
		
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/2, d.height/2);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setVisible(true);
	}
	
	public void addHandler(Handler h) {
		dlmHandlers.addHandler(h);
	}
	
	public void removeHandler(Handler h) {
		dlmHandlers.removeHandler(h);
	}
	
	private TableColumnModel getColumnModel() {
		DefaultTableColumnModel col;
		
		col = new DefaultTableColumnModel();
		String names[] = {"IP Address", "Authorized", "User"};
		int pWidth[] = {30, 30, 30};
		for (int i = 0 ; i < 3; i++) {
			col.addColumn(createTableColumn(i, pWidth[i], pWidth[i], names[i]));
		}
		
		return col;
	}
	
	private TableColumn createTableColumn(int index, int preferredWidth, int minWidth, String name) {
		TableColumn c = new TableColumn(index);
		c.setPreferredWidth(preferredWidth);
		c.setMinWidth(minWidth);
		c.setHeaderValue(name);
		return c;
	}
	
	private JPopupMenu newPopupMenu() {
		JMenuItem menu;
		JPopupMenu tmp = new JPopupMenu();
		
		menu = makeItem("Force Disconnect", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Handler h = dlmHandlers.getDataAt(table.convertRowIndexToModel(table.getSelectedRow()));
				
				
				removeHandler(h);
				h.forceDisconnect();
			}
		});
		tmp.add(menu);
		
		menu = makeItem("IPBan", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Handler h = dlmHandlers.getDataAt(table.convertRowIndexToModel(table.getSelectedRow()));
				
				bans.addIPBan(h.getAddress());
			}
		});
		tmp.add(menu);
		
		menu = makeItem("User Ban", new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Handler h = dlmHandlers.getDataAt(table.convertRowIndexToModel(table.getSelectedRow()));
				
				String user = h.getUserAuthorized();
				
				if (!user.equals("")) {
					bans.addUserBan(user);
					removeHandler(h);
					h.forceDisconnect();
				}
			}
		});
		tmp.add(menu);
		
		return tmp;
	}
	
	private JMenuItem makeItem(String label, ActionListener listener) {
		JMenuItem m;
		m = new JMenuItem(label);
		m.addActionListener(listener);
		
		return m;
	}
	
	public void mouseClicked(MouseEvent e) {
		//showPopup(e);
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		/*if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
			edit.doClick();
		}*/
		//showPopup(e);
	}
	
	public void mouseReleased(MouseEvent e) {
		int r = table.rowAtPoint(e.getPoint());
		if (r >= 0 && r < table.getRowCount())
			table.setRowSelectionInterval(r, r);
		else
			table.clearSelection();
		showPopup(e);
	}
	
	private void showPopup(MouseEvent e) {
		if(e.isPopupTrigger()) popupMenu.show(e.getComponent(), e.getX(), e.getY());
	}
}