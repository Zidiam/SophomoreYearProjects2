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

public class Stage3 extends JPanel{
	private int speed = 75;
	private int loadSpeed = 1000;
	private int woodSpeed = 25000;
	private Timer gameTimer, woodLoadTimer, woodTimer, rabbitT;
	private boolean complete = false;
	private JLabel woodL, storyL, woodLoadL, rabbitLoadL, redL, rabbitL;
	private JButton woodCollectB, rabbitCollectB;
	private int wood = 10;
	private int rabbit = 1;
	private ArrayList<String> loadsW, story, red, loadsR;
	
	public Stage3() {
		this.setLayout(null);
		
		setupStory();
		setupComponents();
		setupLoad();
		
		this.setBackground(Color.white);
		gameTimer = new Timer(speed, new GameListener());
		gameTimer.start();
		woodLoadTimer = new Timer(loadSpeed, new WoodLoadListener());
		woodLoadTimer.start();
		woodTimer = new Timer(woodSpeed, new WoodListener());
		woodTimer.start(); 
	}
	
	private void setupStory() {
		story = new ArrayList<String>();
		story.add("You spot a bunny with ragged hair and an extra ear");
		story.add("The tree's have orange oily bumps on them");
		story.add("Wood feel soft but looks hard.");
		story.add("A breeze with the smell of death rushes past you");	
		
		red = new ArrayList<String>();
		red.add("You grow hungry and spot a rabid bunny with green dots all over it");
	}
	
	private void setupLoad() {
		loadsW = new ArrayList<String>();
		loadsW.add("[      ]");
		loadsW.add("[[]    ]");
		loadsW.add("[[][]  ]");
		loadsW.add("[[][][]]");
		
		loadsR = new ArrayList<String>();
		
		loadsR = loadsW.clone();
		
		woodLoadL = new JLabel(loadsW.get(0));
		
		
		
		woodLoadL.setBounds(250, 25, 125, 25);
		
		add(woodLoadL);
	}
	
	private void setupComponents() {
		woodL = new JLabel("Wood: " + wood);
		storyL = new JLabel(story.get(0));
		woodCollectB = new JButton("Gather");
		redL = new JLabel(red.get(0));
		rabbitL = new JLabel("Rabbit: " + rabbit);
		rabbitCollectB = new JButton("Hunt");
		
		woodL.setBounds(0, 25, 75, 25);
		storyL.setBounds(0, 675, 500, 25);
		woodCollectB.setBounds(425, 25, 100, 25);
		redL.setBounds(0, 125, 500, 25);
		rabbitL.setBounds(0, 50, 500, 25);
		rabbitCollectB.setBounds(425, 50, 100, 25);
		
		woodCollectB.setBackground(Color.WHITE);
		rabbitCollectB.setBackground(Color.WHITE);
		redL.setForeground(Color.RED);
		
		woodCollectB.addActionListener(new ButtonListener());
		rabbitCollectB.addActionListener(new ButtonListener());
		
		add(woodL);
		add(storyL);
		add(woodCollectB);
		add(redL);
		add(rabbitL);
		add(rabbitCollectB);
	}
	
	private void removeWood() {
		if(wood > 0) {
			wood --;
			woodL.setText("Wood: " + wood);
		}
	}
	
	private void load() {
		System.out.println(woodLoadL.getText());
		if(woodLoadL.getText() != loadsW.get(0)) {
			if(woodLoadL.getText() == loadsW.get(loadsW.size()-1)) {
				collectWood();
				Random rand = new Random();
				if(rand.nextBoolean()) {
					storyL.setText(story.get(rand.nextInt(story.size())));
				}
				woodLoadL.setText(loadsW.get(0));
			}
			else {
				woodLoadL.setText(loadsW.get(loadsW.indexOf(woodLoadL.getText()) + 1));
			}
		}
	}
	
	private void collectWood() {
		wood  ++;
		woodL.setText("Wood: " + wood);
	}
	
	private void update() {
		if(wood >= 100)
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
			if(event.getSource() == woodCollectB) {
				if(woodLoadL.getText() == loadsW.get(0)) {
					woodLoadL.setText(loadsW.get(1));
				}
			}
		}
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			update();
			if(woodLoadL.getText() != loadsW.get(0)) {
				
			}
		}
	}
	
	private class WoodListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			removeWood();
		}
	}
	
	private class WoodLoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(woodLoadL.getText() != loadsW.get(0)) {
				load();
			}
		}
	}
}
