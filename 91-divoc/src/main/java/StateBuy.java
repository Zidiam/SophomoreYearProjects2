import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class StateBuy extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 2500;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, rabbitCost, waterCost;
	private JLabel woodL, rabbitL, waterL;
	private int maxFish;
	private StateButton state;
	
	public StateBuy(int woodCost, int rabbitCost, int waterCost, int maxFish) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		state = new StateButton();
		this.maxFish = maxFish;
		this.woodCost = woodCost;
		this.waterCost = waterCost;
		this.rabbitCost = rabbitCost;
		
		timerSetup();
	}
	
	private void buildState() {
		setupComponents();
	}
	
	private void timerSetup() {
		removalTimer = new Timer(removalSpeed, new RemoveListener());
		removalTimer.start(); 
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	public boolean isBuilt() {
		return built;
	}

	
	private void setupComponents() {
		buildB = new JButton("State");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		rabbitL = new JLabel(rabbitCost + " Rabbit", SwingConstants.CENTER);
		waterL = new JLabel(waterCost + " Water", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		waterL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		rabbitL.setBounds(0, 50, 100, 25);
		waterL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(rabbitL);
		add(waterL);
		
		this.updateUI();
	}
	
	private void removeState() {
		buildB.setText("State");
		woodL.setText(woodCost + " Wood");
		rabbitL.setText(rabbitCost + " Rabbit");
		waterL.setText(waterCost + " Water");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		waterL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addState() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(5).remove(waterCost);
		Resource.allResources.get(2).remove(rabbitCost);
		this.removeAll();
		
		state.setBounds(0, 0, 100, 100);
		
		this.add(state);
		built = true;
		this.updateUI();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("State")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(5).get() >= waterCost && Resource.allResources.get(2).get() >= rabbitCost && built == false) {
					addState();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy States over time if an invader comes and such
			//if(built == true) {
			//	removeState();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(8).getOverall() >= maxFish) {
				buildState();
			}
		}
	}
}
