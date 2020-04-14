import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wall extends JPanel{
	private JButton wall, purchase;
	private JLabel cost;
	private int wood, stone;
	private boolean bought = false;
	
	public Wall(int wood, int stone) {
		this.setPreferredSize(new Dimension(575, 700));
		
		this.setLayout(null);
		
		this.setBackground(Color.BLACK);
		
		this.wood = wood;
		this.stone = stone;
				
		setupComponents();
		
	}
	
	private void setupComponents() {
		wall = new JButton();
		purchase = new JButton("Buy Walls");
		cost = new JLabel("Cost: " + wood + " Wood, " + stone + " Stone");
		
		wall.setBackground(Color.BLACK);
		wall.setEnabled(false);
		purchase.setBackground(Color.WHITE);
		cost.setForeground(Color.WHITE);
		
		purchase.setBounds(25, 0, 125, 25);
		cost.setBounds(25, 25, 575, 25);
		wall.setBounds(25, 25, 525, 525);
		
		wall.setEnabled(false);
		
		purchase.addActionListener(new ButtonListener());
		
		add(purchase);
		add(cost);
		add(wall);
		

		
	}
	
	public boolean isBought() {
		return bought;
	}
	
	private void purchased() {
		remove(cost);
		remove(purchase);
		bought = true;
		Wood.removeWood(wood);
		Stone.removeStone(stone);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == purchase && Wood.getWood() >= wood && Stone.getStone() >= stone) {
				purchased();
			}
		}
	}
	
}
