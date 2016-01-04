package classes;


public class Invoice {
	
	private String id;
	private String customerId;
	private double amount;
	private State state;
	
	public Invoice(String theId, String theCustomer, double theAmount) {
		id = theId;
		customerId = theCustomer;
		amount = theAmount;
		state = new NotPaid();   
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

	public State getState() {
		return this.state;
	}

	public void updateState() {
		state = new Paid();
	}
}
