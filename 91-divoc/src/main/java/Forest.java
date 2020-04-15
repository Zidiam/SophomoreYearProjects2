import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Forest extends JPanel{
	
	private JLabel people;
	private Timer gameTimer;
	private int gameSpeed = 1;
	private Gather wood, traps, farm, mines, water, ranch;
	
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
		wood = new Gather("Gatherer", "Gather", Resource.allResources.get(0), 250);
		wood.addResource(Resource.allResources.get(1));
		
		traps = new Gather("Hunter", "Hunt", Resource.allResources.get(2), 300);
		
		farm = new Gather("Farmer", "Farm", Resource.allResources.get(3), 350);
		
		mines = new Gather("Miner", "Mine", Resource.allResources.get(4), 400);
		mines.addResource(Resource.allResources.get(6));
		
		water = new Gather("Purifier", "Collect", Resource.allResources.get(5), 450);
		
		ranch = new Gather("Rancher", "Collect", Resource.allResources.get(7), 500);
		
		people = new JLabel("Available People: " + People.getPeople());
		
		people.setBounds(0, 0, 575, 25);
		wood.setBounds(0, 25, 575, 25);
		traps.setBounds(0, 50, 575, 25);
		farm.setBounds(0, 75, 575, 25);
		mines.setBounds(0, 100, 575, 25);
		water.setBounds(0, 125, 575, 25);
		ranch.setBounds(0, 150, 575, 25);
		
		add(people);
		add(wood);
		add(traps);
		add(farm);
		add(mines);
		add(water);
		add(ranch);
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			people.setText("Available People: " + People.getPeople());
		}
	}
	
}
