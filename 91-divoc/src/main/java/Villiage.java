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
		upgradeB = new JButton("Click to upgrade the stronghold");
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
	
	private void setupComponents() {
		stronghold = new StrongholdButton();
		
		wall = new Wall(100, 100);
		
		stronghold.setBounds(245, 275, 75, 75);
		
		wall.setBounds(0, 25, 575, 700);
		
		wall.setVisible(false);

		//add(wall);
		add(stronghold);
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
			
		}
	}
	
}
