import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class BadEvent extends JPanel{
	private Timer reloadTimer;
	public static Timer checkTimer;
	private int eventTime = 35000;
	private boolean complete = false;
	private JLabel swordL, eventL, choiceL;
	private JButton deffendB, surrenderB, coverB, smallCoverB;
	private boolean active;
	
	public BadEvent() {
		this.setLayout(null);
		
		setupTimers();
		
		active = false;
		
		this.setOpaque(false);
		
		this.setPreferredSize(new Dimension(575, 700));
	}
	
	private void setupTimers() {
		checkTimer = new Timer(eventTime, new CheckListener());
		
		reloadTimer = new Timer(1, new ReloadListener());
		reloadTimer.start(); 
	}
	
	private void setupComponents() {
		
		swordL = new JLabel("\u2694");
		deffendB = new JButton("DEFEND");
		surrenderB = new JButton("SURRENDER");
		coverB = new JButton();
		smallCoverB = new JButton();
		eventL = new JLabel("Bandits are invading your camp!", SwingConstants.CENTER);
		choiceL = new JLabel("What will you do?", SwingConstants.CENTER);
		
		coverB.setOpaque(false);
		
		swordL.setBounds(130, 175, 200, 200);
		deffendB.setBounds(130, 400, 125, 25);
		surrenderB.setBounds(305, 400, 125, 25);
		coverB.setBounds(0, 0, 575, 700);
		smallCoverB.setBounds(130, 250, 300, 175);
		eventL.setBounds(130, 305, 300, 25);
		choiceL.setBounds(130, 355, 300, 25);
		
		swordL.setFont(new Font("Dialog", 1, 50));
		
		surrenderB.setBackground(Color.WHITE);
		deffendB.setBackground(Color.WHITE);
		
		coverB.setEnabled(false);
		coverB.setOpaque(false);
		
		smallCoverB.setBackground(Color.WHITE);
		
		deffendB.addActionListener(new ButtonListener());
		surrenderB.addActionListener(new ButtonListener());
		
		add(choiceL);
		add(eventL);
		add(swordL);
		add(deffendB);
		add(surrenderB);
		add(coverB);
		add(smallCoverB);
		active = true;
		this.updateUI();
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private void deffend() {
		for(int x = 0; x < BuiltBuildings.get().size(); x++) {
			if(BuiltBuildings.get().get(x).isBuilt()) {
				BuiltBuildings.get().get(x).remove();
				break;
			}
		}
		
		Story.addStory("Since you deffended your villiage the bandits were only able to set fire to one building!");
		
		removeEverything();
	}

	private void surrender() {	
		for(int x = 0; x < Resource.allResources.size(); x++) {
			if(Resource.allResources.get(x).isActive()) {
				Random rand = new Random();
				if(Resource.allResources.get(x).get() > 0) {
					Resource.allResources.get(x).remove(rand.nextInt(Resource.allResources.get(x).get()));
					Story.addStory("Since you surrenderd your villiage the bandits only took your resources and fled");
				}
			}
		}
		
		removeEverything();
	}
	
	private void removeEverything() {
		
		this.removeAll();
		active = false;
		this.updateUI();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == surrenderB) {
				surrender();
			}
			if(event.getSource() == deffendB) {
				deffend();
			}
		}
	}
	
	private void updateit() {
		this.updateUI();
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(active == false && BuiltBuildings.getAmount() > 1) {
				setupComponents();
			}
		}
	}
	
	private class ReloadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateit();
		}
	}
	
}
