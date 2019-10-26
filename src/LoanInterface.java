import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JSeparator;

public class LoanInterface extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public LoanInterface(ArrayList<Customer> customers, Customer customer, Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblGetLoanAccountNum = new JLabel("Get loan to");
		lblGetLoanAccountNum.setBounds(30, 22, 89, 16);
		contentPane.add(lblGetLoanAccountNum);
		
		JComboBox cbGetLoanNum = new JComboBox();
		cbGetLoanNum.setBounds(144, 18, 163, 27);
		for(int i = 0;i < customer.getAccounts().size();i++) {
			// only checking account can get loan
			if(customer.getAccounts().get(i).getType() == 1) {
				cbGetLoanNum.addItem(customer.getAccounts().get(i).getAccountNum());
			}
		}
		contentPane.add(cbGetLoanNum);
		
		JLabel lblGetLoanCollaterals = new JLabel("Collaterals");
		lblGetLoanCollaterals.setBounds(30, 71, 89, 16);
		contentPane.add(lblGetLoanCollaterals);
		
		JComboBox cbCollateralsAvailable = new JComboBox();
		cbCollateralsAvailable.setBounds(144, 67, 138, 27);
		for(int i = 0;i < customer.getCollateralsOwn().size();i++) {
			cbCollateralsAvailable.addItem(customer.getCollateralsOwn().get(i).getLabel());
		}
		contentPane.add(cbCollateralsAvailable);
		
		JButton btnGetLoan = new JButton("Get loan");
		btnGetLoan.setBounds(383, 66, 96, 29);
		contentPane.add(btnGetLoan);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 99, 494, 12);
		contentPane.add(separator);
		
		JLabel lblPayAccountNumber = new JLabel("Pay loan from");
		lblPayAccountNumber.setBounds(26, 127, 106, 16);
		contentPane.add(lblPayAccountNumber);
		
		JComboBox cbPayLoanNum = new JComboBox();
		cbPayLoanNum.setBounds(144, 123, 163, 27);
		for(int i = 0;i < customer.getAccounts().size();i++) {
			// only checking account can pay loan
			if(customer.getAccounts().get(i).getType() == 1) {
				cbPayLoanNum.addItem(customer.getAccounts().get(i).getAccountNum());
			}
		}
		contentPane.add(cbPayLoanNum);
		
		JLabel lblReturnCollaterals = new JLabel("Collaterals");
		lblReturnCollaterals.setBounds(30, 176, 89, 16);
		contentPane.add(lblReturnCollaterals);
		
		JComboBox cbPayCollaterals = new JComboBox();
		cbPayCollaterals.setBounds(144, 172, 208, 27);
		for(int i = 0;i < customer.getCollateralsLoan().size();i++) {
			cbPayCollaterals.addItem(customer.getCollateralsLoan().get(i).getLabel());
		}
		contentPane.add(cbPayCollaterals);
		
		JButton btnPayLoan = new JButton("Pay loan");
		btnPayLoan.setBounds(383, 171, 96, 29);
		contentPane.add(btnPayLoan);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(181, 225, 117, 29);
		contentPane.add(btnBack);
		
		JLabel lblWarn1 = new JLabel("Only checking account");
		lblWarn1.setBounds(319, 22, 160, 16);
		contentPane.add(lblWarn1);
		
		JComboBox cbCurrencyType = new JComboBox();
		cbCurrencyType.setBounds(281, 67, 106, 27);
		cbCurrencyType.addItem("RMB");
		cbCurrencyType.addItem("dollar");
		cbCurrencyType.addItem("pound");
		contentPane.add(cbCurrencyType);
		
		JLabel label = new JLabel("Only checking account");
		label.setBounds(319, 127, 160, 16);
		contentPane.add(label);
		
		// add listener
		btnGetLoan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbGetLoanNum.getSelectedItem() != null && cbCollateralsAvailable.getSelectedItem() != null) {
					// TODO Auto-generated method stub
					String getLoanNum = cbGetLoanNum.getSelectedItem().toString();
					String loanLabel = cbCollateralsAvailable.getSelectedItem().toString();
					int currencyType = cbCurrencyType.getSelectedIndex();
					
					double loanAmount = customer.getLoan(getLoanNum, loanLabel, currencyType);
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "Succeed! You will get " + loanAmount + " " + customer.getAccounts().get(0).getCurrencies().get(currencyType).getLabel() + "s with " + getLoanNum , "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
					LoanInterface li = new LoanInterface(customers, customer, manager);
					dispose();
				}else {
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "The information is incomplete!", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				}
			}
		});
		
		btnPayLoan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbPayLoanNum.getSelectedItem() != null && cbPayCollaterals.getSelectedItem() != null) {
					// TODO Auto-generated method stub
					String payLoanNum = cbPayLoanNum.getSelectedItem().toString();
					String loanLabel = cbPayCollaterals.getSelectedItem().toString();
					
					if(customer.payLoan(payLoanNum, loanLabel)){
						Object[] options ={ "ok" }; 
						int m = JOptionPane.showOptionDialog(null, "Succeed! You have paid the loan and get the " + loanLabel + " back!" , "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
						LoanInterface li = new LoanInterface(customers, customer, manager);
						dispose();
					}else {
						Object[] options ={ "ok" }; 
						int m = JOptionPane.showOptionDialog(null, "You don't have enough money to pay the loan" , "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
					}
				}else {
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "The information is incomplete!", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CustomerInterface ci = new CustomerInterface(customers, customer, manager);
				dispose();
			}
		});
		
	}
}
