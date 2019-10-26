import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class ManagerInterface extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ManagerInterface(ArrayList<Customer> customers, Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel(manager.getName() + ", welcome to FancyBank!");
		lblWelcome.setBounds(20, 20, 244, 16);
		contentPane.add(lblWelcome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(341, 15, 117, 29);
		contentPane.add(btnLogout);
		
		JButton btnDailyReport = new JButton("Daily report");
		btnDailyReport.setBounds(147, 58, 190, 56);
		contentPane.add(btnDailyReport);
		
		JButton btnCheckAccount = new JButton("Customer account detail");
		btnCheckAccount.setBounds(147, 126, 190, 56);
		contentPane.add(btnCheckAccount);
		
		JButton btnChargeInterest = new JButton("Charge Interest");
		btnChargeInterest.setBounds(147, 194, 190, 56);
		contentPane.add(btnChargeInterest);
		setVisible(true);
		
		// add listener
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginInterface li = new LoginInterface(customers, manager);
				dispose();
			}
		});
		
		btnDailyReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DailyReportInterface di = new DailyReportInterface(customers, manager);
				dispose();
			}
		});
		
		btnCheckAccount.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CheckCustomerInterface ci = new CheckCustomerInterface(customers, manager);
				dispose();
			}
		});
		
		btnChargeInterest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SavingAccountsBoundInterface si = new SavingAccountsBoundInterface(customers, manager);
			}
		});
		
	}
}
