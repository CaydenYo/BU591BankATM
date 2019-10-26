import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TransactionHistoryInterface extends JFrame{
	public TransactionHistoryInterface(Customer customer) {
		setTitle("Transaction history");
		setSize(600,300);
		setLocationRelativeTo(null);
		setLocation( 600, 200 );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		// Turn it on
		setVisible(true);
		
		JPanel contentPane = new JPanel(new GridLayout(customer.getTransactions().size() + 1, 1));
		JPanel top = new JPanel(new GridLayout(1, 2));
		JPanel jpTitle = new JPanel();
		JPanel jpBack = new JPanel();
		JLabel jlTitle = new JLabel("Transaction history");
		JButton btnBack = new JButton("Back");
		jpTitle.add(jlTitle);
		jpBack.add(btnBack);
		top.add(jpTitle);
		top.add(jpBack);
		contentPane.add(top);
		
		for(int i = 0;i < customer.getTransactions().size();i++) {
			JPanel jpTransactionHistory = new JPanel();
			JLabel jlTransactionHistory = new JLabel(customer.getTransactions().get(i));
			jpTransactionHistory.add(jlTransactionHistory);
			contentPane.add(jpTransactionHistory);
		}
        
		add(contentPane);
		
        // add listener
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
}
