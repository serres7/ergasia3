package classes;

public class Customer 
{	
	private String id;
	private String name;
		
	public Customer( String id, String name )
	{
		this.id = id;
		this.name = name;
				
	}//Constructor

	public String getId() 
	{
		return id;
		
	}//getId

	public void setId( String id ) 
	{
		this.id = id;
		
	}//setId

	public String getName() 
	{
		return name;
		
	}//getName

	public void setName( String name ) 
	{
		this.name = name;
		
	}//setName
	
}//Class Customer
