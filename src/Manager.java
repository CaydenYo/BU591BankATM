import java.util.ArrayList;
import java.util.List;

public class Manager extends User{
	private List<String> transactionReport;

	public Manager(String name, String email, String psw) {
		super(name, email, psw);
		transactionReport = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}
	
	public void transactionReport(Customer customer1, Customer customer2, String accountNum1, String accountNum2, int currencyType, double amount) {
		String currencyName = "";
		if(currencyType == 0) {
			currencyName = "RMBs";
		}else if(currencyType == 1) {
			currencyName = "dollars";
		}else {
			currencyName = "pounds";
		}
		
		String str = customer1.getName() + " transacts " 
										 + amount + " "
										 + currencyName
										 + " from account " + accountNum1
										 + " to account " + accountNum2
										 + " of " + customer2.getName();
		transactionReport.add(str);
	}

	public List<String> getTransactionReport() {
		return transactionReport;
	}

	public void setTransactionReport(List<String> transactionReport) {
		this.transactionReport = transactionReport;
	}
	
	

}
