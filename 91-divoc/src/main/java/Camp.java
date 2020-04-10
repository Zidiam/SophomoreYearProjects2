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
	private Hut hut1;
	
	public Camp() {
		this.setLayout(null);
		
		setupComponents();
		
		this.setBackground(Color.black);
		
		this.setPreferredSize(new Dimension(550, 700));
	}
	
	private void setupComponents() {
		campfire = new CampFire();
		story = new Story(campfire);
		hut1 = new Hut(10);
		
		campfire.setBounds(225, 275, 75, 75);
		story.setBounds(0, 600, 550, 75);
		hut1.setBounds(225, 100, 75, 125);
		
		add(campfire);
		add(story);
		add(hut1);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			
		}
	}
}
