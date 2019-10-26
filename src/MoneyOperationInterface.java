import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class MoneyOperationInterface extends JFrame {

	private JPanel contentPane;
	private JTextField textHowMuch;

	/**
	 * Create the frame.
	 */
	public MoneyOperationInterface(ArrayList<Customer> customers, Customer customer, Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblOperateOption = new JLabel("Save/Withdraw:");
		lblOperateOption.setBounds(59, 24, 120, 16);
		contentPane.add(lblOperateOption);
		
		JComboBox cbOperation = new JComboBox();
		cbOperation.addItem("Save");
		cbOperation.addItem("Withdraw");
		cbOperation.setBounds(191, 20, 129, 27);
		contentPane.add(cbOperation);
		
		JLabel lblAccountNum = new JLabel("Account number：");
		lblAccountNum.setBounds(59, 83, 120, 16);
		contentPane.add(lblAccountNum);
		
		JComboBox cbAccountNum = new JComboBox();
		for(int i = 0;i < customer.getAccounts().size();i++) {
			String accountNum = customer.getAccounts().get(i).getAccountNum();
			int type = customer.getAccounts().get(i).getType();
			String accountType = "";
			if(type == 1) {
				accountType = "Checking account";
				cbAccountNum.addItem(accountNum + " " + accountType);
			}else if(type == 2){
				accountType = "Saving account";
				cbAccountNum.addItem(accountNum + " " + accountType);
			}
		}
		cbAccountNum.setBounds(191, 79, 253, 27);
		contentPane.add(cbAccountNum);
		
		JLabel lblHowMuch = new JLabel("How much:");
		lblHowMuch.setBounds(59, 146, 120, 16);
		contentPane.add(lblHowMuch);
		
		textHowMuch = new JTextField();
		textHowMuch.setBounds(191, 141, 129, 26);
		contentPane.add(textHowMuch);
		textHowMuch.setColumns(10);
		
		JComboBox cbCurrencyType = new JComboBox();
		cbCurrencyType.addItem("RMB");
		cbCurrencyType.addItem("Dollar");
		cbCurrencyType.addItem("Pound");
		cbCurrencyType.setBounds(332, 142, 112, 27);
		contentPane.add(cbCurrencyType);
		
		JButton btnComfirm = new JButton("Comfirm");
		btnComfirm.setBounds(59, 202, 137, 45);
		contentPane.add(btnComfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(246, 202, 137, 45);
		contentPane.add(btnBack);
		
		// add listener
		btnComfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cbAccountNum.getSelectedItem() != null && !textHowMuch.getText().equals("")) {
					String operationType = cbOperation.getSelectedItem().toString();
					String accountNum = cbAccountNum.getSelectedItem().toString().substring(0, 8);
					int currencyType = cbCurrencyType.getSelectedIndex();
					double amount = Double.parseDouble(textHowMuch.getText());
					if(operationType.equals("Save")) {
						customer.saveMoney(accountNum, currencyType, amount);
						Object[] options ={ "ok" }; 
						int m = JOptionPane.showOptionDialog(null, "You successfully saved money to your account!", "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
					}else {
						if(!customer.withdrawMoney(accountNum, currencyType, amount)) {
							Object[] options ={ "ok" }; 
							int m = JOptionPane.showOptionDialog(null, "You don't have enough money", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
						}else {
							Object[] options ={ "ok" }; 
							int m = JOptionPane.showOptionDialog(null, "You successfully withdraw money from your account! And we charge you 5% service", "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
						}
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
