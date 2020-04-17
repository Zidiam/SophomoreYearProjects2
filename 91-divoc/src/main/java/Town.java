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

public class Town extends JPanel{
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = false;
	private VilliageButton villiage;
	private Wall wall;
	private Apartment apartment1, apartment2, apartment3, apartment4;
	private Ranch ranch1, ranch2, ranch3, ranch4;
	private StoryLabel story;
	private BadEvent event;
	
	public Town() {
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
		upgradeB = new JButton("Click to upgrade the town");
		upgradeL = new JLabel("Cost: 2000 Wood, 1000 Leaves, 500 Rabbit, 250 stone, 150 iron, 100 water, 50 beef", SwingConstants.CENTER);
		
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
		villiage = new VilliageButton();
		
		Resource.allResources.get(6).setActive(true);
		
		story = new StoryLabel();
		
		wall = new Wall(500, 500);
		apartment1 = new Apartment(700, 100, 130);
		apartment2 = new Apartment(800, 200, 140);
		apartment3 = new Apartment(900, 300, 150);
		apartment4 = new Apartment(1000, 400, 160);
		
		ranch1 = new Ranch(1000, 800, 400, 10);
		ranch2 = new Ranch(1200, 1000, 600, 200);
		ranch3 = new Ranch(1400, 1200, 800, 300);
		ranch4 = new Ranch(1600, 1400, 1000, 500);
		
		villiage.setBounds(245, 300, 75, 75);
		story.setBounds(0, 600, 575, 75);
		
		apartment1.setBounds(245, 150, 75, 100);
		apartment2.setBounds(365, 275, 75, 100);
		apartment3.setBounds(120, 275, 75, 100);
		apartment4.setBounds(245, 400, 75, 100);
		
		ranch1.setBounds(405, 100, 100, 100);
		ranch2.setBounds(405, 450, 100, 100);
		ranch3.setBounds(70, 450, 100, 100);
		ranch4.setBounds(70, 100, 100, 100);
		
		wall.setBounds(0, 50, 575, 700);
		
		event = new BadEvent();
		event.setBounds(0, 0, 575, 700);
		add(event);
		
		add(villiage);
		add(apartment1);
		add(apartment2);
		add(apartment3);
		add(apartment4);
		
		add(ranch1);
		add(ranch2);
		add(ranch3);
		add(ranch4);
		add(story);
		
		add(wall);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == upgradeB) {
				//2000 Wood, 1000 Leaves, 500 Rabbit, 250 stone, 150 iron, 100 water, 50 beef
				if(Resource.allResources.get(0).get() >= 2000 && Resource.allResources.get(1).get() >= 1000 && Resource.allResources.get(2).get() >= 500 && Resource.allResources.get(4).get() >= 250 &&
						Resource.allResources.get(6).get() >= 150 && Resource.allResources.get(5).get() >= 100 && Resource.allResources.get(7).get() >= 50) {
					Resource.allResources.get(0).remove(2000);
					Resource.allResources.get(1).remove(1000);
					Resource.allResources.get(2).remove(500);
					Resource.allResources.get(4).remove(250);
					Resource.allResources.get(6).remove(150);
					Resource.allResources.get(5).remove(100);
					Resource.allResources.get(7).remove(50);
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
