import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Tradepost extends JPanel{
	
	private static boolean active = false;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private JTextField firstT;
	private JComboBox<String> choice1, choice2;
	private JButton convertB;
	private JLabel amountL, toL;
	private String first, second;
	
	public Tradepost() {
		this.setLayout(null);
		
		this.setBackground(Color.white);
		
		setupComponents();
		setupTimers();
		
		this.setPreferredSize(new Dimension(575, 700));
	}
	
	public static boolean isActive() {
		return active;
	}
	
	public static void setActive(boolean a) {
		active = a;
	}
	
	private void addChoices() {
		choice1.addItem("Wood");
		choice1.addItem("Leaf");
		choice1.addItem("Rabbit");
		choice1.addItem("Wheat");
		choice1.addItem("Stone");
		
		choice2.addItem("Wood");
		choice2.addItem("Leaf");
		choice2.addItem("Rabbit");
		choice2.addItem("Wheat");
		choice2.addItem("Stone");
	}
	
	private void setupComponents() {
		firstT = new JTextField("");
		choice1 = new JComboBox<String>();
		choice2 = new JComboBox<String>();
		convertB = new JButton("Convert");
		amountL = new JLabel("000000");
		toL = new JLabel("----------------->");
		
		firstT.setBounds(0, 0, 75, 25);
		choice1.setBounds(75, 0, 100, 25);
		toL.setBounds(175, 0, 75, 25);
		choice2.setBounds(250, 0, 100, 25);
		amountL.setBounds(350, 0, 75, 25);
		convertB.setBounds(425, 0, 100, 25);

		addChoices();
		
		choice1.addItemListener(new ItemChangeListener1());
		choice2.addItemListener(new ItemChangeListener2());
		
		first = "Wood";
		second = "Wood";
		
		add(firstT);
		add(choice1);
		add(toL);
		add(choice2);
		add(amountL);
		add(convertB);
	}
	
	private void activate() {
		setupComponents();
	}
	
	private void setupTimers() {
		checkTimer = new Timer(gameSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	private void checkConversion() {
		this.updateUI();
		
		System.out.println(first + second);
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(active == true) {
				activate();
				active = false;
			}
		}
	}
	
	private class ItemChangeListener1 implements ItemListener{
		public void itemStateChanged(ItemEvent event) {
			first = event.getItem().toString();
			checkConversion();
			
		}
	}
	
	private class ItemChangeListener2 implements ItemListener{
		public void itemStateChanged(ItemEvent event) {
			second = event.getItem().toString();
			checkConversion();
			
		}
	}
	
}
