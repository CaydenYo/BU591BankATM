import java.util.ArrayList;
import java.util.List;

public class FancyBank {
	private ArrayList<Customer> customers;
	private Manager manager;
	private LoginInterface li;
	
	public FancyBank() {
		customers = new ArrayList<Customer>();
		manager = new Manager("admin", "admin123", "admin123");
		
		for(int i = 0;i < 3;i++) {
			Customer customer = new Customer("000" + i, "000" + i, "000" + i);
			customers.add(customer);
		}
		li = new LoginInterface(customers, manager);
	}
	
	public static void main(String[] args) {
		FancyBank fb = new FancyBank();
	}
}
