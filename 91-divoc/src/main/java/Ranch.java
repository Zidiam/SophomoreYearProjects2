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

public class Ranch extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 2500;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, leafCost, waterCost;
	private JLabel woodL, leafL, stoneL;
	private int maxWater;
	
	public Ranch(int woodCost, int leafCost, int waterCost, int maxWater) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxWater = maxWater;
		this.woodCost = woodCost;
		this.waterCost = waterCost;
		this.leafCost = leafCost;
		
		timerSetup();
	}
	
	private void buildRanch() {
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
		buildB = new JButton("Ranch");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		leafL = new JLabel(leafCost + " Leaves", SwingConstants.CENTER);
		stoneL = new JLabel(waterCost + " Water", SwingConstants.CENTER);
		
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
	
	private void removeRanch() {
		buildB.setText("Ranch");
		woodL.setText(woodCost + " Wood");
		leafL.setText(leafCost + " Leaves");
		stoneL.setText(waterCost + " Water");
		
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
	
	private void addRanch() {
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
					addRanch();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy Ranchs over time if an invader comes and such
			//if(built == true) {
			//	removeRanch();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(5).getOverall() >= maxWater) {
				buildRanch();
			}
		}
	}
}
