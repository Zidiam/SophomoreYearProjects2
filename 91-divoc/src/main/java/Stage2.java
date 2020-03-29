import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.geom.AffineTransform;

public class Stage2 extends JPanel{
	private int speed = 75;
	private int loadSpeed = 1000;
	private int woodSpeed = 25000;
	private Timer gameTimer, loadTimer, woodTimer;
	private boolean complete = true;
	private JLabel woodL, storyL, loadL;
	private JButton collectB;
	private int wood = 0;
	private boolean loading = false;
	private ArrayList<String> loads, story;
	
	public Stage2() {
		this.setLayout(null);
		
		setupStory();
		setupComponents();
		setupLoad();
		
		this.setBackground(Color.white);
		gameTimer = new Timer(speed, new GameListener());
		gameTimer.start();
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
		woodTimer = new Timer(woodSpeed, new WoodListener());
		woodTimer.start();
	}
	
	private void setupStory() {
		story = new ArrayList<String>();
		story.add("You spot a bunny with ragged hair and an extra ear");
		story.add("The tree's have orange oily bumps on them");
		story.add("Wood feel soft but looks hard.");
		story.add("A breeze with the smell of death rushes past you");		
	}
	
	private void setupLoad() {
		loads = new ArrayList<String>();
		loads.add("[      ]");
		loads.add("[[]    ]");
		loads.add("[[][]  ]");
		loads.add("[[][][]]");
		
		loadL = new JLabel(loads.get(0));
		
		loadL.setBounds(250, 25, 125, 25);
		
		add(loadL);
	}
	
	private void setupComponents() {
		woodL = new JLabel("Wood: " + wood);
		storyL = new JLabel("");
		collectB = new JButton("Gather");
		
		woodL.setBounds(0, 25, 75, 25);
		storyL.setBounds(0, 675, 500, 25);
		collectB.setBounds(425, 25, 100, 25);
		
		collectB.setBackground(Color.WHITE);
		
		collectB.addActionListener(new ButtonListener());
		
		add(woodL);
		add(storyL);
		add(collectB);
		
	}
	
	private void removeWood() {
		if(wood > 0) {
			wood --;
			woodL.setText("Wood: " + wood);
		}
	}
	
	private void load() {
		int location = loads.indexOf(loadL.getText());
		if(location == loads.size()-1) {
			loading = false;
			loadL.setText(loads.get(0));
			collectWood();
			Random rand = new Random();
			if(rand.nextBoolean()) {
				storyL.setText(story.get(rand.nextInt(story.size())));
			}
		}
		else{
			location ++;
			loadL.setText(loads.get(location));
		}
		System.out.println(loadL.getText());
	}
	
	private void collectWood() {
		wood  ++;
		woodL.setText("Wood: " + wood);
	}
	
	private void update() {
		if(wood >= 10)
			complete = true;
		this.updateUI();
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public int getWood() {
		return wood;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == collectB) {
				loading = true;
			}
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
			update();
		}
	}
	
	private class WoodListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
			removeWood();
		}
	}
	
	private class LoadListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
			if(loading == true)
				load();
		}
	}
}
