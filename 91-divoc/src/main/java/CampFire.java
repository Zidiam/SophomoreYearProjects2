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
import javax.swing.Timer;

public class CampFire extends JPanel{
	private int loadSpeed = 500;
	private int removalSpeed = 15000;
	private Timer removalTimer;
	private JButton collectB;
	private boolean lit = false;
	
	public CampFire() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(75, 75));
		this.setBackground(Color.white);

		setupComponents();
		timerSetup();
	}
	
	private void timerSetup() {
		removalTimer = new Timer(removalSpeed, new RemoveListener());
	}
	
	public boolean isLit() {
		return lit;
	}

	
	private void setupComponents() {
		collectB = new JButton("Light");
		
		collectB.setBackground(Color.black);
		collectB.setForeground(Color.WHITE);
		collectB.setBorderPainted(false);
		collectB.setFocusable(false);
		
		collectB.addActionListener(new ButtonListener());
		
		collectB.setBounds(0, 0, 75, 75);
		
		add(collectB);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == collectB && lit == false) {
				if(Resource.allResources.get(0).get() > 0) {
					Resource.allResources.get(0).remove(1);
					Resource.allResources.get(0).addUsed(1);
					collectB.setContentAreaFilled(false);
					collectB.setBackground(Color.WHITE);
					lit = true;
					Story.addStory("The fire burns bright!");
					removalTimer.restart(); 
				}
				else {
					Story.addStory("You need to gather wood from the forest!");
					removalTimer.restart(); 	
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(lit == true) {
				collectB.setText("Light");
				collectB.setContentAreaFilled(true);
				collectB.setBackground(Color.BLACK);
				lit = false;
				Story.addStory("As the fire dims so does your life");
				BadEvent.checkTimer.restart();
			}
			if(lit == false && !BadEvent.checkTimer.isRunning()) {
				BadEvent.checkTimer.start();
			}
		}
	}
}
