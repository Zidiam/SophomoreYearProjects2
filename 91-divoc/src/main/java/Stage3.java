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
	
	private Wood wood;
	private Rabbit rabbit;
	private boolean complete = false;
	private int people = 0;
	private JLabel peopleL;
	private Timer gameTimer;
	
	public Stage3() {
		this.setLayout(null);
		
		setupComponents();
		
		this.setBackground(Color.white);
		gameTimer = new Timer(1, new GameListener());
		gameTimer.start();
	}
	
	private void setupComponents() {
		peopleL = new JLabel("Available People: " + people);
		wood = new Wood(10, 1);
		rabbit = new Rabbit(1, 0);
		
		peopleL.setBounds(0, 0, 125, 25);
		wood.setBounds(0, 25, 550, 50);
		rabbit.setBounds(0, 75, 550, 50);
		
		add(peopleL);
		add(wood);
		add(rabbit);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private void checkR() {
		if(rabbit.getArrow().equals("up")) {
			if(people > 0) {
				rabbit.addCollector();
				rabbit.resetArrow();
				people --;
				peopleL.setText("Available People: " + people);
			}
			else {
				rabbit.resetArrow();
			}
		}
		if(rabbit.getArrow().equals("down")) {
			if(rabbit.getCollectors() > 0) {
				rabbit.removeCollector();
				rabbit.resetArrow();
				people ++;
				peopleL.setText("Available People: " + people);
			}
			else {
				rabbit.resetArrow();
			}
		}
		
		
	}
	
	private void checkW() {
		if(wood.getArrow().equals("up")) {
			if(people > 0) {
				wood.addCollector();
				wood.resetArrow();
				people --;
				peopleL.setText("Available People: " + people);
			}
			else {
				wood.resetArrow();
			}
		}
		if(wood.getArrow().equals("down")) {
			if(wood.getCollectors() > 0) {
				wood.removeCollector();
				wood.resetArrow();
				people ++;
				peopleL.setText("Available People: " + people);
			}
			else {
				wood.resetArrow();
			}
		}
		
		
	}
	
	private class GameListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(!rabbit.getArrow().equals("None")) {
				checkR();
			}
			
			if(!wood.getArrow().equals("None")) {
				checkW();
			}
		}
		
	}

}