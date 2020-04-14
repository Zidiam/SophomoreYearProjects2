import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class divocMainPanel extends JPanel{
	
	private final int WIDTH = 575, HEIGHT = 700;
	private int speed = 75;
	private Timer timer;
	private Camp camp;
	private JTabbedPane tabs;
	private Inventory inventory;
	private Forest forest;
	private People people;
	private StrongHold stronghold;
	private Villiage villiage;
	private boolean admin = true;
	private Tradepost tradepost;
	private Resource wood, leaf, rabbit, wheat, stone;
	
	public divocMainPanel() {
		timer = new Timer(speed, new GameListener());
		
		setupValues();
		setupComponents();
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		timer.start();
		
	}
	
	private void setupValues() {
		if(admin == false) {
			people = new People(1);
			wood = new Resource("Wood", 0, 0);
			leaf = new Resource("Leaf", 0, 0);
			wood.setActive(true);
			leaf.setActive(true);
			rabbit = new Resource("Rabbit", 0, 0);
			wheat = new Resource("Wheat", 0, 0);
			stone = new Resource("Stone", 0, 0);
		}
		if(admin == true) {
			Tradepost.setActive(true);
			people = new People(1);
			wood = new Resource("Wood", 10000, 10000);
			wood.addUsed(10000);
			leaf = new Resource("Leaf", 10000, 10000);
			wood.setActive(true);
			leaf.setActive(true);
			rabbit = new Resource("Rabbit", 10000, 10000);
			wheat = new Resource("Wheat", 10000, 10000);
			stone = new Resource("Stone", 10000, 10000);
			
		}
	}
	
	private void setupComponents() {
		tabs = new JTabbedPane();
		inventory = new Inventory();
		forest = new Forest();
		camp = new Camp();
		
		
		tabs.add("Camp", camp);
		tabs.add("Inventory", inventory);
		tabs.add("Forest", forest);
		
		add(tabs);
	}
	
	private void updateTabs() {
		if(camp.isComplete() && stronghold == null) {
			stronghold = new StrongHold();
			tabs.remove(0);
			tabs.add(stronghold, 0);
			tabs.setTitleAt(0, "Stronghold");
			tabs.setSelectedIndex(0);
		}
		if(camp.isComplete() && stronghold.isComplete() && villiage == null) {
			villiage = new Villiage();
			tabs.remove(0);
			tabs.add(villiage, 0);
			tabs.setTitleAt(0, "Villiage");
			tabs.setSelectedIndex(0);
		}
		if(Tradepost.isActive()) {
			tradepost = new Tradepost();
			tabs.add("Tradepost", tradepost);
		}
	}
	
	private class GameListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
			updateTabs();
		}
	}
}
	