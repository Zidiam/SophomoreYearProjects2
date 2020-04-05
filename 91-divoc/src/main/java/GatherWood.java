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
	
	private JButton gatherB, addB, removeB;
	private JLabel gatherersL, loadL;
	private int gatherers, loadSpeed;
	private ArrayList<String> loadList;
	private Timer loadTimer;
	
	
	public GatherWood() {
		this.setPreferredSize(new Dimension(550, 25));
		this.setBackground(Color.WHITE);
		this.setLayout(null);
		
		gatherers = 0;
		loadSpeed = 250;
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
		
		gatherB.setBounds(450, 0, 100, 25);
		addB.setBounds(100, 0, 100, 25);
		removeB.setBounds(200, 0, 100, 25);
		gatherersL.setBounds(0, 0, 100, 25);
		loadL.setBounds(300, 0, 150, 25);
		
		loadL.setFont(new Font("Arial Narrow", Font.BOLD, 31));
		loadL.setForeground(new Color(0, 0, 0, 50));
		
		gatherB.addActionListener(new ButtonListener());
		addB.addActionListener(new ButtonListener());
		removeB.addActionListener(new ButtonListener());
		
		add(gatherB);
		add(addB);
		add(removeB);
		add(gatherersL);
		add(loadL);
	}
	
	private void setupTimer() {
		loadTimer = new Timer(loadSpeed, new LoadListener());
		loadTimer.start();
	}
	
	private void gather() {
		Wood.addWood(gatherers);
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
	
}
