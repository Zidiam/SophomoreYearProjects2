import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WoodInventory extends JPanel{

	private JButton woodB;
	private JLabel woodL;
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public WoodInventory() {
		this.setPreferredSize(new Dimension(75, 75));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		timerSetup();
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private void setupWood() {
		woodB = new JButton("Wood");
		woodL = new JLabel("" + Wood.getWood());
		
		woodB.setBounds(0, 0, 75, 75);
		
		woodB.setBackground(new Color(0, 0, 0, 0));
		woodB.setContentAreaFilled(false);
		
		woodL.setBounds(0, 50, 75, 25);
		
		add(woodL);
		add(woodB);
		this.updateUI();
	}
	
	private void removeWood() {
		remove(woodL);
		remove(woodB);
		woodL = null;
		woodB = null;
		this.updateUI();
	}
	
	public boolean isActive() {
		if(woodL == null)
			return false;
		
		return true;
	}
			
	
	private void checkWood() {
		if(woodL == null && Wood.getWood() > 0) {
			setupWood();
		}
		if(Wood.getWood() <= 0 && Wood.getOverall() > 0 && woodL != null) {
			removeWood();
		}
		if(woodL != null) {
			woodL.setText("" + Wood.getWood());
		}
		
		this.updateUI();
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkWood();
		}
	}
	
}
