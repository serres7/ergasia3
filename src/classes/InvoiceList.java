package classes;

import java.util.*;

import classes.Invoice;

public final class InvoiceList {
	private static InvoiceList instance;
	
	public static InvoiceList getInstance()
	{
            synchronized(InvoiceList.class){  //OCPrinciple for multithreading environment
		if (instance == null) {
                    
			instance = new InvoiceList();
                    }
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
	
	public void addInvoice( Invoice i)
	{
		this.invoices.add( i );
	}
}
