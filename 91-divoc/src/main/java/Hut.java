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
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Hut extends JPanel{
	private int loadSpeed = 500;
	private int removalSpeed = 25000;//set to 50k
	private Timer removalTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost;
	private JLabel costL, hutL;
	
	public Hut(int woodCost) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(75, 125));
		this.setBackground(Color.black);

		this.woodCost = woodCost;
		
		setupComponents();
		timerSetup();
	}
	
	private void timerSetup() {
		removalTimer = new Timer(removalSpeed, new RemoveListener());
		removalTimer.start(); 
	}
	
	public boolean isBuilt() {
		return built;
	}

	
	private void setupComponents() {
		buildB = new JButton("Build");
		costL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		hutL = new JLabel("HUT");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		hutL.setForeground(Color.WHITE);
		costL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 25, 75, 75);
		costL.setBounds(0, 100, 75, 25);
		hutL.setBounds(25, 0, 75, 25);
		
		add(buildB);
		add(costL);
		add(hutL);
	}
	
	private void removeHut() {
		buildB.setText("Build");
		costL.setText(woodCost + " Wood");
		hutL.setText("HUT");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		hutL.setForeground(Color.WHITE);
		costL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addHut() {
		Wood.removeWood(woodCost);
		People.addPeople(1);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("HUT");
		buildB.setEnabled(false);
		costL.setText("");
		hutL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Build")) {
				if(Wood.getWood() >= woodCost && built == false) {
					addHut();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == true) {
				removeHut();	
			}
		}
	}
}
