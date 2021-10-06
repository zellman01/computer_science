import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import java.lang.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.time.*;

public class WorkOrder {
	private String name, dept, description;
	private double billingRate;
	private long initRequest, finishedRequest;

	public WorkOrder() {}
	
	public WorkOrder(String name, String dept, String desc, double billRate, long initRequest, long finishedRequest) {
		this.name = name;
		this.dept = dept;
		description = desc;
		billingRate = billRate;
		this.initRequest = initRequest;
		this.finishedRequest = finishedRequest;
	}
	
	public WorkOrder(DataInputStream dis) throws IOException {
		name = dis.readUTF();
		dept = dis.readUTF();
		description = dis.readUTF();
		billingRate = dis.readDouble();
		initRequest = dis.readLong();
		finishedRequest = dis.readLong();
	}
	
	public static WorkOrder getRandom(Random rand) {
		String departments[] = {"SALES", "HARDWARE", "ELECTRONICS"};
		WorkOrder randWO = new WorkOrder();
		
		randWO.name = "Random Person";
		// dept
		randWO.dept = departments[rand.nextInt(3)];
		randWO.description = "Random work order";
		randWO.billingRate = 6.75 + (10.50 - 6.75) * rand.nextDouble();
		long initRequest = (-946771200000L + (81L * 365 * 24 * 60 * 60 * 1000)) + (Math.abs(rand.nextLong()) % (365 * 24 * 60 * 60 * 1000));
		long finishedRequest = (-946771200000L + (81L * 365 * 24 * 60 * 60 * 1000)) + (Math.abs(rand.nextLong()) % (365 * 24 * 60 * 60 * 1000));
		
		if (initRequest > finishedRequest) {
			long temp = initRequest;
			initRequest = finishedRequest;
			finishedRequest = temp;
		}
		
		randWO.initRequest = initRequest;
		randWO.finishedRequest = finishedRequest;
		return randWO;
	}
	
	private void consolePrint() {
		System.out.println("-------Work Order debug-------");
		System.out.println(name + "\n" + dept + "\n" + description);
		System.out.println(billingRate);
		System.out.println(new Date(initRequest).toString());
		System.out.println(new Date(finishedRequest).toString());
	}
	
	public void store(DataOutputStream dos) throws IOException {
		dos.writeUTF(name);
		dos.writeUTF(dept);
		dos.writeUTF(description);
		dos.writeDouble(billingRate);
		dos.writeLong(initRequest);
		dos.writeLong(finishedRequest);
	}
	
	@Override
	public String toString() {
		String str = "Requestor's Name: " + name + "\t   Department:" + dept + "\t   Description:" + description + "\t   Start date:" + (new Date(initRequest).toString()) + "\t   End date:";
		if (finishedRequest == 0) str += " Not finished.   ";
		else str += new Date(finishedRequest).toString();
		return str += "\t   Billing Rate:" + billingRate;
		//return "Requestor's Name: " + name + "\t   Department:" + dept + "\t   Description:" + description + "\t   Start date:" + (new Date(initRequest).toString()) + "\t   End date:" + (new Date(finishedRequest).toString()) + "\t   Billing Rate:" + billingRate;
	}
	
	public String getName() { return name; }
	
	public String getDepartment() { return dept; }
	
	public String getDescription() { return description; }
	
	public double getBillingRate() { return billingRate; }
	
	public long getInitialRequestDate() { return initRequest; }
	
	public long getFinishedRequestDate() { return finishedRequest; }
}