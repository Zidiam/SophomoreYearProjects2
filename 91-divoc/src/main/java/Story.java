import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Story extends JPanel{
	private JLabel storyL;
	private ArrayList<String> storyList;
	private int max = 1;
	private Timer storyTimer;
	private int storySpeed = 1000;
	private CampFire campfire;
	
	public Story(CampFire campfire) {
		this.setPreferredSize(new Dimension(575, 75));
		this.setBackground(Color.BLACK);
		
		this.setLayout(null);
		
		this.campfire = campfire;
		
		setUpStory();
		setupComponents();
		timerSetup();
	}
	
	private void timerSetup() {
		storyTimer = new Timer(storySpeed, new StoryListener());
		storyTimer.start(); 
	}
	
	private void setUpStory() {
		storyList = new ArrayList<String>();
		storyList.add("The area around you is lit");
		storyList.add("The ground around you is sticky and smells funny");
		storyList.add("The ground around you is sticky and smells funny");
	}
	
	private void setupComponents() {
		storyL = new JLabel("Click the light to light up the area around you");
		
		storyL.setForeground(new Color(255, 255, 255, 255));
		
		storyL.setBounds(0, 0, 550, 25);
		
		add(storyL);
	}
	
	public void updateStory() {
		if(campfire.isLit() == false) {
			storyL.setText("As the light fades so does your life");
		}
		else {
			Random rand = new Random();
			storyL.setText(storyList.get(rand.nextInt(max)));
		}
	}
	
	private class StoryListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateStory();
		}
	}
}
