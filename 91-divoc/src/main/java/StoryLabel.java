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
	private int max = 1;
	private Timer storyTimer, loadTimer;
	private int storySpeed = 1;
	private int loadSpeed = 50;
	private int opacity1 = 255;
	private int opacity2 = 255;
	private int opacity3 = 255;
	
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
		
		story1.setForeground(new Color(255, 255, 255, opacity1));
		story2.setForeground(new Color(255, 255, 255, opacity2));
		story3.setForeground(new Color(255, 255, 255, opacity3));
		
		story1.setBounds(0, 0, 550, 25);
		story2.setBounds(0, 25, 550, 25);
		story3.setBounds(0, 50, 550, 25);
		
		add(story1);
		add(story2);
		add(story3);
	}
	
	public void updateStory() {
		if(Story.getStory().size() >= 1 && !Story.getStory().get(0).equals(story1.getText())) {
			story1.setText(Story.getStory().get(0));
			opacity1 = 255;
		}
		if(Story.getStory().size() >= 2 && !Story.getStory().get(1).equals(story2.getText())) {
			story2.setText(Story.getStory().get(1));
			opacity2 = 255;
		}
		if(Story.getStory().size() >= 3 && !Story.getStory().get(2).equals(story3.getText())) {
			story3.setText(Story.getStory().get(2));
			opacity3 = 255;
		}
	}
	
	private void fadeStory() {
		if(Story.getStory().size() >= 1 && opacity1 == 0) {
			Story.removeStory(Story.getStory().get(0));
		}
		if(Story.getStory().size() >= 2 && opacity2 == 0) {
			Story.removeStory(Story.getStory().get(1));
		}
		if(Story.getStory().size() >= 3 && opacity3 == 0) {
			Story.removeStory(Story.getStory().get(2));
		}
		if(opacity1 == 0) {
			opacity1 = 255;
		}
		if(opacity2 == 0) {
			opacity2 = 255;
		}
		if(opacity3 == 0) {
			opacity3 = 255;
		}
		
		story1.setForeground(new Color(255, 255, 255, opacity1));
		story2.setForeground(new Color(255, 255, 255, opacity1));
		story3.setForeground(new Color(255, 255, 255, opacity1));
		opacity1 --;
		opacity2 --;
		opacity3 --;
		this.updateUI();
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
