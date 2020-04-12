import java.awt.Color;
import java.awt.Dimension;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Camp extends JPanel{
	private CampFire campfire;
	private Story story;
	private Hut hut1, hut2, hut3, hut4;
	private Outpost outpost1, outpost2, outpost3, outpost4;
	
	public Camp() {
		this.setLayout(null);
		
		setupComponents();
		
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(550, 700));
	}
	
	private void setupComponents() {
		campfire = new CampFire();
		story = new Story(campfire);
		
		hut1 = new Hut(10, 5, 5);
		hut2 = new Hut(10, 5, 5);
		hut3 = new Hut(10, 5, 5);
		hut4 = new Hut(10, 5, 5);
		
		outpost1 = new Outpost(10, 5, 5, 5);
		
		campfire.setBounds(225, 275, 75, 75);
		story.setBounds(0, 600, 550, 75);
		
		hut1.setBounds(225, 125, 75, 100);
		hut2.setBounds(350, 250, 75, 100);
		hut3.setBounds(100, 250, 75, 100);
		hut4.setBounds(225, 375, 75, 100);
		
		outpost1.setBounds(400, 75, 100, 100);
		
		add(campfire);
		add(story);
		
		add(hut1);
		add(hut2);
		add(hut3);
		add(hut4);
		
		add(outpost1);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
		}
	}
}
