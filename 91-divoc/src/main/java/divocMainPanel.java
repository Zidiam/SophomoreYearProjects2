import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class divocMainPanel extends JPanel{
	
	private final int WIDTH = 550, HEIGHT = 700;
	private int speed = 75;
	private Timer timer;
	private Camp camp;
	private JTabbedPane tabs;
	private Inventory inventory;
	private Forest forest;
	private Wood wood;
	private People people;
	
	public divocMainPanel() {
		timer = new Timer(speed, new ReboundListener());
		
		setupComponents();
		

		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		timer.start();
		
	}
	
	private void setupComponents() {
		tabs = new JTabbedPane();
		inventory = new Inventory();
		forest = new Forest();
		wood = new Wood();
		people = new People();
		
		camp = new Camp();
		
		
		tabs.add("Camp", camp);
		tabs.add("Inventory", inventory);
		tabs.add("Forest", forest);
		
		add(tabs);
	}
	
	private class ReboundListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
		}
	}
}
	