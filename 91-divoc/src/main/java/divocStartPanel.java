import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class divocStartPanel extends JPanel{
	
	private final int WIDTH = 575, HEIGHT = 700;
	private int speed = 75;
	private Timer timer;
	private JButton enterB;
	private JLabel titleL;
	private divocMainPanel mainP;
	
	public divocStartPanel() {
		addKeyListener(new DirectionListener());
		timer = new Timer(speed, new ReboundListener());
		
		createComponents();
		
		this.setLayout(null);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		timer.start();
		
	}
	
	public void createComponents() {
		titleL = new JLabel("19-DIVOC");
		enterB = new JButton("Enter");
		
		enterB.setBounds(225, 300, 100, 25);
		titleL.setBounds(185, 200, 250, 50);
		
		enterB.setBackground(Color.WHITE);
		titleL.setFont(new Font("Chiller", 1, 50));
		
		enterB.addActionListener(new ButtonListener());
		
		add(enterB);
		add(titleL);
	}
	
	public void startGame() {
		this.removeAll();
		mainP = new divocMainPanel();
		this.setLayout(new BorderLayout());
		this.add(mainP);
		this.updateUI();
	}
	
	public void paintComponent(Graphics page)
	{
		super.paintComponent(page);
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == enterB) {
				startGame();
			}
		}
	}
	
	private class DirectionListener implements KeyListener{
		public void keyPressed(KeyEvent event) {
			if(event.getKeyCode() == KeyEvent.VK_A) {
				//do something
			}
		}
		public void keyTyped(KeyEvent event) {}
		public void keyReleased(KeyEvent event) {}
	}
	
	private class ReboundListener implements ActionListener
	{
		//--------------------------------------------------------------
		//  Updates the position of the image and possibly the direction
		//  of movement whenever the timer fires an action event.
		//--------------------------------------------------------------
		public void actionPerformed(ActionEvent event)
		{
			//happens over and over again
		}
	}
}
	