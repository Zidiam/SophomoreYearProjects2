import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Outro extends JPanel{
	private Timer loadTimer;
	private int loadSpeed = 15;
	private int opacity = 0;
	private ArrayList<String> story;
	private JLabel storyL;
	private boolean down, up;
	private Frame main;
	
	public Outro(Frame main) {
		this.setPreferredSize(new Dimension(575, 700));
		loadTimer = new Timer(loadSpeed, new LoadListener());
		this.setBackground(Color.BLACK);
		this.setLayout(null);
		
		this.main = main;

		
		setupStory();
		setupComponents();
		loadTimer.start();
	}
	
	private void setupStory() {
		story = new ArrayList<String>();
		story.add("As the world start to rebuild itself.");
		story.add("Everything started to work out for the better.");
		story.add("It feels like it was a century ago that our world was destroyed.");
		story.add("The ones that survived will never forget.");
		story.add("The survivers have such horrific stories.");
		story.add("The new generation hopes to not be as ignorant as the ones before.");
		story.add("In this new world things will be done differently.");
		story.add("It will prosper and make sure...");
		story.add("That what happend...");
		story.add("NEVER HAPPENS AGAIN");
		story.add("");
		
		up = true;
		down = false;
	}
	
	private void setupComponents() {
		storyL = new JLabel(story.get(0), SwingConstants.CENTER);
		
		storyL.setBounds(0, 325, 575, 25);
		
		storyL.setForeground(new Color(255,255,255,opacity));
		
		add(storyL);
	}
	
	
	private void updateStory() {
		if(opacity == 0 && down == true) {
			if(story.size() == 1) {
				main.dispose();
			}
			else {
				up = true;
				down = false;
				story.remove(0);
				storyL.setText(story.get(0));
			}
		}
		
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
	
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateStory();
		}
	}
	
}
