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

public class Boat extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 2500;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, leafCost, beefCost;
	private JLabel woodL, leafL, beefL;
	private int maxBeef;
	
	public Boat(int woodCost, int leafCost, int beefCost, int maxBeef) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxBeef = maxBeef;
		this.woodCost = woodCost;
		this.beefCost = beefCost;
		this.leafCost = leafCost;
		
		timerSetup();
	}
	
	private void buildBoat() {
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
		buildB = new JButton("Boat");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		leafL = new JLabel(leafCost + " Leaves", SwingConstants.CENTER);
		beefL = new JLabel(beefCost + " Beef", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		beefL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		leafL.setBounds(0, 50, 100, 25);
		beefL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(leafL);
		add(beefL);
		
		this.updateUI();
	}
	
	private void removeBoat() {
		buildB.setText("Boat");
		woodL.setText(woodCost + " Wood");
		leafL.setText(leafCost + " Leaves");
		beefL.setText(beefCost + " Beef");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		beefL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addBoat() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(7).remove(beefCost);
		Resource.allResources.get(1).remove(leafCost);
		Resource.allResources.get(8).addMultiplier(1);
		Resource.allResources.get(8).setActive(true);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Boat");
		buildB.setLocation(0, 0);
		buildB.setSize(100, 100);
		buildB.setEnabled(false);
		woodL.setText("");
		leafL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Boat")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(7).get() >= beefCost && Resource.allResources.get(1).get() >= leafCost && built == false) {
					addBoat();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy Boats over time if an invader comes and such
			//if(built == true) {
			//	removeBoat();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(7).getOverall() >= maxBeef) {
				buildBoat();
			}
		}
	}
}
