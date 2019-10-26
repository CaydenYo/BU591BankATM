import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class DailyReportInterface extends JFrame{
	public DailyReportInterface(ArrayList<Customer> customers, Manager manager) {
		setTitle("Daily Report");
		setSize(600,400);
		setLocationRelativeTo(null);
		setLocation( 600, 200 );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		// Turn it on
		setVisible(true);
		
		JPanel contentPane = new JPanel(new GridLayout(manager.getTransactionReport().size() + 1, 1));
		JPanel top = new JPanel(new GridLayout(1, 2));
		JPanel jpTitle = new JPanel();
		JPanel jpBack = new JPanel();
		JLabel jlTitle = new JLabel("Daily report");
		JButton btnBack = new JButton("Back");
		jpTitle.add(jlTitle);
		jpBack.add(btnBack);
		top.add(jpTitle);
		top.add(jpBack);
		contentPane.add(top);
		
		for(int i = 0;i < manager.getTransactionReport().size();i++) {
			JPanel jpDailyReport = new JPanel();
			JLabel jlDailyReport = new JLabel(manager.getTransactionReport().get(i));
			jpDailyReport.add(jlDailyReport);
			contentPane.add(jpDailyReport);
		}
        
		add(contentPane);
		
        // add listener
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
