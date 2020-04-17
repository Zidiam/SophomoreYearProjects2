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

public class StrongHold extends JPanel{
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = false;
	private CampButton camp;
	private Farm farm1, farm2, farm3, farm4;
	private MineShaft mine1, mine2, mine3;
	private Wall wall;
	private TradepostButton tradepost;
	private StoryLabel story;
	private BadEvent event;
	
	public StrongHold() {
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
		upgradeB = new JButton("Click to upgrade the stronghold");
		upgradeL = new JLabel("Cost: 10000 Wood, 5550 Leaves, 2500 Rabbit, 1000 stone", SwingConstants.CENTER);
		
		upgradeB.setBackground(Color.WHITE);
		upgradeL.setForeground(Color.WHITE);
		
		upgradeB.setBounds(0, 0, 575, 25);
		upgradeL.setBounds(0, 25, 575, 25);
		
		upgradeB.addActionListener(new ButtonListener());
		
		add(upgradeB);
		add(upgradeL);
		
		//this.updateUI();
	}
	
	private void destroyUpgrade() {
		remove(upgradeB);
		remove(upgradeL);
		
		
		upgradeB = null;
		upgradeL = null;
	}
	
	private void setupComponents() {
		farm1 = new Farm(200, 125, 50);
		farm2 = new Farm(250, 200, 60);
		farm3 = new Farm(300, 250, 70);
		farm4 = new Farm(400, 300, 80);
	
		mine1 = new MineShaft(250, 80, 20, 5);
		mine2 = new MineShaft(300, 90, 30, 10);
		mine3 = new MineShaft(350, 100, 30, 10);
		
		wall = new Wall(100, 100);
		
		story = new StoryLabel();
		
		tradepost = new TradepostButton(260, 90, 20, 7);
		
		camp = new CampButton();
		
		camp.setBounds(245, 300, 75, 75);
		farm1.setBounds(245, 150, 75, 100);
		farm2.setBounds(365, 275, 75, 100);
		farm3.setBounds(120, 275, 75, 100);
		farm4.setBounds(245, 400, 75, 100);
		
		mine1.setBounds(405, 100, 100, 100);
		mine2.setBounds(405, 450, 100, 100);
		mine3.setBounds(70, 450, 100, 100);
		tradepost.setBounds(70, 100, 100, 100);
		
		story.setBounds(0, 600, 575, 75);
		
		wall.setBounds(0, 50, 575, 700);
		
		event = new BadEvent();
		event.setBounds(0, 0, 575, 700);
		add(event);
		
		add(camp);
		add(farm1);
		add(farm2);
		add(farm3);
		add(farm4);
		add(mine1);
		add(mine2);
		add(mine3);
		add(tradepost);
		add(story);
		
		add(wall);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == upgradeB) {
				complete = true;
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
