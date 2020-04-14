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
		upgradeB = new JButton("Click to upgrade the camp");
		upgradeL = new JLabel("Cost: 1000 Wood, 650 Leaves, 400 Rabbit, 200 Deer, 10 Bear", SwingConstants.CENTER);
		
		upgradeB.setBackground(Color.WHITE);
		upgradeL.setForeground(Color.WHITE);
		
		upgradeB.setBounds(0, 0, 550, 25);
		upgradeL.setBounds(0, 25, 550, 25);
		
		upgradeB.addActionListener(new ButtonListener());
		
		add(upgradeB);
		add(upgradeL);
		
		this.updateUI();
	}
	
	private void setupComponents() {
		farm1 = new Farm(20, 10, 10);
		farm2 = new Farm(20, 10, 10);
		farm3 = new Farm(20, 10, 10);
		farm4 = new Farm(20, 10, 10);
	
		camp = new CampButton();
		
		camp.setBounds(245, 275, 75, 75);
		farm1.setBounds(245, 125, 75, 100);
		farm2.setBounds(365, 250, 75, 100);
		farm3.setBounds(120, 250, 75, 100);
		farm4.setBounds(245, 375, 75, 100);
		
		//outpost1.setBounds(405, 75, 100, 100);
		//outpost2.setBounds(405, 425, 100, 100);
		//outpost3.setBounds(70, 425, 100, 100);
		//outpost4.setBounds(70, 75, 100, 100);
		
		add(camp);
		add(farm1);
		add(farm2);
		add(farm3);
		add(farm4);
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
//			if(upgradeB == null) {
//				setupUpgrade();
//			}
		}
	}
	
}
