import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginInterface extends JFrame{
	// 1--customer  2--manager
	private int role = -1;
	
	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}
	
	// Constructor
	public LoginInterface(ArrayList<Customer> customers, Manager manager) {
		
		// Create two panels, one for choosing role, another for logining in
		JPanel rolePanel = new JPanel();
		JPanel loginPanel = new JPanel();
		JPanel registerPanel = new JPanel();
		
		// Create a panel with CardLayout
		JPanel cards = new JPanel(new CardLayout());
		
		/*
		 * role choice
		 * */
		
		// Create the panels to place the buttons on
		rolePanel.setLayout(new GridLayout(5, 1));
		JPanel rolePanel1 = new JPanel();
        JPanel rolePanel2 = new JPanel();
        JPanel rolePanel3 = new JPanel();
        
        // Create the buttons
     	JButton btnCustomer = new JButton( "I'm Customer" );
     	JButton btnManager = new JButton( "I'm Manager" );
     	
		// Add each button to the panel
        rolePanel2.add(btnCustomer);
        rolePanel2.add(btnManager);
		
        rolePanel.add(rolePanel1);
        rolePanel.add(rolePanel2);
        rolePanel.add(rolePanel3);
		
        /*
         * login information
         * */
        
		// Create the panels to place login informations
		loginPanel.setLayout(new GridLayout(3, 1));
		JPanel loginPanel1 = new JPanel();
		JPanel loginPanel2 = new JPanel();
		JPanel loginPanel3 = new JPanel();
		
		// Create textfields and button to put login informations
		JLabel jlUsername = new JLabel("Email:");
		JLabel jlPassword = new JLabel("Password:");
		JTextField loginUsername = new JTextField(10);
		JTextField loginPassword = new JTextField(10);
		JButton btnLogin = new JButton("Login");
		JButton btnRegister = new JButton("Register");
		JButton btnBack = new JButton("Back");
		
		loginPanel1.add(jlUsername);
		loginPanel1.add(loginUsername);
		loginPanel2.add(jlPassword);
        loginPanel2.add(loginPassword);
        loginPanel3.add(btnLogin);
        loginPanel3.add(btnRegister);
        loginPanel3.add(btnBack);
        
        loginPanel.add(loginPanel1);
        loginPanel.add(loginPanel2);
        loginPanel.add(loginPanel3);
		
        
        
        
        /*
         * register information
         * */
        registerPanel.setLayout(new GridLayout(4, 1));
		JPanel registerPanel1 = new JPanel();
		JPanel registerPanel2 = new JPanel();
		JPanel registerPanel3 = new JPanel();
		JPanel registerPanel4 = new JPanel();
		
		// Create textfields and button to put buttons
		JLabel jlReName = new JLabel("Name:");
		JLabel jlReEmail = new JLabel("Email:");
		JLabel jlRePassword = new JLabel("Password:");
		JTextField jtreName = new JTextField(10);
		JTextField jtReEmail = new JTextField(10);
		JTextField jtRePassword = new JTextField(10);
		JButton btnReRegister = new JButton("Register");
		JButton btnReClear = new JButton("Clear");
		JButton btnReBack = new JButton("back");
        
		registerPanel1.add(jlReName);
		registerPanel1.add(jtreName);
		registerPanel2.add(jlReEmail);
		registerPanel2.add(jtReEmail);
		registerPanel3.add(jlRePassword);
		registerPanel3.add(jtRePassword);
		registerPanel4.add(btnReRegister);
		registerPanel4.add(btnReClear);
		registerPanel4.add(btnReBack);
		
		registerPanel.add(registerPanel1);
		registerPanel.add(registerPanel2);
		registerPanel.add(registerPanel3);
		registerPanel.add(registerPanel4);
		
		cards.add(rolePanel, "card1");
        cards.add(loginPanel, "card2");
        cards.add(registerPanel, "card3");
        
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards,"card1"); 
        add(cards);
		
		
		// Add ActionListener
		btnCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				role = 1;
				cl.show(cards, "card2");
			}
		});
		btnManager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				role = 2;
				cl.show(cards, "card2");
				btnRegister.setVisible(false);
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!loginUsername.getText().equals("") && !loginPassword.getText().equals("")) {
					String email = loginUsername.getText();
					String password = loginPassword.getText();
					if(role == 1) {
						boolean canFind = false;
						for(int i = 0;i < customers.size();i++) {
							if(email.equals(customers.get(i).getEmail()) && password.equals(customers.get(i).getPsw())) {
								canFind = true;
								CustomerInterface ci = new CustomerInterface(customers, customers.get(i), manager);
								dispose();
							}
						}
						if(!canFind) {
							Object[] options ={ "ok" }; 
							int m = JOptionPane.showOptionDialog(null, "There are maybe some mistakes in your input!", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
							loginUsername.setText("");
							loginPassword.setText("");
						}	
					}else if(role == 2) {
						if(email.equals(manager.getEmail()) && password.equals(manager.getPsw())){
							ManagerInterface mi = new ManagerInterface(customers, manager);
							dispose();
						}else {
							Object[] options ={ "ok" }; 
							int m = JOptionPane.showOptionDialog(null, "There are maybe some mistakes in your input!", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
							loginUsername.setText("");
							loginPassword.setText("");
						}
					}
				}else {
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "Please complete the blanks!", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
				}
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(cards, "card3");
			}
		});
		
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				role = -1;
				btnRegister.setVisible(true);
				cl.show(cards, "card1");
			}
		});
		
		btnReRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(!jtreName.getText().equals("") && !jtReEmail.getText().equals("") && !jtRePassword.getText().equals("")) {
					String name = jtreName.getText();
					String email = jtReEmail.getText();
					String password = jtRePassword.getText();
					Customer customer = new Customer(name, email, password);
					customers.add(customer);
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "Welcome to be one of us!", "Succeed",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
					cl.show(cards, "card2");
					jtreName.setText("");;
					jtReEmail.setText("");;
					jtRePassword.setText("");
				}else {
					Object[] options ={ "ok" }; 
					int m = JOptionPane.showOptionDialog(null, "Please complete the blanks!", "Fail",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]); 
				}
			}
		});
		
		btnReBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cl.show(cards, "card2");
			}
		});
		
		btnReClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jtreName.setText("");;
				jtReEmail.setText("");;
				jtRePassword.setText("");;
				cl.show(cards, "card3");
			}
		});
		
		setTitle("Welcome to FancyBank!");
		setSize(500,300);
		setLocationRelativeTo(null);
		setLocation( 600, 200 );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
		// Turn it on
		setVisible( true );
	}
	
}

