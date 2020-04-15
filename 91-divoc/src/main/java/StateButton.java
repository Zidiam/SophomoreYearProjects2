import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class StateButton extends JPanel{
	private JButton lightB, stateB;
	private JLabel stateL;
	private boolean lit = true;
	private int removalSpeed = 15000;
	private Timer removalTimer;
	
	public StateButton() {
		this.setPreferredSize(new Dimension(100, 100));
		
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
		stateB = new JButton();
		stateL = new JLabel("State", SwingConstants.CENTER);
		
		lightB.setBounds(25, 25, 50, 50);
		stateB.setBounds(0, 0, 100, 100);
		stateL.setBounds(0, 0, 100, 25);
		
		lightB.setFocusable(false);
		
		lightB.setBackground(Color.WHITE);
		stateB.setBackground(Color.BLACK);
		stateL.setForeground(Color.WHITE);
		
		lightB.addActionListener(new ButtonListener());
		
		stateB.setEnabled(false);
		
		add(stateL);
		add(lightB);
		add(stateB);
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
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			lightB.setFocusable(true);
			lightB.setBackground(Color.BLACK);
			lit = false;
		}
	}
	
	
}
