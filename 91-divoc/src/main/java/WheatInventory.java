import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class WheatInventory extends JPanel{

	private JButton wheatB;
	private JLabel wheatL;
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public WheatInventory() {
		this.setPreferredSize(new Dimension(75, 75));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		timerSetup();
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private void setupWheat() {
		wheatB = new JButton("Wheat");
		wheatL = new JLabel("" + Wheat.getWheat());
		
		wheatB.setBounds(0, 0, 75, 75);
		
		wheatB.setBackground(new Color(0, 0, 0, 0));
		wheatB.setContentAreaFilled(false);
		
		wheatL.setBounds(0, 50, 75, 25);
		
		add(wheatL);
		add(wheatB);
		this.updateUI();
	}
	
	private void removeWheat() {
		remove(wheatL);
		remove(wheatB);
		wheatL = null;
		wheatB = null;
		this.updateUI();
	}
	
	public boolean isActive() {
		if(wheatL == null)
			return false;
		
		return true;
	}
			
	
	private void updateInventory() {
	} 
	
	private void checkWheat() {
		if(wheatL == null && Wheat.getWheat() > 0) {
			setupWheat();
		}
		if(Wheat.getWheat() <= 0 && Wheat.getOverall() > 0 && wheatL != null) {
			removeWheat();
		}
		if(wheatL != null) {
			wheatL.setText("" + Wheat.getWheat());
		}
		
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkWheat();
			//updateInventory();
		}
	}
	
}
