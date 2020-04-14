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

public class GatherMines extends JPanel{
	
	private JButton minerB, addB, removeB, startB;
	private JLabel minersL, loadL, multiplierL;
	private int miners, loadSpeed, checkSpeed;
	private ArrayList<String> loadList;
	private Timer loadTimer, checkTimer;
	private boolean isDisplayed;
	
	public GatherMines() {
		this.setPreferredSize(new Dimension(575, 25));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		miners = 0;
		loadSpeed = 400;
		checkSpeed = 1;
		isDisplayed = false;
		setupLoad();
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
		minerB = new JButton("Mine");
		addB = new JButton("Add");
		removeB = new JButton("Remove");
		minersL = new JLabel("Miners: " + miners);
		loadL = new JLabel(loadList.get(0));
		startB = new JButton("Click to setup miners");
		multiplierL = new JLabel(Stone.getMultiplier() + "x");
		
		minerB.setBounds(475, 0, 100, 25);
		addB.setBounds(125, 0, 100, 25);
		removeB.setBounds(225, 0, 100, 25);
		minersL.setBounds(0, 0, 75, 25);
		loadL.setBounds(325, 0, 150, 25);
		startB.setBounds(0, 0, 575, 25);
		multiplierL.setBounds(100, 0, 25, 25);
		
		addB.setBackground(Color.WHITE);
		removeB.setBackground(Color.WHITE);
		startB.setBackground(Color.WHITE);
		minerB.setBackground(Color.WHITE);
		
		loadL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadL.setForeground(new Color(0, 0, 0, 50));
		
		minerB.addActionListener(new ButtonListener());
		addB.addActionListener(new ButtonListener());
		removeB.addActionListener(new ButtonListener());
		startB.addActionListener(new ButtonListener());
		
		add(startB);
		add(minerB);
		add(addB);
		add(removeB);
		add(minersL);
		add(loadL);
		add(multiplierL);
	}
	
	private void setupTimer() {
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start();
	}
	
	private void mine() {
		Stone.addStone(miners);
	}
	
	private void checkPeople() {
		while(People.getPeople() < 0 && miners > 0) {
			miners --;
			People.addPeople(1);
		}
		minersL.setText("Miners: " + miners);
		multiplierL.setText(Stone.getMultiplier() + "x");
	}
	
	private void update() {
		this.updateUI();
	}
	
	private void checkDisplay(){
		if(isDisplayed == false && Stone.isActive()) {
			setupComponents();
			isDisplayed = true;
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == minerB && miners > 0) {
				if(loadL.getText().equals(loadList.get(0))) {
					loadL.setText(loadList.get(1));
				}
			}
			if(event.getSource() == addB && loadL.getText().equals(loadList.get(0))) {
				if(People.getPeople() > 0) {
					miners ++;
					People.removePeople(1);
					minersL.setText("Miners: " + miners);
				}
			}
			if(event.getSource() == removeB && loadL.getText().equals(loadList.get(0))) {
				if(miners > 0) {
					miners --;
					People.addPeople(1);
					minersL.setText("Miners: " + miners);
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
					mine();
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
			checkDisplay();
			if(isDisplayed == true) {
				checkPeople();
			}
		}
	}
	
}
