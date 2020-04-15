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

public class Office extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 25000;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int ironCost, woodCost;
	private JLabel ironL, officeL, woodL;
	private int maxWood;
	
	public Office(int woodCost, int ironCost, int maxWood) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(75, 100));
		this.setBackground(Color.black);

		this.maxWood = maxWood;
		this.ironCost = ironCost;
		this.woodCost = woodCost;
		
		timerSetup();
	}
	
	private void buildOffice() {
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
		buildB = new JButton("Build");
		ironL = new JLabel(ironCost + " Iron", SwingConstants.CENTER);
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		officeL = new JLabel("Office", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
			
		officeL.setForeground(Color.WHITE);
		ironL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		officeL.setBounds(0, 0, 75, 25);
		buildB.setBounds(0, 25, 75, 25);
		ironL.setBounds(0, 50, 75, 25);
		woodL.setBounds(0, 75, 75, 25);
		
		add(buildB);
		add(ironL);
		add(officeL);
		add(woodL);
		
		this.updateUI();
	}
	
	private void removeOffice() {
		buildB.setText("Build");
		ironL.setText(ironCost + " Iron");
		woodL.setText(woodCost + " Wood");
		officeL.setText("Office");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setSize(75, 25);
		
		officeL.setForeground(Color.WHITE);
		ironL.setForeground(Color.WHITE);
		woodL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addOffice() {
		People.addPeople(5);
		Resource.allResources.get(6).remove(ironCost);
		Resource.allResources.get(0).remove(woodCost);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Office");
		buildB.setSize(75, 75);
		buildB.setEnabled(false);
		ironL.setText("");
		officeL.setText("");
		woodL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Build")) {
				if(Resource.allResources.get(6).get() >= ironCost && Resource.allResources.get(0).get() >= woodCost && built == false) {
					addOffice();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy Offices over time if an invader comes and such
			//if(built == true) {
			//	removeOffice();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && ironL == null && Resource.allResources.get(0).getUsed() >= maxWood) {
				buildOffice();
			}
		}
	}
}
