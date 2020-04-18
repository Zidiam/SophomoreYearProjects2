import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;

public class Story {
	private int loadSpeed = 10000;
	private Timer loadTimer;
	
	public static ArrayList<String> stories;
	private ArrayList<String> privateStory;
	
	public Story() {
		stories = new ArrayList<String>();
		loadTimer = new Timer(loadSpeed, new LoadListener());
		setupStory();
		loadTimer.start();
		 
	}
	
	public static void addStory(String add) {
		stories.add(add);
	}
	
	private void setupStory() {
		privateStory = new ArrayList<String>();
		
		privateStory.add("A stragler walks past with their brown and worn out cloths.");
		privateStory.add("There are screams coming from the dark.");
		privateStory.add("The more the fire is lit the more people walk by and stop.");
		privateStory.add("There is no place to use the bathroom. A hole will do.");
		privateStory.add("Meat tasts kind of funny");
		privateStory.add("Will civilization ever come back?");
		privateStory.add("You hear stories of a once thriving nation");
		privateStory.add("Your mind slowly gets broken down as you get more and more crazy");
		privateStory.add("Why is everything so dark?");
		privateStory.add("Sleeping is hard when constant fear is within you.");
		privateStory.add("If only we can get out of this mess and fix the world");
		
	}
	
	private void updateStory() {
		Random rand = new Random();
		stories.add(privateStory.get(rand.nextInt(privateStory.size())));
	}
	
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateStory();
		}
	}
	
}
