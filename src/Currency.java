
public class Currency {
	private String type = "";
	private double balance = 0;
	
	public Currency(String label, double amount) {
		this.type = label;
		this.balance = amount;
	}

	public String getLabel() {
		return type;
	}

	public void setLabel(String label) {
		this.type = label;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
}
