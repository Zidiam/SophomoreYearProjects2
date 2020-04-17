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

public class StateBuy extends Building{
	private int checkSpeed = 1;
	private Timer checkTimer;
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
		
		BuiltBuildings.add(this);
		
		timerSetup();
	}
	
	private void build() {
		setupComponents();
	}
	
	private void timerSetup() {
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
	
	public void remove() {
		remove(state);
		
		add(buildB);
		add(woodL);
		add(rabbitL);
		add(waterL);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(25);
		
	}
	
	private void add() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(5).remove(waterCost);
		Resource.allResources.get(2).remove(rabbitCost);
		this.removeAll();
		
		state.setBounds(0, 0, 100, 100);
		
		People.addPeople(25);
		
		this.add(state);
		built = true;
		this.updateUI();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("State")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(5).get() >= waterCost && Resource.allResources.get(2).get() >= rabbitCost && built == false) {
					add();
				}
			}	
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(8).getOverall() >= maxFish) {
				build();
			}
		}
	}
}
