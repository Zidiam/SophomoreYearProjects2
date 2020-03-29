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
	private int rabbitSpeed = 25000;
	private Timer gameTimer, woodLoadTimer, woodTimer, rabbitTimer, rabbitLoadTimer;
	private boolean complete = false;
	private JLabel woodL, storyL, woodLoadL, rabbitLoadL, redL, rabbitL;
	private JButton woodCollectB, rabbitCollectB;
	private int wood = 10;
	private int rabbit = 1;
	private ArrayList<String> loadsW, storyW, red, loadsR;
	
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
		rabbitLoadTimer = new Timer(loadSpeed, new RabbitLoadListener());
		rabbitLoadTimer.start();
		rabbitTimer = new Timer(rabbitSpeed, new RabbitListener());
		rabbitTimer.start(); 
	}
	
	private void setupStory() {
		storyW = new ArrayList<String>();
		storyW.add("You spot a bunny with ragged hair and an extra ear");
		storyW.add("The tree's have orange oily bumps on them");
		storyW.add("Wood feel soft but looks hard.");
		storyW.add("A breeze with the smell of death rushes past you");	
		
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
		
		loadsR = (ArrayList<String>) loadsW.clone();
		
		woodLoadL = new JLabel(loadsW.get(0));
		rabbitLoadL = new JLabel(loadsR.get(0));
		
		
		woodLoadL.setBounds(250, 25, 125, 25);
		rabbitLoadL.setBounds(250, 50, 125, 25);
		
		add(woodLoadL);
		add(rabbitLoadL);
	}
	
	private void setupComponents() {
		woodL = new JLabel("Wood: " + wood);
		storyL = new JLabel(storyW.get(0));
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
	
	private void removeRabbit() {
		if(rabbit > 0) {
			rabbit --;
			rabbitL.setText("Rabbit: " + rabbit);
		}
	}
	
	private void loadW() {
		if(woodLoadL.getText() != loadsW.get(0)) {
			if(woodLoadL.getText() == loadsW.get(loadsW.size()-1)) {
				collectWood();
				Random rand = new Random();
				if(rand.nextBoolean()) {
					storyL.setText(storyW.get(rand.nextInt(storyW.size())));
				}
				woodLoadL.setText(loadsW.get(0));
			}
			else {
				woodLoadL.setText(loadsW.get(loadsW.indexOf(woodLoadL.getText()) + 1));
			}
		}
	}
	
	private void loadR() {
		if(rabbitLoadL.getText() != loadsR.get(0)) {
			if(rabbitLoadL.getText() == loadsR.get(loadsR.size()-1)) {
				huntRabbit();
				Random rand = new Random();
				if(rand.nextBoolean()) {
					storyL.setText(storyW.get(rand.nextInt(storyW.size())));
				}
				rabbitLoadL.setText(loadsR.get(0));
			}
			else {
				rabbitLoadL.setText(loadsR.get(loadsR.indexOf(rabbitLoadL.getText()) + 1));
			}
		}
	}
	
	private void huntRabbit() {
		rabbit  ++;
		rabbitL.setText("Rabbit: " + rabbit);
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
				if(woodLoadL.getText() == loadsW.get(0) && rabbitLoadL.getText() == loadsR.get(0)) {
					woodLoadL.setText(loadsW.get(1));
				}
			}
			
			if(event.getSource() == rabbitCollectB) {
				if(rabbitLoadL.getText() == loadsR.get(0) && woodLoadL.getText() == loadsW.get(0)) {
					rabbitLoadL.setText(loadsR.get(1));
					redL.setText("");
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
	
	private class RabbitListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			removeRabbit();
		}
	}
	
	private class WoodLoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(woodLoadL.getText() != loadsW.get(0)) {
				loadW();
			}
		}
	}
	
	private class RabbitLoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(rabbitLoadL.getText() != loadsR.get(0)) {
				loadR();
			}
		}
	}
}
