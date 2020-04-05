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
	private int removalSpeed = 25000;
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
		removalTimer.start(); 
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
			if(event.getSource() == collectB && collectB.getText().equals("Light")) {
				if(Wood.getWood() > 0) {
					Wood.removeWood(1);
					collectB.setContentAreaFilled(false);
					collectB.setBackground(Color.WHITE);
					lit = true;
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			collectB.setText("Light");
			collectB.setContentAreaFilled(true);
			collectB.setBackground(Color.BLACK);
			lit = false;
		}
	}
}
