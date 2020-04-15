import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class State extends JPanel{
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = false;
	private CityButton city;
	private Wall wall;
	private CityBuy city1, city2, city3, city4, city5, city6, city7, city8;
	
	public State() {
		this.setLayout(null);
		
		setupComponents();
		setupTimers();
		
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(575, 700));
	}
	
	private void setupTimers() {
		checkTimer = new Timer(gameSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	private void setupUpgrade() {
		upgradeB = new JButton("Click to upgrade the state");
		upgradeL = new JLabel("Cost: 10000 Wood, 5000 Leaves, 2000 Rabbit, 1000 stone, 600 iron, 400 water, 200 beef, 100 fish", SwingConstants.CENTER);
		
		upgradeB.setBackground(Color.WHITE);
		upgradeL.setForeground(Color.WHITE);
		
		upgradeB.setBounds(0, 0, 575, 25);
		upgradeL.setBounds(0, 25, 575, 25);
		
		upgradeB.addActionListener(new ButtonListener());
		
		add(upgradeB);
		add(upgradeL);
		
		this.updateUI();
	}
	
	private void setupComponents() {
		city = new CityButton();
		
		Resource.allResources.get(6).setActive(true);
		
		wall = new Wall(5000, 5000);
		city1 = new CityBuy(1250, 900, 100, 10);
		city2 = new CityBuy(1250, 900, 100, 10);
		city3 = new CityBuy(1250, 900, 100, 10);
		city4 = new CityBuy(1250, 900, 100, 10);
		city5 = new CityBuy(1250, 900, 100, 10);
		city6 = new CityBuy(1300, 1000, 200, 100);
		city7 = new CityBuy(1500, 1200, 800, 200);
		city8 = new CityBuy(2000, 1600, 1000, 400);
		
		city.setBounds(245, 300, 75, 75);
		
		city1.setBounds(245, 100, 100, 100);
		city2.setBounds(405, 300, 100, 100);
		city3.setBounds(245, 450, 100, 100);
		city4.setBounds(70, 300, 100, 100);
		
		city5.setBounds(405, 100, 100, 100);
		city6.setBounds(405, 450, 100, 100);
		city7.setBounds(70, 450, 100, 100);
		city8.setBounds(70, 100, 100, 100);
		
		wall.setBounds(0, 50, 575, 700);
		
		wall.setVisible(false);

		add(city);
		add(city1);
		add(city2);
		add(city3);
		add(city4);
		
		add(city5);
		add(city6);
		add(city7);
		add(city8);
		
		add(wall);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == upgradeB) {
				//10000 Wood, 5000 Leaves, 2000 Rabbit, 1000 stone, 600 iron, 400 water, 200 beef, 100 fish
				if(Resource.allResources.get(0).get() >= 5000*2 && Resource.allResources.get(1).get() >= 2500*2 && Resource.allResources.get(2).get() >= 1000*2 && Resource.allResources.get(4).get() >= 500*2 &&
						Resource.allResources.get(6).get() >= 300*2 && Resource.allResources.get(5).get() >= 200*2 && Resource.allResources.get(7).get() >= 100*2 && Resource.allResources.get(8).get() >= 50*2) {
					Resource.allResources.get(0).remove(5000*2);
					Resource.allResources.get(1).remove(2500*2);
					Resource.allResources.get(2).remove(1000*2);
					Resource.allResources.get(4).remove(500*2);
					Resource.allResources.get(6).remove(300*2);
					Resource.allResources.get(5).remove(200*2);
					Resource.allResources.get(7).remove(100*2);
					Resource.allResources.get(8).remove(50*2);
					complete = true;
				}
			}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(city1.isBuilt() == true && city2.isBuilt() == true && city3.isBuilt() == true && city4.isBuilt() == true) {
				if(city5.isBuilt() == true && city6.isBuilt() == true && city7.isBuilt() == true && city8.isBuilt() == true) {
					if(wall.isVisible() == false) {
						wall.setVisible(true);
					}
					if(wall.isBought()) {
						if(upgradeB == null) {
							setupUpgrade();
						}
					}
				}
			}
		}
	}
}
