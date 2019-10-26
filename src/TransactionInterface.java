import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class TransactionInterface extends JFrame {
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TransactionInterface(ArrayList<Customer> customers, Customer customer, Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		
		JLabel lblFromAccountNum = new JLabel("From Account Number:");
		lblFromAccountNum.setBounds(62, 23, 152, 16);
		contentPane.add(lblFromAccountNum);
		
		JComboBox cbFromAccountNum = new JComboBox();
		for(int i = 0;i < customer.getAccounts().size();i++) {
			if(customer.getAccounts().get(i).getType() == 1) {
				cbFromAccountNum.addItem(customer.getAccounts().get(i).getAccountNum());
			}
		}
		cbFromAccountNum.setBounds(212, 19, 130, 27);
		contentPane.add(cbFromAccountNum);
		
		JLabel lblReceiverName = new JLabel("Receiver Name:");
		lblReceiverName.setBounds(62, 73, 124, 16);
		contentPane.add(lblReceiverName);
		
		JTextField textFieldReceiverName = new JTextField();
		textFieldReceiverName.setBounds(212, 68, 130, 26);
		contentPane.add(textFieldReceiverName);
		textFieldReceiverName.setColumns(10);
		
		JLabel lblReceiverAccountNum = new JLabel("To Account Number:");
		lblReceiverAccountNum.setBounds(62, 124, 133, 16);
		contentPane.add(lblReceiverAccountNum);
		
		final JTextField textFieldReceiverAccountNum = new JTextField();
		textFieldReceiverAccountNum.setBounds(212, 119, 130, 26);
		contentPane.add(textFieldReceiverAccountNum);
		textFieldReceiverAccountNum.setColumns(10);
		
		JLabel lblWarnAccountType = new JLabel("only checking account");
		lblWarnAccountType.setBounds(347, 124, 153, 16);
		contentPane.add(lblWarnAccountType);
		
		JLabel lblHowMuch = new JLabel("Amount:");
		lblHowMuch.setBounds(62, 176, 61, 16);
		contentPane.add(lblHowMuch);
		
		JTextField textFieldHowMuch = new JTextField();
		textFieldHowMuch.setBounds(212, 171, 130, 26);
		contentPane.add(textFieldHowMuch);
		textFieldHowMuch.setColumns(10);
		
		JComboBox cbCurrencyType = new JComboBox();
		cbCurrencyType.addItem("RMB");
		cbCurrencyType.addItem("Dollar");
		cbCurrencyType.addItem("Pound");
		cbCurrencyType.setBounds(356, 172, 92, 27);
		contentPane.add(cbCurrencyType);
		
		JButton btnComfirm = new JButton("Comfirm");
		btnComfirm.setBounds(78, 226, 117, 29);
		contentPane.add(btnComfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(361, 18, 117, 29);
		contentPane.add(btnBack);
		
		JButton btnViewRecord = new JButton("View history");
		btnViewRecord.setBounds(289, 226, 117, 29);
		contentPane.add(btnViewRecord);
		
		
		// add listener
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CustomerInterface ci = new CustomerInterface(customers, customer, manager);
				dispose();
			}
		});
		
		btnComfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// find receiver
				if(!textFieldReceiverName.getText().equals("") 
						&& !textFieldReceiverAccountNum.getText().equals("") 
						&& cbFromAccountNum.getSelectedItem() != null
						&& !textFieldHowMuch.getText().equals("")) {
					String receiverName = textFieldReceiverName.getText();
					String receiverAccountNum = textFieldReceiverAccountNum.getText();
					String fromAccountNum = cbFromAccountNum.getSelectedItem().toString();
					int currencyType = cbCurrencyType.getSelectedIndex(); 
					double amount = Double.parseDouble(textFieldHowMuch.getText());
					for(int i = 0;i < customers.size();i++) {
						if(receiverName.equals(customers.get(i).getName())) {
							if(customer.transactMoney(customers.get(i), receiverAccountNum, fromAccountNum, currencyType, amount)) {
								manager.transactionReport(customer, customers.get(i), fromAccountNum, receiverAccountNum, currencyType, amount);
								Object[] options ={ "ok" }; 
								int m = JOptionPane.showOptionDialog(null, "You successfully transact money to account " + receiverAccountNum + "! And we charge you 5% service", "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
							}else {
								Object[] options ={ "ok" }; 
								int m = JOptionPane.showOptionDialog(null, "Please enter correct information!", "fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
							}
						}
					}
				}else {
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "Please enter complete information!", "fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
				}
			}
		});
		
		btnViewRecord.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TransactionHistoryInterface ti = new TransactionHistoryInterface(customer);
			}
		});
	}

}
