import javax.swing.table.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

public class ListModel<E> extends DefaultListModel {
	public void store(DataOutputStream dos) throws IOException {
		Object allElements[] = toArray();
		int arrayLength = allElements.length;
		dos.writeInt(arrayLength);
		for (int i = 0; i < arrayLength; i++) {
			WorkOrder temp = (WorkOrder)(allElements[i]);
			temp.store(dos);
		}
		dos.flush();
		dos.close();
	}
}