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

public class GatherWood extends JPanel{
	
	private JButton gatherB, addB, removeB, startB;
	private JLabel gatherersL, loadL, multiplierL;
	private int gatherers, loadSpeed, checkSpeed;
	private ArrayList<String> loadList;
	private Timer loadTimer, checkTimer;
	
	
	public GatherWood() {
		this.setPreferredSize(new Dimension(575, 25));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		gatherers = 0;
		loadSpeed = 250;
		checkSpeed = 1;
		setupLoad();
		setupComponents();
		setupTimer();
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
		gatherB = new JButton("Gather");
		addB = new JButton("Add");
		removeB = new JButton("Remove");
		gatherersL = new JLabel("Gatherers: " + gatherers);
		loadL = new JLabel(loadList.get(0));
		startB = new JButton("Click to unlock wood gathering");
		multiplierL = new JLabel(Wood.getMultiplier() + "x");
		
		gatherB.setBounds(475, 0, 100, 25);
		addB.setBounds(125, 0, 100, 25);
		removeB.setBounds(225, 0, 100, 25);
		gatherersL.setBounds(0, 0, 75, 25);
		loadL.setBounds(325, 0, 150, 25);
		startB.setBounds(0, 0, 575, 25);
		multiplierL.setBounds(100, 0, 25, 25);
		
		addB.setBackground(Color.WHITE);
		removeB.setBackground(Color.WHITE);
		startB.setBackground(Color.WHITE);
		gatherB.setBackground(Color.WHITE);
		
		loadL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadL.setForeground(new Color(0, 0, 0, 50));
		
		gatherB.addActionListener(new ButtonListener());
		addB.addActionListener(new ButtonListener());
		removeB.addActionListener(new ButtonListener());
		startB.addActionListener(new ButtonListener());
		
		add(startB);
		add(gatherB);
		add(addB);
		add(removeB);
		add(gatherersL);
		add(loadL);
		add(multiplierL);
	}
	
	private void setupTimer() {
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start();
	}
	
	private void gather() {
		Wood.addWood(gatherers);
		Leaf.addLeaf(gatherers);
	}
	
	private void checkPeople() {
		while(People.getPeople() < 0 && gatherers > 0) {
			gatherers --;
			People.addPeople(1);
		}
		gatherersL.setText("Gatherers: " + gatherers);
		multiplierL.setText(Wood.getMultiplier() + "x");
	}
	
	private void update() {
		this.updateUI();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == gatherB && gatherers > 0) {
				if(loadL.getText().equals(loadList.get(0))) {
					loadL.setText(loadList.get(1));
				}
			}
			if(event.getSource() == addB && loadL.getText().equals(loadList.get(0))) {
				if(People.getPeople() > 0) {
					gatherers ++;
					People.removePeople(1);
					gatherersL.setText("Gatherers: " + gatherers);
				}
			}
			if(event.getSource() == removeB && loadL.getText().equals(loadList.get(0))) {
				if(gatherers > 0) {
					gatherers --;
					People.addPeople(1);
					gatherersL.setText("Gatherers: " + gatherers);
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
			if(loadList.indexOf(loadL.getText()) == loadList.size()-1) {
				gather();
				loadL.setText(loadList.get(0));
			}
			if(!loadL.getText().equals(loadList.get(0))) {
				loadL.setText(loadList.get(loadList.indexOf(loadL.getText()) + 1));
			}
		}
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			checkPeople();
		}
	}
	
}
