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
	private StoryLabel story;
	private BadEvent event;
	
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
	
	private void destroyUpgrade() {
		remove(upgradeB);
		remove(upgradeL);
		
		
		upgradeB = null;
		upgradeL = null;
	}
	
	private void setupComponents() {
		city = new CityButton();
		
		Resource.allResources.get(6).setActive(true);
		story = new StoryLabel();
		wall = new Wall(5000, 5000);
		
		int woodc = Resource.allResources.get(0).getUsed();
		
		city1 = new CityBuy(500, 400, 100, woodc + 1);
		city2 = new CityBuy(1000, 800, 200, woodc + 5);
		city3 = new CityBuy(1500, 1200, 400, woodc + 10);
		city4 = new CityBuy(2500, 1200, 600, woodc + 20);
		city5 = new CityBuy(3500, 2500, 1500, woodc + 35);
		city6 = new CityBuy(5500, 3600, 2500, woodc + 50);
		city7 = new CityBuy(8200, 5600, 3500, woodc + 75);
		city8 = new CityBuy(10000, 8000, 6000, woodc + 100);
		
		city.setBounds(245, 300, 75, 75);
		
		city1.setBounds(245, 100, 100, 100);
		city2.setBounds(405, 300, 100, 100);
		city3.setBounds(245, 450, 100, 100);
		city4.setBounds(70, 300, 100, 100);
		
		city5.setBounds(405, 100, 100, 100);
		city6.setBounds(405, 450, 100, 100);
		city7.setBounds(70, 450, 100, 100);
		city8.setBounds(70, 100, 100, 100);
		
		story.setBounds(0, 600, 575, 75);
		
		wall.setBounds(0, 50, 575, 700);
		
		event = new BadEvent();
		event.setBounds(0, 0, 575, 700);
		add(event);
		
		add(city);
		add(city1);
		add(city2);
		add(city3);
		add(city4);
		
		add(city5);
		add(city6);
		add(city7);
		add(city8);
		
		add(story);
		
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
			if(wall.isBought()) {
				if(BuiltBuildings.getAmount() >= 8 && upgradeL == null) {
					setupUpgrade();
				}
				if(BuiltBuildings.getAmount() < 8 && upgradeL != null) {
					destroyUpgrade();
				}
			}
		}
	}
}
