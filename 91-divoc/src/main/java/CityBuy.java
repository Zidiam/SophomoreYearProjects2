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

public class CityBuy extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 2500;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int ironCost, fishCost, beefCost;
	private JLabel ironL, fishL, beefL;
	private int maxFish;
	private CityButton city;
	
	public CityBuy(int ironCost, int fishCost, int beefCost, int maxFish) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		city = new CityButton();
		this.maxFish = maxFish;
		this.ironCost = ironCost;
		this.beefCost = beefCost;
		this.fishCost = fishCost;
		
		timerSetup();
	}
	
	private void buildCity() {
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
		buildB = new JButton("City");
		ironL = new JLabel(ironCost + " Iron", SwingConstants.CENTER);
		fishL = new JLabel(fishCost + " Fish", SwingConstants.CENTER);
		beefL = new JLabel(beefCost + " Beef", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		ironL.setForeground(Color.WHITE);
		fishL.setForeground(Color.WHITE);
		beefL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		ironL.setBounds(0, 25, 100, 25);
		fishL.setBounds(0, 50, 100, 25);
		beefL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(ironL);
		add(fishL);
		add(beefL);
		
		this.updateUI();
	}
	
	private void removeCity() {
		buildB.setText("City");
		ironL.setText(ironCost + " Iron");
		fishL.setText(fishCost + " Fish");
		beefL.setText(beefCost + " Beef");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		ironL.setForeground(Color.WHITE);
		fishL.setForeground(Color.WHITE);
		beefL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addCity() {
		Resource.allResources.get(6).remove(ironCost);
		Resource.allResources.get(7).remove(beefCost);
		Resource.allResources.get(8).remove(fishCost);
		this.removeAll();
		
		city.setBounds(0, 0, 75, 75);
		
		this.add(city);
		built = true;
		this.updateUI();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("City")) {
				if(Resource.allResources.get(6).get() >= ironCost && Resource.allResources.get(7).get() >= beefCost && Resource.allResources.get(8).get() >= fishCost && built == false) {
					addCity();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy Citys over time if an invader comes and such
			//if(built == true) {
			//	removeCity();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && ironL == null && Resource.allResources.get(8).getOverall() >= maxFish) {
				buildCity();
			}
		}
	}
}
