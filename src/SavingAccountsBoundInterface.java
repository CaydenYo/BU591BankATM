import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class SavingAccountsBoundInterface extends JFrame {

	private JPanel contentPane;
	private JTextField tfRMBBound;
	private JTextField tfDollarBound;
	private JTextField tfPoundBound;
	/**
	 * Create the frame.
	 */
	public SavingAccountsBoundInterface(ArrayList<Customer> customers, Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRMBBound = new JLabel("RMB bound");
		lblRMBBound.setBounds(53, 48, 87, 16);
		contentPane.add(lblRMBBound);
		
		tfRMBBound = new JTextField();
		tfRMBBound.setBounds(166, 43, 130, 26);
		contentPane.add(tfRMBBound);
		tfRMBBound.setColumns(10);
		
		JLabel lblDollarBound = new JLabel("Dollar bound");
		lblDollarBound.setBounds(53, 101, 87, 16);
		contentPane.add(lblDollarBound);
		
		tfDollarBound = new JTextField();
		tfDollarBound.setColumns(10);
		tfDollarBound.setBounds(166, 96, 130, 26);
		contentPane.add(tfDollarBound);
		
		JLabel lblPoundBound = new JLabel("Pound bound");
		lblPoundBound.setBounds(53, 157, 87, 16);
		contentPane.add(lblPoundBound);
		
		tfPoundBound = new JTextField();
		tfPoundBound.setColumns(10);
		tfPoundBound.setBounds(166, 152, 130, 26);
		contentPane.add(tfPoundBound);
		
		JButton btnComfirm = new JButton("Comfirm");
		btnComfirm.setBounds(79, 214, 117, 29);
		contentPane.add(btnComfirm);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(217, 214, 117, 29);
		contentPane.add(btnBack);
		setVisible(true);
		
		// add listener
		btnComfirm.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!tfRMBBound.getText().equals("") && !tfDollarBound.getText().equals("") && !tfPoundBound.getText().equals("")) {
					double bound0 = Double.parseDouble(tfRMBBound.getText());
					double bound1 = Double.parseDouble(tfDollarBound.getText());
					double bound2 = Double.parseDouble(tfPoundBound.getText());
					// update collaterals new value
					for(int i = 0;i < customers.size();i++) {
						for(int j = 0;j < customers.get(i).getCollateralsLoan().size();j++) {
							customers.get(i).getCollateralsLoan().get(j).updateValue(customers.get(i).getAccounts().get(0).getInterestRate());
						}
					}
					
					// update loan account
					for(int i = 0;i < customers.size();i++) {
						for(int j = 0;j < customers.get(i).getAccounts().size();j++) {
							if(customers.get(i).getAccounts().get(j).getType() == 3) {
								customers.get(i).getAccounts().get(j).updateLoanBalance();
							}
						}
					}
					
					// update saving account
					for(int i = 0;i < customers.size();i++) {
						for(int j = 0;j < customers.get(i).getAccounts().size();j++) {
							customers.get(i).getAccounts().get(j).updateSavingBalance(bound0, bound1, bound2);
						}
					}
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "Balances of saving accounts and loan account have been changed!", "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
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
				dispose();
			}
		});
	}

}
