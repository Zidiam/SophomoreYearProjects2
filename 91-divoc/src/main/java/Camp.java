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

public class Camp extends JPanel{
	private CampFire campfire;
	private StoryLabel story;
	private Hut hut1, hut2, hut3, hut4;
	private Outpost outpost1, outpost2, outpost3, outpost4;
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = false;
	private BadEvent event;
	
	public Camp() {
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
		upgradeB = new JButton("Click to upgrade the camp");
		upgradeL = new JLabel("Cost: 200 Wood, 125 Leaves, 65 Rabbit", SwingConstants.CENTER);
		
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
		campfire = new CampFire();
		story = new StoryLabel();
		
		hut1 = new Hut(10, 5, 5);
		hut2 = new Hut(20, 10, 10);
		hut3 = new Hut(30, 15, 20);
		hut4 = new Hut(50, 30, 40);
		
		outpost1 = new Outpost(60, 35, 10, 5);
		outpost2 = new Outpost(80, 45, 15, 10);
		outpost3 = new Outpost(90, 55, 20, 20);
		outpost4 = new Outpost(100, 65, 30, 40);
		
		campfire.setBounds(245, 300, 75, 75);
		story.setBounds(0, 600, 575, 75);
		
		hut1.setBounds(245, 150, 75, 100);
		hut2.setBounds(365, 275, 75, 100);
		hut3.setBounds(120, 275, 75, 100);
		hut4.setBounds(245, 400, 75, 100);
		
		outpost1.setBounds(405, 100, 100, 100);
		outpost2.setBounds(405, 450, 100, 100);
		outpost3.setBounds(70, 450, 100, 100);
		outpost4.setBounds(70, 100, 100, 100);
		
		event = new BadEvent();
		event.setBounds(0, 0, 575, 700);
		add(event);
		
		add(campfire);
		add(story);
		
		add(hut1);
		add(hut2);
		add(hut3);
		add(hut4);
		
		add(outpost1);
		add(outpost2);
		add(outpost3);
		add(outpost4);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == upgradeB && Resource.allResources.get(0).get() >= 200 && Resource.allResources.get(1).get() >= 125 && Resource.allResources.get(2).get() >= 65) {
				Resource.allResources.get(0).remove(200);
				Resource.allResources.get(1).remove(125);
				Resource.allResources.get(2).remove(65);
				complete = true;
			}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(BuiltBuildings.getAmount() >= 8 && upgradeL == null) {
				setupUpgrade();
			}
			if(BuiltBuildings.getAmount() < 8 && upgradeL != null) {
				destroyUpgrade();
			}
		}
	}
	
}
