import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class ShopButton extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 2500;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, waterCost, rabbitCost;
	private JLabel woodL, waterL, rabbitL;
	private int maxWater;
	
	public ShopButton(int woodCost, int waterCost, int rabbitCost, int maxWater) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxWater = maxWater;
		this.woodCost = woodCost;
		this.waterCost = waterCost;
		this.rabbitCost = rabbitCost;
		
		timerSetup();
	}
	
	private void buildShop() {
		setupComponents();
	}
	
	private void timerSetup() {
		removalTimer = new Timer(removalSpeed, new RemoveListener());
		removalTimer.start(); 
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	public boolean isBuilt() {
		return built;
	}

	
	private void setupComponents() {
		buildB = new JButton("Shop");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		waterL = new JLabel(waterCost + " Water", SwingConstants.CENTER);
		rabbitL = new JLabel(rabbitCost + " Rabbits", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		waterL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		waterL.setBounds(0, 50, 100, 25);
		rabbitL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(waterL);
		add(rabbitL);
		
		this.updateUI();
	}
	
	private void removeShop() {
		buildB.setText("Shop");
		woodL.setText(woodCost + " Wood");
		waterL.setText(waterCost + " Water");
		rabbitL.setText(rabbitCost + " Rabbits");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		waterL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addShop() {
		Shop.setActive(true);
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(5).remove(waterCost);
		Resource.allResources.get(2).remove(rabbitCost);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Shop");
		buildB.setLocation(0, 0);
		buildB.setSize(100, 100);
		buildB.setEnabled(false);
		woodL.setText("");
		waterL.setText("");
		BuiltBuildings.addAmount(1);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Shop")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(5).get() >= waterCost && Resource.allResources.get(2).get() >= rabbitCost && built == false) {
					addShop();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy waterCost over time if an invader comes and such
			//if(built == true) {
			//	removeShop();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(5).getOverall() >= maxWater) {
				buildShop();
			}
		}
	}
}
