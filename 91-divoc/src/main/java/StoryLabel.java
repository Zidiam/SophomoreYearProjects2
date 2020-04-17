import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class StoryLabel extends JPanel{
	private JLabel story1, story2, story3;
	private Timer storyTimer, loadTimer;
	private int storySpeed = 1;
	private int loadSpeed = 50;
	
	public StoryLabel() {
		this.setPreferredSize(new Dimension(575, 75));
		this.setBackground(Color.BLACK);
		
		this.setLayout(null);
		
		setupComponents();
		timerSetup();
	}
	
	private void timerSetup() {
		storyTimer = new Timer(storySpeed, new StoryListener());
		storyTimer.start(); 
		
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start(); 
	}
	
	private void setupComponents() {
		story1 = new JLabel("");
		story2 = new JLabel("");
		story3 = new JLabel("");
		
		story1.setForeground(new Color(255, 255, 255, 255));
		story2.setForeground(new Color(255, 255, 255, 150));
		story3.setForeground(new Color(255, 255, 255, 75));
		
		story1.setBounds(0, 0, 550, 25);
		story2.setBounds(0, 25, 550, 25);
		story3.setBounds(0, 50, 550, 25);
		
		add(story1);
		add(story2);
		add(story3);
	}
	
	public void updateStory() {
		
		if(Story.stories.size() > 3) {
			Story.stories.remove(0);
		}
		
		if(Story.stories.size() == 1) {
			story1.setText(Story.stories.get(0));
		}
		
		if(Story.stories.size() == 2) {
			story1.setText(Story.stories.get(1));
			story2.setText(Story.stories.get(0));
		}
		
		if(Story.stories.size() == 3) {
			story1.setText(Story.stories.get(2));
			story2.setText(Story.stories.get(1));
			story3.setText(Story.stories.get(0));
		}
	}
	
	private void fadeStory() {
		
	}
	
	private class StoryListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateStory();
		}
	}
	
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			fadeStory();
		}
	}
}
