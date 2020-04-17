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

public class House extends Building{
	private int checkSpeed = 1;
	private Timer checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, stoneCost;
	private JLabel woodL, houseL, stoneL;
	private int maxWood;
	
	public House(int woodCost, int stoneCost, int maxWood) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(75, 100));
		this.setBackground(Color.black);

		this.maxWood = maxWood;
		this.woodCost = woodCost;
		this.stoneCost = stoneCost;
		
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
		stoneL = new JLabel(stoneCost + " Stone", SwingConstants.CENTER);
		houseL = new JLabel("House", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
			
		houseL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		stoneL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		houseL.setBounds(0, 0, 75, 25);
		buildB.setBounds(0, 25, 75, 25);
		woodL.setBounds(0, 50, 75, 25);
		stoneL.setBounds(0, 75, 75, 25);
		
		add(buildB);
		add(woodL);
		add(houseL);
		add(stoneL);
		
		this.updateUI();
	}
	
	public void remove() {
		buildB.setText("Build");
		woodL.setText(woodCost + " Wood");
		stoneL.setText(stoneCost + " Stone");
		houseL.setText("House");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setSize(75, 25);
		
		houseL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		stoneL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(2);
		
		
	}
	
	private void add() {
		People.addPeople(2);
		Resource.allResources.get(4).remove(stoneCost);
		Resource.allResources.get(0).remove(woodCost);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("House");
		buildB.setSize(75, 75);
		buildB.setEnabled(false);
		woodL.setText("");
		houseL.setText("");
		stoneL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Build")) {
				if(Resource.allResources.get(0).get() >= woodCost && Resource.allResources.get(1).get() >= stoneCost && built == false) {
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
