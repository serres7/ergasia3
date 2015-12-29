package classes;

import java.util.*;

import classes.Invoice;

public final class InvoiceList {
	private static InvoiceList instance;
	
	public static InvoiceList getInstance()
	{
		if (instance == null) {
			instance = new InvoiceList();
		}
		
		return instance;
	}
	
	
	private ArrayList<Invoice> invoices;
	
	private InvoiceList() 
	{
		invoices = new ArrayList<Invoice>();
	}
	
	public ArrayList<Invoice> getInvoicesList()
	{
		return invoices;
	}
	
}
