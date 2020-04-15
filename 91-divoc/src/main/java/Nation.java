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

public class Nation extends JPanel{
	private JButton upgradeB;
	private JLabel upgradeL;
	private Timer checkTimer;
	private int gameSpeed = 1;
	private boolean complete = false;
	private StateButton state;
	private Wall wall;
	private StateBuy state1, state2, state3, state4, state5, state6, state7, state8;
	
	public Nation() {
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
		state = new StateButton();
		
		Resource.allResources.get(6).setActive(true);
		
		wall = new Wall(5000, 5000);
		state1 = new StateBuy(1250, 900, 100, 10);
		state2 = new StateBuy(1250, 900, 100, 10);
		state3 = new StateBuy(1250, 900, 100, 10);
		state4 = new StateBuy(1250, 900, 100, 10);
		state5 = new StateBuy(1250, 900, 100, 10);
		state6 = new StateBuy(1300, 1000, 200, 100);
		state7 = new StateBuy(1500, 1200, 800, 200);
		state8 = new StateBuy(2000, 1600, 1000, 400);
		
		state.setBounds(245, 300, 100, 100);
		
		state1.setBounds(245, 100, 100, 100);
		state2.setBounds(405, 300, 100, 100);
		state3.setBounds(245, 450, 100, 100);
		state4.setBounds(70, 300, 100, 100);
		
		state5.setBounds(405, 100, 100, 100);
		state6.setBounds(405, 450, 100, 100);
		state7.setBounds(70, 450, 100, 100);
		state8.setBounds(70, 100, 100, 100);
		
		wall.setBounds(0, 50, 575, 700);
		
		wall.setVisible(false);

		add(state);
		add(state1);
		add(state2);
		add(state3);
		add(state4);
		
		add(state5);
		add(state6);
		add(state7);
		add(state8);
		
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
			if(state1.isBuilt() == true && state2.isBuilt() == true && state3.isBuilt() == true && state4.isBuilt() == true) {
				if(state5.isBuilt() == true && state6.isBuilt() == true && state7.isBuilt() == true && state8.isBuilt() == true) {
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
