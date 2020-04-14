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

public class MineShaft extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 2500;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, rabbitCost, wheatCost;
	private JLabel woodL, wheatL, rabbitL;
	private int maxWheat;
	
	public MineShaft(int woodCost, int rabbitCost, int wheatCost, int maxWheat) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxWheat = maxWheat;
		this.woodCost = woodCost;
		this.wheatCost = wheatCost;
		this.rabbitCost = rabbitCost;
		
		timerSetup();
	}
	
	private void buildMineShaft() {
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
		buildB = new JButton("MineShaft");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		wheatL = new JLabel(wheatCost + " Wheat", SwingConstants.CENTER);
		rabbitL = new JLabel(rabbitCost + " Rabbits", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		wheatL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		wheatL.setBounds(0, 50, 100, 25);
		rabbitL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(wheatL);
		add(rabbitL);
		
		this.updateUI();
	}
	
	private void removeMineShaft() {
		buildB.setText("MineShaft");
		woodL.setText(woodCost + " Wood");
		wheatL.setText(wheatCost + " Leaves");
		rabbitL.setText(rabbitCost + " Rabbits");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		wheatL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addMineShaft() {
		Wood.removeWood(woodCost);
		Wheat.removeWheat(wheatCost);
		Rabbit.removeRabbit(rabbitCost);
		Stone.addMultiplier(1);
		Stone.setActive(true);
		People.addPeople(1);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("MineShaft");
		buildB.setLocation(0, 0);
		buildB.setSize(100, 100);
		buildB.setEnabled(false);
		woodL.setText("");
		wheatL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("MineShaft")) {
				if(Wood.getWood() >= woodCost && Wheat.getWheat() >= wheatCost && Rabbit.getRabbit() >= rabbitCost && built == false) {
					addMineShaft();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy MineShafts over time if an invader comes and such
			//if(built == true) {
			//	removeMineShaft();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Wheat.getOverall() >= maxWheat) {
				buildMineShaft();
			}
		}
	}
}
