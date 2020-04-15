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
import javax.swing.Timer;

public class Resource{
	public static ArrayList<Resource> allResources = new ArrayList<Resource>();
	
	private int amount, overall, multiplier, odds, used;
	private String name;
	private boolean active;
	private Item item;
	
	public Resource(String name, int amount, int overall) {
		this.name = name;
		this.amount = amount;
		this.overall = overall;
		this.multiplier = 1;
		odds = 100;
		used = 0;
		active = false;
		item = new Item(this);
		
		allResources.add(this);
	}
	
	public int get() {
		return amount;
	}
	
	public void setOdds(int odds) {
		this.odds = odds;
	}
	
	public void add(int add) {
		Random rand = new Random();
		if(rand.nextInt(100) <= odds) {
			amount += add;
			overall += add;	
		}
	}
	
	public void setActive(boolean a) {
		active = a;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void remove(int remove) {
		amount -= remove;
	}
	
	public void addUsed(int add) {
		used += add;
	}
	
	public int getUsed() {
		return used;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
	
	public void addMultiplier(int add) {
		multiplier += add;
	}
	
	public int getOverall() {
		return overall;
	}
	
	public Item getItem() {
		return item;
	}
	
	public void setBounds(int x, int y, int width, int height) {
		item.setBounds(x, y, width, height);
	}
	
	public void setStory(String s) {
		item.setStory(s);
	}
	
	private class Item extends JPanel{

		private JButton resourceB;
		private JLabel resourceL;
		private Timer gameTimer;
		private int gameSpeed = 1;
		private Resource resource;
		private String story;
		
		private Item(Resource resource) {
			this.setPreferredSize(new Dimension(75, 75));
			this.setBackground(Color.WHITE);
			this.setLayout(null);
			
			this.resource = resource;
			
			timerSetup();
		}
		
		public void setStory(String s) {
			story = s;
		}
		
		private void timerSetup() {
			gameTimer = new Timer(gameSpeed, new GameListener());
			gameTimer.start();
		}
		
		private void setupResource() {
			resourceB = new JButton(resource.getName());
			resourceL = new JLabel("" +  resource.get());
			
			resourceB.setBounds(0, 0, 75, 75);
			
			resourceB.setBackground(new Color(0, 0, 0, 0));
			resourceB.setContentAreaFilled(false);
			resourceB.addActionListener(new ButtonListener());
			
			resourceL.setBounds(0, 50, 75, 25);
			
			add(resourceL);
			add(resourceB);
			this.updateUI();
		}
		
		private void removeResource() {
			remove(resourceL);
			remove(resourceB);
			resourceL = null;
			resourceB = null;
			this.updateUI();
		}
		
		public boolean isActive() {
			if(resourceL == null)
				return false;
			
			return true;
		}
				
		
		private void checkResource() {
			if(resourceL == null && resource.get() > 0) {
				setupResource();
			}
			if(resource.get() <= 0 && resource.getOverall() > 0 && resourceL != null) {
				removeResource();
			}
			if(resourceL != null) {
				resourceL.setText("" + resource.get());
			}
			
			this.updateUI();
		}
		
		private class GameListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				checkResource();
			}
		}
		
		private class ButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource() == resourceB) {
					InventoryStory.setStory(story);
				}
			}
		}
		
	}
}
