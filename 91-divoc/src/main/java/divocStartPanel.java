import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

public class divocStartPanel extends JPanel{
	
	private final int WIDTH = 575, HEIGHT = 700;
	private int speed = 75;
	private JButton enterB;
	private JLabel titleL;
	private divocMainPanel mainP;
	private JLabel creatorL, versionL;
	private Intro intro;
	private Timer checkTimer;
	
	public divocStartPanel() {
		createComponents();
		checkTimer = new Timer(speed, new CheckListener());
		
		this.setLayout(null);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		checkTimer.start();
		
	}
	
	public void createComponents() {
		titleL = new JLabel("19-DIVOC");
		enterB = new JButton("Enter");
		creatorL = new JLabel("Creator: Jason Melnik");
		versionL = new JLabel("Version: 2.0 BETA", SwingConstants.RIGHT);
		
		enterB.setBounds(225, 300, 100, 25);
		titleL.setBounds(185, 200, 250, 50);
		creatorL.setBounds(0, 675, 200, 25);
		versionL.setBounds(400, 675, 175, 25);
		
		enterB.setBackground(Color.WHITE);
		titleL.setFont(new Font("Chiller", 1, 50));
		
		enterB.addActionListener(new ButtonListener());
		
		add(enterB);
		add(titleL);
		add(creatorL);
		add(versionL);
	}
	
	public void startGame() {
		this.removeAll();
		intro = new Intro();
		this.add(intro);
		
		mainP = new divocMainPanel();
		this.setLayout(new BorderLayout());
		this.add(mainP);
		this.updateUI();
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == enterB) {
				startGame();
			}
		}
	}
	
	private void setupGame() {
		mainP = new divocMainPanel();
		this.setLayout(new BorderLayout());
		this.add(mainP);
		this.updateUI();
	}
	
	private class CheckListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			if(mainP == null && intro != null && intro.isComplete()) {
				setupGame();
			}
		}
	}
}