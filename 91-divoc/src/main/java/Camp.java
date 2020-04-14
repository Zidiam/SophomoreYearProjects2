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
	private Story story;
	private Hut hut1, hut2, hut3, hut4;
	private Outpost outpost1, outpost2, outpost3, outpost4;
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = true;
	
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
		upgradeL = new JLabel("Cost: 1000 Wood, 650 Leaves, 400 Rabbit, 200 Deer, 10 Bear", SwingConstants.CENTER);
		
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
		campfire = new CampFire();
		story = new Story(campfire);
		
		hut1 = new Hut(10, 5, 5);
		hut2 = new Hut(10, 5, 5);
		hut3 = new Hut(10, 5, 5);
		hut4 = new Hut(10, 5, 5);
		
		outpost1 = new Outpost(10, 5, 5, 5);
		outpost2 = new Outpost(10, 5, 5, 5);
		outpost3 = new Outpost(10, 5, 5, 5);
		outpost4 = new Outpost(10, 5, 5, 5);
		
		campfire.setBounds(245, 275, 75, 75);
		story.setBounds(0, 600, 575, 75);
		
		hut1.setBounds(245, 125, 75, 100);
		hut2.setBounds(365, 250, 75, 100);
		hut3.setBounds(120, 250, 75, 100);
		hut4.setBounds(245, 375, 75, 100);
		
		outpost1.setBounds(405, 75, 100, 100);
		outpost2.setBounds(405, 425, 100, 100);
		outpost3.setBounds(70, 425, 100, 100);
		outpost4.setBounds(70, 75, 100, 100);
		
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
			if(event.getSource() == upgradeB) {
				complete = true;
			}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(hut1.isBuilt() == true && hut2.isBuilt() == true && hut3.isBuilt() == true && hut4.isBuilt() == true) {
				if(outpost1.isBuilt() == true && outpost2.isBuilt() == true && outpost3.isBuilt() == true && outpost4.isBuilt() == true) {
					if(upgradeB == null) {
						setupUpgrade();
					}
				}
			}
		}
	}
	
}
