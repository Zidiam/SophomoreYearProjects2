import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolTip;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class TradepostButton extends JPanel{
	private int checkSpeed = 1;
	private int removalSpeed = 2500;//set to 50k
	private Timer removalTimer, checkTimer;
	private JButton buildB;
	private boolean built = false;
	private int woodCost, leafCost, rabbitCost;
	private JLabel woodL, leafL, rabbitL;
	private int maxRabbit;
	
	public TradepostButton(int woodCost, int leafCost, int rabbitCost, int maxRabbit) {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(100, 100));
		this.setBackground(Color.black);

		this.maxRabbit = maxRabbit;
		this.woodCost = woodCost;
		this.leafCost = leafCost;
		this.rabbitCost = rabbitCost;
		
		timerSetup();
	}
	
	private void buildTradepost() {
		setupComponents();
	}
	
	private void timerSetup() {
		removalTimer = new Timer(removalSpeed, new RemoveListener());
		removalTimer.start(); 
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start(); 
	}
	
	public boolean isBuilt() {
		return built;
	}

	
	private void setupComponents() {
		buildB = new JButton("Tradepost");
		woodL = new JLabel(woodCost + " Wood", SwingConstants.CENTER);
		leafL = new JLabel(leafCost + " Leaves", SwingConstants.CENTER);
		rabbitL = new JLabel(rabbitCost + " Rabbits", SwingConstants.CENTER);
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.addActionListener(new ButtonListener());
		
		buildB.setBounds(0, 0, 100, 25);
		woodL.setBounds(0, 25, 100, 25);
		leafL.setBounds(0, 50, 100, 25);
		rabbitL.setBounds(0, 75, 100, 25);
		
		add(buildB);
		add(woodL);
		add(leafL);
		add(rabbitL);
		
		this.updateUI();
	}
	
	private void removeTradepost() {
		buildB.setText("Tradepost");
		woodL.setText(woodCost + " Wood");
		leafL.setText(leafCost + " Leaves");
		rabbitL.setText(rabbitCost + " Rabbits");
		
		buildB.setBackground(Color.black);
		buildB.setForeground(Color.WHITE);
		buildB.setBorderPainted(true);
		buildB.setFocusable(false);
		
		buildB.setLocation(0, 0);
		buildB.setSize(100, 25);

		woodL.setForeground(Color.WHITE);
		leafL.setForeground(Color.WHITE);
		rabbitL.setForeground(Color.WHITE);
		
		buildB.setEnabled(true);
		built = false;
		People.removePeople(1);
		
		
	}
	
	private void addTradepost() {
		Tradepost.setActive(true);
		Wood.removeWood(woodCost);
		Leaf.removeLeaf(leafCost);
		Rabbit.removeRabbit(rabbitCost);
		buildB.setBackground(Color.GRAY);
		built = true;
		buildB.setText("Tradepost");
		buildB.setLocation(0, 0);
		buildB.setSize(100, 100);
		buildB.setEnabled(false);
		woodL.setText("");
		leafL.setText("");
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == buildB && buildB.getText().equals("Tradepost")) {
				if(Wood.getWood() >= woodCost && Leaf.getLeaf() >= leafCost && Rabbit.getRabbit() >= rabbitCost && built == false) {
					System.out.println("test");
					addTradepost();
				}
			}	
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//later we can destroy Tradeposts over time if an invader comes and such
			//if(built == true) {
			//	removeTradepost();	
			//}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(built == false && woodL == null && Rabbit.getOverall() >= maxRabbit) {
				buildTradepost();
			}
		}
	}
}
