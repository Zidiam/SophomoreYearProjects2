import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;

public class EndGame extends JPanel{
	
	private final int WIDTH = 575, HEIGHT = 700;
	private JLabel endL;
	
	public EndGame() {
		
		createComponents();
		
		this.setLayout(null);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		createComponents();
		
		setFocusable(true);
		
	}
	
	public void createComponents() {
		endL = new JLabel("Congrates you beat the game!");
		
		endL.setBounds(0, 0, 550, 25);
		
		add(endL);
	}
	
	
}
	