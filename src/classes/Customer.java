package classes;

import java.util.ArrayList;
import java.util.List;

public class Customer 
{
	//Attributes
	private String id;
	private String name;
	private List invoiceList;
	
	public Customer( String id, String name )
	{
		this.id = id;
		this.name = name;
		this.invoiceList = new ArrayList();
		
	}//Contructor

}
