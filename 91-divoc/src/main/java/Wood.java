import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Wood extends JPanel{
	private int loadSpeed = 500;
	private int removalSpeed = 25000;
	private Timer loadTimer, removalTimer;
	private JLabel amountL, storyL, loadL, gatherL;
	private JButton collectB, upB, downB;
	private int wood, collectors;
	private ArrayList<String> loads, story, red;
	private String arrow;
	
	public Wood(int wood, int collectors) {
		this.wood = wood;
		this.collectors = collectors;
		
		setupWood();
	}
	
	private void setupWood() {
		this.setLayout(null);
		this.setPreferredSize(new Dimension(550, 50));
		this.setBackground(Color.white);
		
		arrow = "None";
		setupStory();
		setupLoad();
		setupComponents();
		timerSetup();
	}
	
	private void timerSetup() {
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
		removalTimer = new Timer(removalSpeed, new RemoveListener());
		removalTimer.start(); 
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
		loads = new ArrayList<String>();
		loads.add("");
		loads.add("|");
		loads.add("||");
		loads.add("|||");
		loads.add("||||");
		loads.add("|||||");
		loads.add("||||||");
		loads.add("|||||||");
		loads.add("||||||||");
		loads.add("|||||||||");
		loads.add("||||||||||");
		loads.add("|||||||||||");
		loads.add("||||||||||||");
		loads.add("|||||||||||||");
		loads.add("||||||||||||||");
		
		loadL = new JLabel(loads.get(0));
		loadL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadL.setForeground(new Color(0, 0, 0, 50));
		
		//loadL.setBounds(250, 0, 125, 25);
		loadL.setBounds(425, -1, 125, 25);
		
		add(loadL);
	}
	
	private void setupComponents() {
		amountL = new JLabel("Wood: " + wood);
		storyL = new JLabel(story.get(0));
		collectB = new JButton("Gather");
		gatherL = new JLabel("Gatherers: " + collectors);
		collectB.setBackground(Color.WHITE);
		upB = new JButton("\uA71B");
		downB = new JButton("\uA71C");
		
		upB.setBorderPainted(false);
		upB.setContentAreaFilled(false);
		upB.setFocusable(false);
		upB.setBackground(Color.WHITE);
		upB.setFont(new Font("Arial", Font.BOLD, 25));
		
		downB.setBorderPainted(false);
		downB.setContentAreaFilled(false);
		downB.setFocusable(false);
		downB.setBackground(Color.WHITE);
		downB.setFont(new Font("Arial", Font.BOLD, 25));
		
		collectB.addActionListener(new ButtonListener());
		upB.addActionListener(new ButtonListener());
		downB.addActionListener(new ButtonListener());
		
		amountL.setBounds(0, 0, 250, 25);
		collectB.setBounds(425, 0, 100, 25);
		storyL.setBounds(0, 25, 550, 25);
		gatherL.setBounds(425, 25, 100, 25);
		upB.setBounds(325, 0, 50, 50);
		downB.setBounds(375, 0, 50, 50);
		
		
		add(amountL);
		add(storyL);
		add(collectB);
		add(gatherL);
		add(upB);
		add(downB);
	}
	
	public String getArrow() {
		return arrow;
	}
	
	public int getCollectors() {
		return collectors;
	}
	
	public void resetArrow() {
		this.arrow = "None";
	}
	
	public void addCollector() {
		collectors ++;
		gatherL.setText("Gatherers: " + collectors);
	}
	
	public void removeCollector() {
		collectors --;
		gatherL.setText("Gatherers: " + collectors);
	}
	
	private void removeWood() {
		if(wood > 0) {
			wood --;
			amountL.setText("Wood: " + wood);
		}
	}
	
	private void load() {
		if(loadL.getText() != loads.get(0)) {
			if(loadL.getText() == loads.get(loads.size()-1)) {
				collectWood();
				Random rand = new Random();
				if(rand.nextBoolean()) {
					storyL.setText(story.get(rand.nextInt(story.size())));
				}
				loadL.setText(loads.get(0));
			}
			else {
				loadL.setText(loads.get(loads.indexOf(loadL.getText()) + 1));
			}
		}
	}

	private void collectWood() {
		wood += collectors;
		amountL.setText("Wood: " + wood);
	}

	
	public int getWood() {
		return wood;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == collectB) {
				if(loadL.getText() == loads.get(0) && collectors > 0) {
					loadL.setText(loads.get(1));
				}
			}
			
			if(event.getSource() == upB && loadL.getText() == loads.get(0)) {
				arrow = "up";
			}
			if(event.getSource() == downB && loadL.getText() == loads.get(0)) {
				arrow = "down";
			}
			
		}
	}
	
	private class RemoveListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			removeWood();
		}
	}
	
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(loadL.getText() != loads.get(0)) {
				load();
			}
		}
	}
}
