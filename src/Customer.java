import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
	
	private List<Account> accounts;
	private List<String> transactions;
	private List<Collateral> collateralsOwn;
	private List<Collateral> collateralsLoan;
	
	public Customer(String name, String email, String psw) {
		super(name, email, psw);
		accounts = new ArrayList<Account>();
		createAccount(3);
		transactions = new ArrayList<String>();
		collateralsOwn = new ArrayList<Collateral>();
		collateralsOwn.add(new Collateral("phone8", 4000, 600, 500));
		collateralsOwn.add(new Collateral("phoneX", 6000, 800, 700));
		collateralsOwn.add(new Collateral("phone11", 9000, 1300, 1000));
		collateralsLoan = new ArrayList<Collateral>();
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public List<String> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<String> transactions) {
		this.transactions = transactions;
	}
	
	public List<Collateral> getCollateralsOwn() {
		return collateralsOwn;
	}

	public void setCollateralsOwn(List<Collateral> collateralsOwn) {
		this.collateralsOwn = collateralsOwn;
	}

	public List<Collateral> getCollateralsLoan() {
		return collateralsLoan;
	}

	public void setCollateralsLoan(List<Collateral> collateralsLoan) {
		this.collateralsLoan = collateralsLoan;
	}
	
	public void removeCollaterals(List<Collateral> collaterals, int pos) {
		collaterals.remove(pos);
	}
	
	public void addCollaterals(Collateral collateral) {
		collateralsOwn.add(collateral);
	}

	//Create account
	public void createAccount(int type) {
		Account account = new Account(type);
		accounts.add(account);
	}
	
	
	// get loan
	public double getLoan(String accountNum, String collateralLabel, int currencyType) {
		//get value of collateral
		double loanAmount = 0;
		Collateral c = null;
		for(int i = 0;i < collateralsOwn.size();i++) {
			if(collateralLabel.equals(collateralsOwn.get(i).getLabel())) {
				c = collateralsOwn.get(i);
				loanAmount = collateralsOwn.get(i).getOriValue()[currencyType];
				for(int j = 0;j < 3;j++) {
					if(j != currencyType) {
						collateralsOwn.get(i).setNewValue(j, 0);
					}
				}
				// add to collateralsLoan and remove this collateralLabel from collateralsOwn
				collateralsLoan.add(c);
				collateralsOwn.remove(i);
			}
		}
		// find account
		for(int i = 0;i < accounts.size();i++) {
			if(accountNum.equals(accounts.get(i).getAccountNum())) {
				double balance = accounts.get(i).getCurrencies().get(currencyType).getBalance();
				//update balance
				accounts.get(i).getCurrencies().get(currencyType).setBalance(balance + loanAmount);
			}
		}
		// update loan account
		for(int i = 0;i < accounts.size();i++) {
			if(accounts.get(i).getType() == 3) {
				double balance = accounts.get(i).getCurrencies().get(currencyType).getBalance();
				accounts.get(i).getCurrencies().get(currencyType).setBalance(balance - loanAmount);
			}
		}
		return loanAmount;
	}
	
	// Pay loan
	public boolean payLoan(String accountNum, String collateralLabel) {
		//get value of collateral
		double loanAmount = 0;
		int currencyType = -1;
		int movePos = -1;
		Collateral c = null;
		boolean isSucceed = false;
		for(int i = 0;i < collateralsLoan.size();i++) {
			if(collateralLabel.equals(collateralsLoan.get(i).getLabel())) {
				movePos = i;
				for(int j = 0;j < 3;j++) {
					if(collateralsLoan.get(i).getNewValue()[j] != 0) {
						loanAmount = collateralsLoan.get(i).getNewValue()[j];
						currencyType = j;
					}
				}
			}
		}
		// find account
		for(int i = 0;i < accounts.size();i++) {
			if(accountNum.equals(accounts.get(i).getAccountNum())) {
				double balance = accounts.get(i).getCurrencies().get(currencyType).getBalance();
				if(balance >= loanAmount) {
					// update checking account
					accounts.get(i).getCurrencies().get(currencyType).setBalance(balance - loanAmount);
					collateralsLoan.get(movePos).cleanValue();
					c = collateralsLoan.get(movePos);
					// add to collateralsOwn and remove this collateralLabel from collateralsLoan
					collateralsOwn.add(c);
					collateralsLoan.remove(movePos);
					// update loan account
					for(int j = 0;j < accounts.size();j++) {
						if(accounts.get(j).getType() == 3) {
							double loanBalance = accounts.get(j).getCurrencies().get(currencyType).getBalance();
							accounts.get(j).getCurrencies().get(currencyType).setBalance(loanBalance + loanAmount);
						}
					}
					isSucceed = true;
				}
			}
		}
		return isSucceed;
	}
	
	
	// Save money
	public void saveMoney(String accountNum, int type, double amount) {
		for(int i = 0;i < accounts.size();i++) {
			if(accountNum.equals(accounts.get(i).getAccountNum())) {
				double balance = accounts.get(i).getCurrencies().get(type).getBalance();
				accounts.get(i).getCurrencies().get(type).setBalance(balance + amount);
			}
		}
	}
		
	// Withdraw money
	public boolean withdrawMoney(String accountNum, int type, double amount) {
		boolean isSucceed = false;
		for(int i = 0;i < accounts.size();i++) {
			if(accountNum.equals(accounts.get(i).getAccountNum())) {
				double balance = accounts.get(i).getCurrencies().get(type).getBalance();
				if(balance >= amount) {
					accounts.get(i).getCurrencies().get(type).setBalance(balance - 1.05 * amount);
					isSucceed = true;
					break;
				}
			}
		}
		return isSucceed;
	}
	
	// Transact money
	public boolean transactMoney(Customer customer, String otherAccountNum, String operateAccountNum, int type, double amount) {
		boolean isSucceed = false;
		//if operateAccount has enough money to transact
		for(int i = 0;i < this.accounts.size();i++) {
			if(operateAccountNum.equals(accounts.get(i).getAccountNum())) {
				double operateBalance = accounts.get(i).getCurrencies().get(type).getBalance();
				if(operateBalance >= amount) {
					accounts.get(i).getCurrencies().get(type).setBalance(operateBalance - 1.05 * amount);
					// find customer's account
					for(int j = 0;j < customer.getAccounts().size();j++) {
						if(otherAccountNum.equals(customer.getAccounts().get(j).getAccountNum()) && customer.getAccounts().get(j).getType() == 1) {
							double otherBalance = customer.getAccounts().get(j).getCurrencies().get(type).getBalance();
							customer.getAccounts().get(j).getCurrencies().get(type).setBalance(otherBalance + amount);
							transactRecord(customer, operateAccountNum, otherAccountNum, type, amount);
							isSucceed = true;
							break;
						}
					}
				}
			}
		}
		
		return isSucceed;
	}
	
	public void transactRecord(Customer customer2, String accountNum1, String accountNum2, int type, double amount) {
		String currencyName = "";
		if(type == 0) {
			currencyName = "RMBs";
		}else if(type == 1) {
			currencyName = "dollars";
		}else {
			currencyName = "pounds";
		}
		String str = " transacts " 
					+ amount + " "
					+ currencyName
					+ " from account " + accountNum1
					+ " to account " + accountNum2
					+ " of " + customer2.getName();
		
		transactions.add(str);
	}
	
}
