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

public class GatherTraps extends JPanel{
	
	private JButton hunterB, addB, removeB, startB;
	private JLabel huntersL, loadL;
	private int hunters, loadSpeed, checkSpeed;
	private ArrayList<String> loadList;
	private Timer loadTimer, checkTimer;
	private boolean isDisplayed;
	
	public GatherTraps() {
		this.setPreferredSize(new Dimension(550, 25));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		hunters = 0;
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
		hunterB = new JButton("Gather");
		addB = new JButton("Add");
		removeB = new JButton("Remove");
		huntersL = new JLabel("Hunters: " + hunters);
		loadL = new JLabel(loadList.get(0));
		startB = new JButton("Click to setup traps");
		
		hunterB.setBounds(450, 0, 100, 25);
		addB.setBounds(100, 0, 100, 25);
		removeB.setBounds(200, 0, 100, 25);
		huntersL.setBounds(0, 0, 100, 25);
		loadL.setBounds(300, 0, 150, 25);
		startB.setBounds(0, 0, 550, 25);
		
		loadL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadL.setForeground(new Color(0, 0, 0, 50));
		
		hunterB.addActionListener(new ButtonListener());
		addB.addActionListener(new ButtonListener());
		removeB.addActionListener(new ButtonListener());
		startB.addActionListener(new ButtonListener());
		
		add(startB);
		add(hunterB);
		add(addB);
		add(removeB);
		add(huntersL);
		add(loadL);
	}
	
	private void setupTimer() {
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
		
		checkTimer = new Timer(checkSpeed, new CheckListener());
		checkTimer.start();
	}
	
	private void gather() {
		Rabbit.addRabbit(hunters);
	}
	
	private void checkPeople() {
		while(People.getPeople() < 0 && hunters > 0) {
			hunters --;
			People.addPeople(1);
		}
		huntersL.setText("Hunters: " + hunters);
	}
	
	private void update() {
		this.updateUI();
	}
	
	private void checkDisplay(){
		if(isDisplayed == false && Rabbit.isActive()) {
			setupComponents();
			isDisplayed = true;
		}
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == hunterB && hunters > 0) {
				if(loadL.getText().equals(loadList.get(0))) {
					loadL.setText(loadList.get(1));
				}
			}
			if(event.getSource() == addB && loadL.getText().equals(loadList.get(0))) {
				if(People.getPeople() > 0) {
					hunters ++;
					People.removePeople(1);
					huntersL.setText("Hunters: " + hunters);
				}
			}
			if(event.getSource() == removeB && loadL.getText().equals(loadList.get(0))) {
				if(hunters > 0) {
					hunters --;
					People.addPeople(1);
					huntersL.setText("Hunters: " + hunters);
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
					gather();
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
