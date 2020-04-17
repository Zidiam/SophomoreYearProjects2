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

public class Apartment extends Building{
	private int checkSpeed = 1;
	private Timer checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int stoneCost, ironCost;
	private JLabel ironL, apartmentL, stoneL;
	private int maxWood;
	
	public Apartment(int ironCost, int stoneCost, int maxWood) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(75, 100));
		this.setBackground(Color.black);

		this.maxWood = maxWood;
		this.stoneCost = stoneCost;
		this.ironCost = ironCost;
		
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
		ironL = new JLabel(ironCost + " Iron", SwingConstants.CENTER);
		stoneL = new JLabel(stoneCost + " Stone", SwingConstants.CENTER);
		apartmentL = new JLabel("Apartment", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
			
		apartmentL.setForeground(Color.WHITE);
		ironL.setForeground(Color.WHITE);
		stoneL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		apartmentL.setBounds(0, 0, 75, 25);
		buildB.setBounds(0, 25, 75, 25);
		ironL.setBounds(0, 50, 75, 25);
		stoneL.setBounds(0, 75, 75, 25);
		
		add(buildB);
		add(ironL);
		add(apartmentL);
		add(stoneL);
		
		this.updateUI();
	}
	
	public void remove() {
		buildB.setText("Build");
		ironL.setText(ironCost + " Iron");
		stoneL.setText(stoneCost + " Stone");
		apartmentL.setText("Apartment");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setSize(75, 25);
		
		apartmentL.setForeground(Color.WHITE);
		ironL.setForeground(Color.WHITE);
		stoneL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(3);
		
		
	}
	
	private void add() {
		People.addPeople(3);
		Resource.allResources.get(4).remove(stoneCost);
		Resource.allResources.get(6).remove(ironCost);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Apartment");
		buildB.setSize(75, 75);
		buildB.setEnabled(false);
		ironL.setText("");
		apartmentL.setText("");
		stoneL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Build")) {
				if(Resource.allResources.get(4).get() >= stoneCost && Resource.allResources.get(6).get() >= ironCost && built == false) {
					add();
				}
			}	
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && ironL == null && Resource.allResources.get(0).getUsed() >= maxWood) {
				build();
			}
		}
	}
}
