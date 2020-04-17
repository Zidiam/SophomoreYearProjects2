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

public class Villiage extends JPanel{
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = false;
	private StrongholdButton stronghold;
	private Wall wall;
	private House house1, house2, house3, house4;
	private Well well1, well2, well3;
	private ShopButton shop;
	private StoryLabel story;
	private BadEvent event;
	
	public Villiage() {
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
		upgradeB = new JButton("Click to upgrade the villiage");
		upgradeL = new JLabel("Cost: 10000 Wood, 5550 Leaves, 2500 Rabbit, 1000 stone", SwingConstants.CENTER);
		
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
		stronghold = new StrongholdButton();
		
		shop = new ShopButton(1000, 600, 200, 120);
		story = new StoryLabel();
		
		wall = new Wall(250, 250);
		house1 = new House(500, 400, 90);
		house2 = new House(600, 500, 100);
		house3 = new House(700, 600, 110);
		house4 = new House(800, 700, 120);
		
		well1 = new Well(550, 400, 250, 14);
		well2 = new Well(650, 500, 400, 16);
		well3 = new Well(800, 650, 500, 18);
		
		stronghold.setBounds(245, 300, 75, 75);
		
		house1.setBounds(245, 150, 75, 100);
		house2.setBounds(365, 275, 75, 100);
		house3.setBounds(120, 275, 75, 100);
		house4.setBounds(245, 400, 75, 100);
		
		well1.setBounds(405, 100, 100, 100);
		well2.setBounds(405, 450, 100, 100);
		well3.setBounds(70, 450, 100, 100);
		
		shop.setBounds(70, 100, 100, 100);
		story.setBounds(0, 600, 575, 75);
		
		wall.setBounds(0, 50, 575, 700);

		event = new BadEvent();
		event.setBounds(0, 0, 575, 700);
		add(event);
		
		add(stronghold);
		add(house1);
		add(house2);
		add(house3);
		add(house4);
		
		add(well1);
		add(well2);
		add(well3);
		add(shop);
		add(story);
		
		add(wall);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == upgradeB) {
				if(Resource.allResources.get(0).get() >= 1000) {
					Resource.allResources.get(0).remove(1000);
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
