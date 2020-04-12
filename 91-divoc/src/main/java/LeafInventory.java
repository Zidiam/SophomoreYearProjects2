import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LeafInventory extends JPanel{

	private JButton leafB;
	private JLabel leafL;
	private Timer gameTimer;
	private int gameSpeed = 1;
	
	public LeafInventory() {
		this.setPreferredSize(new Dimension(75, 75));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		timerSetup();
	}
	
	private void timerSetup() {
		gameTimer = new Timer(gameSpeed, new GameListener());
		gameTimer.start();
	}
	
	private void setupLeaf() {
		leafB = new JButton("Leaf");
		leafL = new JLabel("" + Leaf.getLeaf());
		
		leafB.setBounds(0, 0, 75, 75);
		
		leafB.setBackground(new Color(0, 0, 0, 0));
		leafB.setContentAreaFilled(false);
		
		leafL.setBounds(0, 50, 75, 25);
		
		add(leafL);
		add(leafB);
		this.updateUI();
	}
	
	private void removeLeaf() {
		remove(leafL);
		remove(leafB);
		leafL = null;
		leafB = null;
		this.updateUI();
	}
	
	public boolean isActive() {
		if(leafL == null)
			return false;
		
		return true;
	}
			
	
	private void updateInventory() {
	} 
	
	private void checkLeaf() {
		if(leafL == null && Leaf.getLeaf() > 0) {
			setupLeaf();
		}
		if(Leaf.getLeaf() <= 0 && Leaf.getOverall() > 0 && leafL != null) {
			removeLeaf();
		}
		if(leafL != null) {
			leafL.setText("" + Leaf.getLeaf());
		}
		
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkLeaf();
			//updateInventory();
		}
	}
	
}
