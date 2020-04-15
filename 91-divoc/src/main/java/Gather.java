import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gather extends JPanel{
	
	private JButton collectB, addB, removeB, startB;
	private JLabel collectorsL, loadL, multiplierL;
	private int collectors, loadSpeed, checkSpeed;
	private ArrayList<String> loadList;
	private Timer loadTimer, checkTimer;
	private boolean isDisplayed;
	private ArrayList<Resource> resources;
	private String name, nameB;
	private int multiplier;
	
	public Gather(String name, String nameB, Resource resource, int loadSpeed) {
		this.setPreferredSize(new Dimension(575, 25));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		resources = new ArrayList<Resource>();
		resources.add(resource);
		
		this.name = name;
		this.nameB = nameB;
		collectors = 0;
		this.loadSpeed = loadSpeed;
		checkSpeed = 1;
		isDisplayed = false;
		multiplier = resource.getMultiplier();
		setupLoad();
		setupTimer();
	}
	
	public void addResource(Resource resource) {
		resources.add(resource);
	}
	
	private void setupLoad() {
		loadList = new ArrayList<String>();
		loadList.add("");
		loadList.add("|");
		loadList.add("||");
		loadList.add("|||");
		loadList.add("||||");
		loadList.add("|||||");
		loadList.add("||||||");
		loadList.add("|||||||");
		loadList.add("||||||||");
		loadList.add("|||||||||");
		loadList.add("||||||||||");
		loadList.add("|||||||||||");
		loadList.add("||||||||||||");
		loadList.add("|||||||||||||");
		loadList.add("||||||||||||||");
		loadList.add("|||||||||||||||");
		loadList.add("||||||||||||||||");
		loadList.add("|||||||||||||||||");
		loadList.add("||||||||||||||||||");
		loadList.add("|||||||||||||||||||");
		loadList.add("||||||||||||||||||||");
		loadList.add("|||||||||||||||||||||");
	}
	
	private void setupComponents() {
		collectB = new JButton(nameB);
		addB = new JButton("Add");
		removeB = new JButton("Remove");
		collectorsL = new JLabel(name + "s: " + collectors);
		loadL = new JLabel(loadList.get(0));
		startB = new JButton("Click to setup " + name + "s");
		multiplierL = new JLabel(multiplier + "x");
		
		collectB.setBounds(475, 0, 100, 25);
		addB.setBounds(125, 0, 100, 25);
		removeB.setBounds(225, 0, 100, 25);
		collectorsL.setBounds(0, 0, 100, 25);
		loadL.setBounds(325, 0, 150, 25);
		startB.setBounds(0, 0, 575, 25);
		multiplierL.setBounds(100, 0, 25, 25);
		
		addB.setBackground(Color.WHITE);
		removeB.setBackground(Color.WHITE);
		startB.setBackground(Color.WHITE);
		collectB.setBackground(Color.WHITE);
		
		loadL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadL.setForeground(new Color(0, 0, 0, 50));
		
		collectB.addActionListener(new ButtonListener());
		addB.addActionListener(new ButtonListener());
		removeB.addActionListener(new ButtonListener());
		startB.addActionListener(new ButtonListener());
		
		add(startB);
		add(collectB);
		add(addB);
		add(removeB);
		add(collectorsL);
		add(loadL);
		add(multiplierL);
	}
	
	private void setupTimer() {
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start();
	}
	
	private void add() {
		for(int z = 0; z < resources.size(); z ++) {
			if(resources.get(z).isActive()) {
				for(int y = 0; y < collectors; y++) {
					resources.get(z).add(multiplier);
				}
			}
		}
	}
	
	private void checkPeople() {
		while(People.getPeople() < 0 && collectors > 0) {
			collectors --;
			People.addPeople(1);
		}
		collectorsL.setText(name + "s: " + collectors);
		multiplierL.setText(multiplier + "x");
	}
	
	private void update() {
		this.updateUI();
	}
	
	private void checkDisplay(){
		if(isDisplayed == false && resources.get(0).isActive()) {
			setupComponents();
			isDisplayed = true;
		}
	}
	
	private void checkMultiplier() {
		for(int x = 0; x < resources.size(); x++) {
			if(resources.get(x).getMultiplier() > multiplier) {
				multiplier = resources.get(x).getMultiplier();
			}
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == collectB && collectors > 0) {
				if(loadL.getText().equals(loadList.get(0))) {
					loadL.setText(loadList.get(1));
				}
			}
			if(event.getSource() == addB && loadL.getText().equals(loadList.get(0))) {
				if(People.getPeople() > 0) {
					collectors ++;
					People.removePeople(1);
					collectorsL.setText(name + "s: " + collectors);
				}
			}
			if(event.getSource() == removeB && loadL.getText().equals(loadList.get(0))) {
				if(collectors > 0) {
					collectors --;
					People.addPeople(1);
					collectorsL.setText(name + "s: " + collectors);
				}
			}
			if(event.getSource() == startB) {
				remove(startB);
				update();
			}
		}
	}
	
	private class LoadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(isDisplayed == true) {
				if(loadList.indexOf(loadL.getText()) == loadList.size()-1) {
					add();
					loadL.setText(loadList.get(0));
				}
				if(!loadL.getText().equals(loadList.get(0))) {
					loadL.setText(loadList.get(loadList.indexOf(loadL.getText()) + 1));
				}
			}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkMultiplier();
			checkDisplay();
			if(isDisplayed == true) {
				checkPeople();
			}
			
		}
	}
	
}
