import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Account {
	
	private String accountNum;
	// 1--checking account  2--saving account  3---loan account
	private int type;
	// type:1--interest:0 
	private double interestRate;
	private List<Currency> currencies;
	
	public Account(int type) {
		// Create 8 digits account number
		StringBuilder str = new StringBuilder();
		this.type = type;
		if(type == 1) {
			interestRate = 0;
		}else if(type == 2){
			interestRate = 0.05;
		}else {
			interestRate = 0.1;
		}
		
		Random random = new Random();
		for(int i = 0;i < 8;i++){
		    str.append(random.nextInt(10));
		}
		this.accountNum = str.toString();
		this.currencies = new ArrayList<Currency>();
		String[] labels = {"RMB", "dollar", "pound"};
		for(int i = 0;i < labels.length;i++) {
			currencies.add(new Currency(labels[i], 0));
		}
		// charge 10 dollars to open an account
		if(type == 1 || type == 2) {
			double balance = currencies.get(1).getBalance();
			currencies.get(1).setBalance(balance - 10);
		}
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public List<Currency> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(List<Currency> currencies) {
		this.currencies = currencies;
	}
	
	public void updateSavingBalance(double bound0, double bound1, double bound2) {
		if(this.type == 2) {
			if(this.currencies.get(0).getBalance() >= bound0) {
				this.currencies.get(0).setBalance(this.currencies.get(0).getBalance() * (1 + interestRate));
			}
			if(this.currencies.get(1).getBalance() >= bound1) {
				this.currencies.get(1).setBalance(this.currencies.get(1).getBalance() * (1 + interestRate));
			}
			if(this.currencies.get(2).getBalance() >= bound2) {
				this.currencies.get(2).setBalance(this.currencies.get(2).getBalance() * (1 + interestRate));
			}
		}
	}
	
	public void updateLoanBalance() {
		for(int i = 0;i < currencies.size();i++) {
			double balance = currencies.get(i).getBalance();
			currencies.get(i).setBalance(balance * (1 + interestRate));
		}
	}
	
	
	public String toString() {
		String str = "";
		str += ("Account number: " + accountNum + "     ");
		for(int i = 0;i < currencies.size();i++) {
			str += (currencies.get(i).getLabel() + ": " + currencies.get(i).getBalance() + "    ");
		}
		str += "\n";
		return str;
	}
	
}
