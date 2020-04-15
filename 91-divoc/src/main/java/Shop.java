import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Shop extends JPanel{
	
	private static boolean active = false;
	private Timer checkTimer, loadTimer;
	private int gameSpeed = 1;
	private int loadSpeed = 2500;
	private JLabel loadL, loadingL;
	private ArrayList<String> loadList;
	private ButtonShop button1, button2, button3, button4, button5, button6;
	
	public Shop() {
		this.setLayout(null);
		
		this.setBackground(Color.white);
		
		this.setupLoad();
		this.setupTimers();
		
		this.setPreferredSize(new Dimension(575, 700));
	}
	
	public static boolean isActive() {
		return active;
	}
	
	public static void setActive(boolean a) {
		active = a;
	}
	
	private void setupLoad() {
		loadList = new ArrayList<String>();
		
		String s = "";
		for(int x = 0; x < 83; x++) {
			loadList.add(s);
			s += "|";
		}
	}
	
	private void setupComponents() {
		loadL = new JLabel("Time until refresh:");
		loadingL = new JLabel(loadList.get(1));
		button1 = new ButtonShop();
		button2 = new ButtonShop();
		button3 = new ButtonShop();
		button4 = new ButtonShop();
		button5 = new ButtonShop();
		button6 = new ButtonShop();
		
		
		button1.setBounds(50, 25, 225, 175);
		button2.setBounds(300, 25, 225, 175);
		button3.setBounds(50, 225, 225, 175);
		button4.setBounds(300, 225, 225, 175);
		button5.setBounds(50, 425, 225, 175);
		button6.setBounds(300, 425, 225, 175);
		loadL.setBounds(0, 625, 575, 25);
		loadingL.setBounds(0, 645, 575, 25);
		
		loadingL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadingL.setForeground(new Color(0, 0, 0, 50));
		
		add(button1);
		add(button2);
		add(button3);
		add(button4);
		add(button5);
		add(button6);
		add(loadL);
		add(loadingL);
	}
	
	private void activate() {
		this.setupComponents();
	}
	
	private void setupTimers() {
		checkTimer = new Timer(gameSpeed, new CheckListener());
		checkTimer.start(); 
		
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start(); 
	}
	
	private void checkConversion() {
		this.updateUI();
		
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
	
	private void checkLoading() {
		if(loadingL.getText().equals(loadList.get(loadList.size()-1))) {
			loadingL.setText(loadList.get(1));
			button1.randomize();
			button2.randomize();
			button3.randomize();
			button4.randomize();
			button5.randomize();
			button6.randomize();
		}
		if(!loadingL.getText().equals(loadList.get(0))) {
			loadingL.setText(loadList.get(loadList.indexOf(loadingL.getText()) + 1));
		}
	}
	
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkLoading();
		}
	}
	
	private class ButtonShop extends JPanel{
		private JButton button, tradeB;
		private JLabel resourceL1, resourceL2, forL;
		private Resource resource1, resource2;
		private int amount1, amount2;
		
		private ButtonShop() {
			this.setPreferredSize(new Dimension(225, 175));
			
			this.setLayout(null);
			
			setupComponents();
			
		}
		
		private void setupComponents() {
			button = new JButton();
			resourceL1 = new JLabel("Resource", SwingConstants.CENTER);
			resourceL2 = new JLabel("Resource", SwingConstants.CENTER);
			forL = new JLabel("For", SwingConstants.CENTER);
			tradeB = new JButton("Purchase");
			
			button.setBounds(0, 0, 225, 175);
			resourceL1.setBounds(0, 25, 225, 25);
			resourceL2.setBounds(0, 75, 225, 25);
			forL.setBounds(0, 50, 225, 25);
			tradeB.setBounds(50, 125, 125, 25);
			
			tradeB.addActionListener(new ButtonListener());
			
			tradeB.setBackground(Color.WHITE);
			button.setBackground(Color.WHITE);
			
			button.setEnabled(false);
			
			add(resourceL1);
			add(resourceL2);
			add(forL);
			add(tradeB);
			this.randomize();
			
			add(button);
		}
		
		public void randomize() {
			tradeB.setEnabled(true);
			tradeB.setText("Purchase");
			
			Random rand = new Random();
			
			ArrayList<Resource> active = new ArrayList<Resource>();
			for(int x = 0; x < Resource.allResources.size(); x++) {
				if(Resource.allResources.get(x).isActive()) {
					active.add(Resource.allResources.get(x));
				}
			}
			
			resource1 = active.get(rand.nextInt(active.size()));
			resource2 = active.get(rand.nextInt(active.size()));
			
			amount2 = rand.nextInt(resource2.getMultiplier());
			if(amount2 == 0) {
				amount2 += 1;
			}
			amount1 = rand.nextInt(resource1.get() * amount2);
			
			resourceL1.setText(amount1 + " " + resource1.getName());
			resourceL2.setText("Add " + amount2 + "x multiplier for " + resource2.getName());
		}
		
		private void deactivateB() {
			if(resource1.get() >= amount1) {
				resource1.remove(amount1);
				resource2.addMultiplier(amount2);
				tradeB.setText("Purchased");
				tradeB.setEnabled(false);
			}
		}
		
		private class ButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource() == tradeB) {
					deactivateB();
				}
			}
		}
		
		
	}
}
