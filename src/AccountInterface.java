import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class AccountInterface extends JFrame {

	private JPanel contentPane;
	private int checkingAccountNum = 0;
	private int savingAccountNum = 0;
	/**
	 * Create the frame.
	 */
	public AccountInterface(ArrayList<Customer> customers,Customer customer, Manager manager, int role) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 550, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1));
		setVisible(true);
		
		for(int i = 0;i < customer.getAccounts().size();i++) {
			if(customer.getAccounts().get(i).getType() == 1) {
				checkingAccountNum += 1;
			}else {
				savingAccountNum += 1;
			}
		}
		
		JPanel checkingAccounts = new JPanel(new GridLayout(checkingAccountNum + 1, 1));
		JPanel jptop = new JPanel(new GridLayout(1, 2));
		JPanel jpBack = new JPanel();
		JLabel jlCheckingAccount = new JLabel("Checking Account:");
		jpBack.setLayout(null);
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(84, 0, 75, 29);
		jpBack.add(btnBack);
		jptop.add(jlCheckingAccount);
		jptop.add(jpBack);
		checkingAccounts.add(jptop);
		for(int i = 0;i < customer.getAccounts().size();i++) {
			if(customer.getAccounts().get(i).getType() == 1) {
				JLabel jlCheckingAccountDetails = new JLabel(customer.getAccounts().get(i).toString());
				checkingAccounts.add(jlCheckingAccountDetails);
			}
		}
		
		JPanel savingAccounts = new JPanel(new GridLayout(savingAccountNum + 1, 1));
		JLabel jlSavingAccount = new JLabel("Saving Account:");
		savingAccounts.add(jlSavingAccount);
		for(int i = 0;i < customer.getAccounts().size();i++) {
			if(customer.getAccounts().get(i).getType() == 2) {
				JLabel jlSavingAccountDetails = new JLabel(customer.getAccounts().get(i).toString());
				savingAccounts.add(jlSavingAccountDetails);
			}
		}
		
		JPanel loanAccount = new JPanel(new GridLayout(2, 1));
		JLabel jlLoanAccount = new JLabel("Loan Account:");
		loanAccount.add(jlLoanAccount);
		for(int i = 0;i < customer.getAccounts().size();i++) {
			if(customer.getAccounts().get(i).getType() == 3) {
				JLabel jlSavingLoanDetails = new JLabel(customer.getAccounts().get(i).toString());
				loanAccount.add(jlSavingLoanDetails);
			}
		}
		
		contentPane.add(checkingAccounts);
		contentPane.add(savingAccounts);
		contentPane.add(loanAccount);
		
		// add listener
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(role == 1) {
					CustomerInterface ci = new CustomerInterface(customers, customer, manager);
					dispose();
				}else if(role == 2) {
					dispose();
				}
			}
		});
	}

}
