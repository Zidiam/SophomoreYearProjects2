import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Inventory extends JPanel{
	private WoodInventory wood;
	private LeafInventory leaf;
	private RabbitInventory rabbit;
	
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public Inventory() {
		this.setPreferredSize(new Dimension(550, 700));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		setupComponents();
		
		timerSetup();
	}
	
	private void setupComponents() {
		wood = new WoodInventory();
		rabbit = new RabbitInventory();
		leaf = new LeafInventory();
		add(wood);
		add(leaf);
		add(rabbit);
	}
	
	private void updateLocation() {
		int x = 0;
		int y = 0;
		if(wood.isActive() == true) {
			wood.setBounds(x, y, 75, 75);
			x += 75;
		}
		else {
			//set it outside of the screen
			wood.setBounds(-100, -100, 75, 75);
		}
		
		if(rabbit.isActive() == true) {
			rabbit.setBounds(x, y, 75, 75);
			x += 75;
		}
		else {
			//set it outside of the screen
			rabbit.setBounds(-100, -100, 75, 75);
		}
		
		if(leaf.isActive() == true) {
			leaf.setBounds(x, y, 75, 75);
			x += 75;
		}
		else {
			//set it outside of the screen
			leaf.setBounds(-100, -100, 75, 75);
		}
		
		
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateLocation();
		}
	}
	
}
