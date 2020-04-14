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

public class GatherFarm extends JPanel{
	
	private JButton farmerB, addB, removeB, startB;
	private JLabel farmersL, loadL;
	private int farmers, loadSpeed, checkSpeed;
	private ArrayList<String> loadList;
	private Timer loadTimer, checkTimer;
	private boolean isDisplayed;
	
	public GatherFarm() {
		this.setPreferredSize(new Dimension(575, 25));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		farmers = 0;
		loadSpeed = 250;
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
		farmerB = new JButton("Farm");
		addB = new JButton("Add");
		removeB = new JButton("Remove");
		farmersL = new JLabel("Hunters: " + farmers);
		loadL = new JLabel(loadList.get(0));
		startB = new JButton("Click to setup farms");
		
		farmerB.setBounds(475, 0, 100, 25);
		addB.setBounds(125, 0, 100, 25);
		removeB.setBounds(225, 0, 100, 25);
		farmersL.setBounds(0, 0, 100, 25);
		loadL.setBounds(325, 0, 150, 25);
		startB.setBounds(0, 0, 575, 25);
		
		addB.setBackground(Color.WHITE);
		removeB.setBackground(Color.WHITE);
		startB.setBackground(Color.WHITE);
		farmerB.setBackground(Color.WHITE);
		
		loadL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadL.setForeground(new Color(0, 0, 0, 50));
		
		farmerB.addActionListener(new ButtonListener());
		addB.addActionListener(new ButtonListener());
		removeB.addActionListener(new ButtonListener());
		startB.addActionListener(new ButtonListener());
		
		add(startB);
		add(farmerB);
		add(addB);
		add(removeB);
		add(farmersL);
		add(loadL);
	}
	
	private void setupTimer() {
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start();
	}
	
	private void farm() {
		Wheat.addWheat(farmers);
	}
	
	private void checkPeople() {
		while(People.getPeople() < 0 && farmers > 0) {
			farmers --;
			People.addPeople(1);
		}
		farmersL.setText("Hunters: " + farmers);
	}
	
	private void update() {
		this.updateUI();
	}
	
	private void checkDisplay(){
		if(isDisplayed == false && Wheat.isActive()) {
			setupComponents();
			isDisplayed = true;
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == farmerB && farmers > 0) {
				if(loadL.getText().equals(loadList.get(0))) {
					loadL.setText(loadList.get(1));
				}
			}
			if(event.getSource() == addB && loadL.getText().equals(loadList.get(0))) {
				if(People.getPeople() > 0) {
					farmers ++;
					People.removePeople(1);
					farmersL.setText("Hunters: " + farmers);
				}
			}
			if(event.getSource() == removeB && loadL.getText().equals(loadList.get(0))) {
				if(farmers > 0) {
					farmers --;
					People.addPeople(1);
					farmersL.setText("Hunters: " + farmers);
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
					farm();
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
