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

public class Well extends Building{
	private int checkSpeed = 1;
	private Timer checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, leafCost, stoneCost;
	private JLabel woodL, leafL, stoneL;
	private int maxPeople;
	
	public Well(int woodCost, int leafCost, int stoneCost, int maxPeople) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxPeople = maxPeople;
		this.woodCost = woodCost;
		this.stoneCost = stoneCost;
		this.leafCost = leafCost;
		
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
		buildB = new JButton("Well");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		leafL = new JLabel(leafCost + " Leaves", SwingConstants.CENTER);
		stoneL = new JLabel(stoneCost + " Stone", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		stoneL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		leafL.setBounds(0, 50, 100, 25);
		stoneL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(leafL);
		add(stoneL);
		
		this.updateUI();
	}
	
	public void remove() {
		buildB.setText("Well");
		woodL.setText(woodCost + " Wood");
		leafL.setText(leafCost + " Leaves");
		stoneL.setText(stoneCost + " Stone");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		stoneL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void add() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(4).remove(stoneCost);
		Resource.allResources.get(1).remove(leafCost);
		Resource.allResources.get(5).addMultiplier(1);
		Resource.allResources.get(5).setActive(true);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Well");
		buildB.setLocation(0, 0);
		buildB.setSize(100, 100);
		buildB.setEnabled(false);
		woodL.setText("");
		leafL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Well")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(4).get() >= stoneCost && Resource.allResources.get(1).get() >= leafCost && built == false) {
					add();
				}
			}	
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && People.getPeople() >= maxPeople) {
				build();
			}
		}
	}
}
