import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class BadEvent extends JPanel{
	private Timer checkTimer, reloadTimer;
	private int eventTime = 100;
	private boolean complete = false;
	private JLabel swordL, eventL;
	private JButton deffendB, surrenderB, coverB, smallCoverB;
	private boolean active;
	
	public BadEvent() {
		this.setLayout(null);
		
		setupTimers();
		
		active = false;
		
		this.setOpaque(false);
		
		this.setPreferredSize(new Dimension(575, 700));
	}
	
	private void setupTimers() {
		checkTimer = new Timer(eventTime, new CheckListener());
		checkTimer.start(); 
		
		reloadTimer = new Timer(1, new ReloadListener());
		reloadTimer.start(); 
	}
	
	private void setupComponents() {
		
		swordL = new JLabel("\u2694");
		deffendB = new JButton("DEFEND");
		surrenderB = new JButton("SURRENDER");
		coverB = new JButton();
		smallCoverB = new JButton();
		
		coverB.setOpaque(false);
		
		swordL.setBounds(130, 175, 200, 200);
		deffendB.setBounds(130, 400, 125, 25);
		surrenderB.setBounds(305, 400, 125, 25);
		coverB.setBounds(0, 0, 575, 700);
		smallCoverB.setBounds(130, 250, 300, 175);
		
		
		swordL.setFont(new Font("Dialog", 1, 50));
		
		surrenderB.setBackground(Color.WHITE);
		deffendB.setBackground(Color.WHITE);
		
		coverB.setEnabled(false);
		coverB.setOpaque(false);
		
		smallCoverB.setBackground(Color.WHITE);
		
		deffendB.addActionListener(new ButtonListener());
		
		add(swordL);
		add(deffendB);
		add(surrenderB);
		add(coverB);
		add(smallCoverB);
		active = true;
		checkTimer.stop(); 
		this.updateUI();
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private void removeEverything() {
		
		this.removeAll();
		active = false;
		this.updateUI();
		checkTimer.start(); 
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == surrenderB) {
				removeEverything();
			}
		}
	}
	
	private void updateit() {
		this.updateUI();
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(active == false) {
				setupComponents();
			}
		}
	}
	
	private class ReloadListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			updateit();
		}
	}
	
}
