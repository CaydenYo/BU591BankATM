import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;

public class CustomerInterface extends JFrame {

	private JPanel contentPane;
	private int role = 1;
	/**
	 * Create the frame.
	 */
	public CustomerInterface(ArrayList<Customer> customers, Customer customer, Manager manager) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		JLabel lblWelcome = new JLabel(customer.getName() + ", welcome to fancy bank!");
		lblWelcome.setBounds(20, 21, 284, 16);
		contentPane.add(lblWelcome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setBounds(369, 16, 117, 29);
		contentPane.add(btnLogout);
		
		JButton btnOpenCA = new JButton("Open a checking account");
		btnOpenCA.setBounds(20, 65, 195, 49);
		contentPane.add(btnOpenCA);
		
		JButton btnOpenSA = new JButton("Open a saving account");
		btnOpenSA.setBounds(20, 126, 195, 49);
		contentPane.add(btnOpenSA);
		
		JButton btnCheckAccounts = new JButton("Check your accounts");
		btnCheckAccounts.setBounds(20, 187, 195, 49);
		contentPane.add(btnCheckAccounts);
		
		JButton btnLoan = new JButton("About loan");
		btnLoan.setBounds(291, 65, 195, 49);
		contentPane.add(btnLoan);
		
		JButton btnSaveAndWithdraw = new JButton("Save/Withdraw money");
		btnSaveAndWithdraw.setBounds(291, 126, 195, 49);
		contentPane.add(btnSaveAndWithdraw);
		
		JButton btnTransact = new JButton("Transaction");
		btnTransact.setBounds(291, 187, 195, 49);
		contentPane.add(btnTransact);
		
		//add listener
		btnLogout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoginInterface li = new LoginInterface(customers, manager);
				dispose();
			}
		});
		btnOpenCA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				customer.createAccount(1);
				Object[] options ={ "ok" }; 
				int m = JOptionPane.showOptionDialog(null, "Succeed! We will charge you $10", "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
			}
		});
		btnOpenSA.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				customer.createAccount(2);
				Object[] options ={ "ok" }; 
				int m = JOptionPane.showOptionDialog(null, "Succeed! We will charge you $10", "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
			}
		});
		btnCheckAccounts.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AccountInterface ai = new AccountInterface(customers, customer, manager, role);
				dispose();
			}
		});
		btnLoan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoanInterface li = new LoanInterface(customers, customer, manager);
				dispose();
			}
		});
		btnSaveAndWithdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MoneyOperationInterface moi = new MoneyOperationInterface(customers, customer, manager);
				dispose();
			}
		});
		btnTransact.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TransactionInterface ti = new TransactionInterface(customers, customer, manager);
				dispose();
			}
		});
	}
}
