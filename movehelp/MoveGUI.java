import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.table.*;

public class MoveGUI extends JFrame implements MouseListener {
	private JButton add;
	private JButton save;
	private JButton load;
	private TableModel tm;
	private TableRowSorter<TableModel> trs;
	private JTable table;
	private JFileChooser files;
	private File chosenFile;
	private MoveGUI mg;
	
	public MoveGUI() {
		mg = this;
		
		chosenFile = new File("");
		
		JScrollPane scrollBar;
		
		tm = new TableModel();
		table = new JTable(tm);
		
		trs = new TableRowSorter<TableModel>(tm);
		
		table.setRowSorter(trs);
		table.setColumnModel(getColumnModel());
		
		table.addMouseListener(this);
		
		scrollBar = new JScrollPane(table);
		
		files = new JFileChooser(".");
		
		add = new JButton("Add a move");
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new MoveHelper(tm);
			}
		});
		
		save = new JButton("Save to file");
		save.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!chosenFile.exists()) {
					if (!(files.showSaveDialog(mg) == JFileChooser.APPROVE_OPTION)) return;
					try {
						chosenFile = files.getSelectedFile();
						chosenFile.createNewFile();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				try {
					DataOutputStream dos = new DataOutputStream(new FileOutputStream(chosenFile));
					tm.saveElements(dos);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		load = new JButton("Load from file");
		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Load from a file
				if (files.showOpenDialog(mg) == JFileChooser.APPROVE_OPTION) {
					chosenFile = files.getSelectedFile();
					try {
						DataInputStream dis = new DataInputStream(new FileInputStream(chosenFile));
						tm.loadElements(dis);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		JPanel a = new JPanel();
		a.add(add);
		a.add(save);
		a.add(load);
		
		add(a, BorderLayout.SOUTH);
		
		add(scrollBar);
		
		start();
	}
	
	private TableColumnModel getColumnModel() {
		DefaultTableColumnModel colModel;
		
		colModel = new DefaultTableColumnModel();
		String names[] = {"Type", "Name", "Accuracy", "Critical", "Dmaage Pool", "Accuracy", "Effect"};
		int pWidth[] = {50, 50, 50, 50, 50, 50, 50};
		for (int i = 0; i < 7; i++) {
			colModel.addColumn(createTableColumn(i, pWidth[i], pWidth[i], names[i]));
		}
		
		return colModel;
		
	}
	
	private TableColumn createTableColumn(int index, int preferredWidth, int minWidth, String name) {
		TableColumn c = new TableColumn(index);
		c.setPreferredWidth(preferredWidth);
		c.setMinWidth(minWidth);
		c.setHeaderValue(name);
		return c;
	}
	
	private void start() {
		Toolkit tk;
		Dimension d;
		
		setTitle("Move GUI");
		tk = Toolkit.getDefaultToolkit();
		d = tk.getScreenSize();
		setSize(d.width/2, d.height/2);
		setLocation(d.width/4, d.height/4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	
	public void mouseClicked(MouseEvent e) {
	}
	
	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	
	public void mousePressed(MouseEvent e) {
		if (e.getClickCount() == 2 && table.getSelectedRow() != -1) {
			showPopup(e);
		}
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}
	
	private void showPopup(MouseEvent e) {
		int index = table.getSelectedRow();
		index = table.convertRowIndexToModel(index);
		Moves m = tm.getDataAt(index);
		new Infocard(m);
	}
	
	public static void main(String[] args) {
		new MoveGUI();
	}
}