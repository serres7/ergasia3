package classes;

public class Invoice {
	
	private String id;
	private String customerId;
	private double amount;
	private String state;
	
	public Invoice(String theId, String theCustomer, double theAmount) {
		id = theId;
		customerId = theCustomer;
		amount = theAmount;
		state = "red";   // Unpaid
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void updateState() {
		state = "green";   // Paid
	}
}
