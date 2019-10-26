import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class CheckCustomerInterface extends JFrame {

	private JPanel contentPane;
	private int role = 2;
	/**
	 * Create the frame.
	 */
	public CheckCustomerInterface(ArrayList<Customer> customers, Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCheckCustomer = new JLabel(" Choose one customer");
		lblCheckCustomer.setBounds(165, 6, 164, 64);
		contentPane.add(lblCheckCustomer);
		
		JComboBox customerEmail = new JComboBox();
		customerEmail.setBounds(165, 82, 164, 27);
		for(int i = 0;i < customers.size();i++) {
			String username = customers.get(i).getEmail();
			customerEmail.addItem(username);
		}
		contentPane.add(customerEmail);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.setBounds(165, 139, 164, 41);
		contentPane.add(btnCheck);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(165, 210, 164, 41);
		contentPane.add(btnBack);
		setVisible(true);
		
		// add listener
		btnCheck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username = customerEmail.getSelectedItem().toString();
				for(int i = 0;i < customers.size();i++) {
					if(username.equals(customers.get(i).getEmail())) {
						AccountInterface ai = new AccountInterface(customers,customers.get(i), manager, role);
					}
				}
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ManagerInterface mi = new ManagerInterface(customers, manager);
				dispose();
			}
		});
	}
}
