import java.util.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.text.html.parser.*;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import java.awt.BorderLayout;

public class Param {
	public static final int MAXRADIUS = 3;
	public static final int MAXEXPANSIONTIME = 6000;
	public static final int MAXRUNTIME = 8000;
	
	public static DefaultListModel<String> sortList(DefaultListModel<String> listToSort) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < listToSort.size(); i++) {
			list.add(listToSort.elementAt(i));
		}
		Collections.sort(list);
		for (int i = 0; i < listToSort.size(); i++) {
			listToSort.setElementAt(list.get(i),i);
		}
		
		return listToSort;
	}
	
	public static DefaultListModel<Pages> sortList(DefaultListModel<Pages> listToSort, Comparator c) {
		ArrayList<Pages> list = new ArrayList<Pages>();
		for (int i = 0; i < listToSort.size(); i++) {
			list.add(listToSort.elementAt(i));
		}
		Collections.sort(list, c);
		for (int i = 0; i < listToSort.size(); i++) {
			listToSort.setElementAt(list.get(i),i);
		}
		
		return listToSort;
	}
}