import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Forest extends JPanel{
	
	private GatherWood wood;
	private JLabel people;
	private Timer gameTimer;
	private int gameSpeed = 1;
	private GatherTraps traps;
	private GatherFarm farm;
	
	public Forest() {
		this.setPreferredSize(new Dimension(575, 700));
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
		traps = new GatherTraps();
		farm = new GatherFarm();
		
		people = new JLabel("Available People: " + People.getPeople());
		
		people.setBounds(0, 0, 575, 25);
		wood.setBounds(0, 25, 575, 25);
		traps.setBounds(0, 50, 575, 25);
		farm.setBounds(0, 75, 575, 25);
		
		add(people);
		add(wood);
		add(traps);
		add(farm);
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			people.setText("Available People: " + People.getPeople());
		}
	}
	
}
