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

public class Ranch extends Building{
	private int checkSpeed = 1;
	private Timer checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, leafCost, waterCost;
	private JLabel woodL, leafL, waterL;
	private int maxWater;
	
	public Ranch(int woodCost, int leafCost, int waterCost, int maxWater) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxWater = maxWater;
		this.woodCost = woodCost;
		this.waterCost = waterCost;
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
		buildB = new JButton("Ranch");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		leafL = new JLabel(leafCost + " Leaves", SwingConstants.CENTER);
		waterL = new JLabel(waterCost + " Water", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		waterL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		leafL.setBounds(0, 50, 100, 25);
		waterL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(leafL);
		add(waterL);
		
		this.updateUI();
	}
	
	public void remove() {
		buildB.setText("Ranch");
		woodL.setText(woodCost + " Wood");
		leafL.setText(leafCost + " Leaves");
		waterL.setText(waterCost + " Water");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		waterL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		Resource.allResources.get(7).removeMultiplier(1);
	}
	
	private void add() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(5).remove(waterCost);
		Resource.allResources.get(1).remove(leafCost);
		Resource.allResources.get(7).addMultiplier(1);
		Resource.allResources.get(7).setActive(true);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Ranch");
		buildB.setLocation(0, 0);
		buildB.setSize(100, 100);
		buildB.setEnabled(false);
		woodL.setText("");
		leafL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Ranch")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(5).get() >= waterCost && Resource.allResources.get(1).get() >= leafCost && built == false) {
					add();
				}
			}	
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(5).getOverall() >= maxWater) {
				build();
			}
		}
	}
}
