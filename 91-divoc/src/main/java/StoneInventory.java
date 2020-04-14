import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StoneInventory extends JPanel{

	private JButton stoneB;
	private JLabel stoneL;
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public StoneInventory() {
		this.setPreferredSize(new Dimension(75, 75));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		timerSetup();
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private void setupStone() {
		stoneB = new JButton("Stone");
		stoneL = new JLabel("" + Stone.getStone());
		
		stoneB.setBounds(0, 0, 75, 75);
		
		stoneB.setBackground(new Color(0, 0, 0, 0));
		stoneB.setContentAreaFilled(false);
		
		stoneL.setBounds(0, 50, 75, 25);
		
		add(stoneL);
		add(stoneB);
		this.updateUI();
	}
	
	private void removeStone() {
		remove(stoneL);
		remove(stoneB);
		stoneL = null;
		stoneB = null;
		this.updateUI();
	}
	
	public boolean isActive() {
		if(stoneL == null)
			return false;
		
		return true;
	}
			
	
	private void updateInventory() {
	} 
	
	private void checkStone() {
		if(stoneL == null && Stone.getStone() > 0) {
			setupStone();
		}
		if(Stone.getStone() <= 0 && Stone.getOverall() > 0 && stoneL != null) {
			removeStone();
		}
		if(stoneL != null) {
			stoneL.setText("" + Stone.getStone());
		}
		
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkStone();
			//updateInventory();
		}
	}
	
}
