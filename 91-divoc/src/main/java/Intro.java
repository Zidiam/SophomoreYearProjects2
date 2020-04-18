import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Intro extends JPanel{
	private boolean complete = false;
	private Timer loadTimer;
	private int loadSpeed = 15;
	private int opacity = 0;
	private ArrayList<String> story;
	private JLabel storyL;
	private boolean down, up;
	private JButton skipB;
	
	public Intro() {
		this.setPreferredSize(new Dimension(575, 700));
		loadTimer = new Timer(loadSpeed, new LoadListener());
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		setupStory();
		setupComponents();
		loadTimer.start();
	}
	
	private void setupStory() {
		story = new ArrayList<String>();
		story.add("At first it was a joke.");
		story.add("No one thought it would get this far...");
		story.add("The ignorance of humanity wiped us out.");
		story.add("We thought it only effected the old.");
		story.add("We thought it was nothing to worry about.");
		story.add("We thought we would over come it.");
		story.add("WE THOUGHT WRONG");
		story.add("The year is 2021, around one year after it first started.");
		story.add("Life is not how is used to be...");
		story.add("We need to rebuild socity before no one is left.");
		story.add("Even if that means one small step at a time.");
		
		up = true;
		down = false;
	}
	
	private void setupComponents() {
		storyL = new JLabel(story.get(0), SwingConstants.CENTER);
		skipB = new JButton("Skip");
		
		storyL.setBounds(0, 325, 575, 25);
		skipB.setBounds(515, 675, 60, 25);
		
		storyL.setForeground(new Color(255,255,255,opacity));
		skipB.setBackground(Color.WHITE);
		
		skipB.addActionListener(new ButtonListener());
		
		add(storyL);
		add(skipB);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	
	private void updateStory() {
		if(opacity == 0 && down == true) {
			if(story.size() == 1) {
				complete = true;
			}
			else {
				up = true;
				down = false;
				story.remove(0);
				storyL.setText(story.get(0));
			}
		}
		
		if(complete == false) {
			if(opacity == 255 && up == true) {
				up = false;
				down = true;
			}
			
			if(up == true) {
				opacity ++;
				storyL.setForeground(new Color(255,255,255,opacity));
			}
			
			if(down == true) {
				opacity --;
				storyL.setForeground(new Color(255,255,255,opacity));
			}
		}
		
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == skipB) {
				complete = true;
			}
		}
	}
	
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateStory();
		}
	}
	
}
