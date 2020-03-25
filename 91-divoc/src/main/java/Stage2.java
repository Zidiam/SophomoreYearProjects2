import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Stage2 extends JPanel{
	private int speed = 75;
	private Timer timer;
	private boolean complete = false;
	private JLabel woodL, storyL;
	private JButton collectB;
	private int wood = 0;
	
	public Stage2() {
		this.setLayout(null);
		
		setupComponents();
		
		this.setBackground(Color.white);
		timer = new Timer(speed, new ReboundListener());
		timer.start();
	}
	
	private void setupComponents() {
		woodL = new JLabel("Wood: " + wood);
		storyL = new JLabel("random sayings");
		collectB = new JButton("Collect");
		
		woodL.setBounds(0, 25, 75, 25);
		storyL.setBounds(0, 675, 500, 25);
		collectB.setBounds(425, 25, 100, 25);
		
		collectB.setBackground(Color.WHITE);
		
		collectB.addActionListener(new ButtonListener());
		
		add(woodL);
		add(storyL);
		add(collectB);
		
	}
	
	private void collectWood() {
		wood  ++;
		woodL.setText("Wood: " + wood);
	}
	
	private void update() {
		this.updateUI();
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	public int getWood() {
		return wood;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == collectB) {
				collectWood();
			}
		}
	}
	
	private class ReboundListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
			update();
		}
	}
}
