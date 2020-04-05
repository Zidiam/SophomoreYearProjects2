import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Inventory extends JPanel{

	private JButton wood;
	private JLabel woodL;
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public Inventory() {
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
		wood = new JButton("Wood");
		woodL = new JLabel("" + Wood.getWood());
		
		wood.setBounds(0, 0, 75, 75);
		
		wood.setBackground(new Color(0, 0, 0, 0));
		wood.setContentAreaFilled(false);
		
		woodL.setBounds(0, 50, 75, 25);
		
		add(woodL);
		add(wood);
		
	}
	
	private void updateInventory() {
		woodL.setText("" + Wood.getWood());
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateInventory();
		}
	}
	
}
