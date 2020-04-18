import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class StrongholdButton extends JPanel{
	private JButton lightB, strongholdB;
	private JLabel strongholdL;
	private boolean lit = true;
	private int removalSpeed = 15000;
	private Timer removalTimer;
	
	public StrongholdButton() {
		this.setPreferredSize(new Dimension(75, 75));
		
		this.setLayout(null);
		
		setupComponents();
		timerSetup();
	}
	
	private void timerSetup() {
		removalTimer = new Timer(removalSpeed, new RemoveListener());
		removalTimer.start(); 
	}
	
	private void setupComponents() {
		lightB = new JButton();
		strongholdB = new JButton();
		strongholdL = new JLabel("Stronghold", SwingConstants.CENTER);
		
		lightB.setBounds(25, 25, 25, 25);
		strongholdB.setBounds(0, 0, 75, 75);
		strongholdL.setBounds(0, 0, 75, 25);
		
		lightB.setFocusable(false);
		
		lightB.setBackground(Color.WHITE);
		strongholdB.setBackground(Color.BLACK);
		strongholdL.setForeground(Color.WHITE);
		
		lightB.addActionListener(new ButtonListener());
		
		strongholdB.setEnabled(false);
		
		add(strongholdL);
		add(lightB);
		add(strongholdB);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == lightB && lightB.getBackground() == Color.BLACK) {
				if(Resource.allResources.get(0).get() > 0) {
					Resource.allResources.get(0).remove(1);
					Resource.allResources.get(0).addUsed(1);
					lightB.setFocusable(false);
					lightB.setBackground(Color.WHITE);
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
				lightB.setBackground(Color.BLACK);
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
