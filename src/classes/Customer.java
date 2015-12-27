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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
