import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Farm extends Building{
	private int checkSpeed = 1;
	private Timer checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, leafCost;
	private JLabel woodL, farmL, leafL;
	private int maxWood;
	
	public Farm(int woodCost, int leafCost, int maxWood) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(75, 100));
		this.setBackground(Color.black);

		this.maxWood = maxWood;
		this.woodCost = woodCost;
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
		buildB = new JButton("Build");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		leafL = new JLabel(leafCost + " Leaves", SwingConstants.CENTER);
		farmL = new JLabel("Farm", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		farmL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		farmL.setBounds(0, 0, 75, 25);
		buildB.setBounds(0, 25, 75, 25);
		woodL.setBounds(0, 50, 75, 25);
		leafL.setBounds(0, 75, 75, 25);
		
		add(buildB);
		add(woodL);
		add(farmL);
		add(leafL);
		
		this.updateUI();
	}
	
	public void remove() {
		buildB.setText("Build");
		woodL.setText(woodCost + " Wood");
		leafL.setText(leafCost + " Leaves");
		farmL.setText("Farm");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setSize(75, 25);
		
		farmL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		Resource.allResources.get(3).removeMultiplier(1);
		
	}
	
	private void add() {
		Resource.allResources.get(0).remove(woodCost);
		Resource.allResources.get(1).remove(leafCost);
		Resource.allResources.get(3).addMultiplier(1);
		Resource.allResources.get(3).setActive(true);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Farm");
		buildB.setSize(75, 75);
		buildB.setEnabled(false);
		woodL.setText("");
		farmL.setText("");
		leafL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Build")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(1).get() >= leafCost && built == false) {
					add();
				}
			}	
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Resource.allResources.get(0).getUsed() >= maxWood) {
				build();
			}
		}
	}
}
