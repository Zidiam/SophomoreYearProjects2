import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Forest extends JPanel{
	
	GatherWood wood;
	private JLabel people;
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public Forest() {
		this.setPreferredSize(new Dimension(550, 700));
		this.setBackground(Color.WHITE);
		
		this.setLayout(null);
		
		setupComponents();
		timerSetup();
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private void setupComponents() {
		wood = new GatherWood();
		people = new JLabel("Available People: " + People.getPeople());
		
		people.setBounds(0, 0, 550, 25);
		wood.setBounds(0, 25, 550, 25);
		
		add(people);
		add(wood);
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			people.setText("Available People: " + People.getPeople());
		}
	}
	
}
