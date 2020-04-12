import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class RabbitInventory extends JPanel{

	private JButton rabbitB;
	private JLabel rabbitL;
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public RabbitInventory() {
		this.setPreferredSize(new Dimension(75, 75));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		timerSetup();
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private void setupRabbit() {
		rabbitB = new JButton("Rabbit");
		rabbitL = new JLabel("" + Rabbit.getRabbit());
		
		rabbitB.setBounds(0, 0, 75, 75);
		
		rabbitB.setBackground(new Color(0, 0, 0, 0));
		rabbitB.setContentAreaFilled(false);
		
		rabbitL.setBounds(0, 50, 75, 25);
		
		add(rabbitL);
		add(rabbitB);
		this.updateUI();
	}
	
	private void removeRabbit() {
		remove(rabbitL);
		remove(rabbitB);
		rabbitL = null;
		rabbitB = null;
		this.updateUI();
	}
	
	public boolean isActive() {
		if(rabbitL == null)
			return false;
		
		return true;
	}
			
	
	private void updateInventory() {
	} 
	
	private void checkRabbit() {
		if(rabbitL == null && Rabbit.getRabbit() > 0) {
			setupRabbit();
		}
		if(Rabbit.getRabbit() <= 0 && Rabbit.getOverall() > 0 && rabbitL != null) {
			removeRabbit();
		}
		if(rabbitL != null) {
			rabbitL.setText("" + Rabbit.getRabbit());
		}
		
		this.updateUI();
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkRabbit();
			//updateInventory();
		}
	}
	
}
