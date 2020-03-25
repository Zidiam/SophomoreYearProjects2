import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SchedualMainPanel extends JPanel{
	
	JTextField loginT, passwordT;
	JButton loginB;
	JLabel loginL, passwordL, welcome;
	
	public SchedualMainPanel() {
		this.setPreferredSize(new Dimension(1200, 720));
		setupComponents();
	}
	
	public void setupComponents() {
		this.setLayout(new BorderLayout());
		JPanel components = new JPanel();
		components.setLayout(new GridLayout(3, 2));
		
		loginT = new JTextField("");
		passwordT = new JTextField("");
		
		loginL = new JLabel("Login:");
		passwordL = new JLabel("Password");
		welcome = new JLabel("Welcome to a Schedualing Program");
		
		loginB = new JButton("Login");
		
		loginB.addActionListener(new ButtonListener());
		
		components.add(loginL);
		components.add(loginT);
		components.add(passwordL);
		components.add(passwordT);
		components.add(loginB);
		
		add(welcome, BorderLayout.NORTH);
		add(components, BorderLayout.PAGE_END);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == loginB) {
				if(loginPassed(loginT.getText(), passwordT.getText())) {
					System.out.println("Test");
				}
			}
		}
	}
	
	private boolean loginPassed(String username, String password) {
		
		return false;
	}
	
}
