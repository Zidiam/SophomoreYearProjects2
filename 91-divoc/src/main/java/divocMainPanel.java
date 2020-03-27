import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

public class divocMainPanel extends JPanel{
	
	private final int WIDTH = 550, HEIGHT = 700;
	private int speed = 75;
	private Timer timer;
	private JButton confirmB;
	private Stage1 stage1;
	private Stage2 stage2;
	private Stage3 stage3;
	
	public divocMainPanel() {
		timer = new Timer(speed, new ReboundListener());
		
		stage1 = new Stage1();
		
		this.setLayout(new BorderLayout());
		
		add(stage1);
		
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		timer.start();
		
	}
	
	private void update() {
		if(stage1 != null && stage1.isComplete() == false) {
			this.updateUI();
		}
		else if(stage1 != null && stage1.isComplete() == true) {
			remove(stage1);
			stage1 = null;
			stage2 = new Stage2();
			add(stage2);
			this.updateUI();
		}
		if(stage2 != null && stage2.isComplete() == false) {
			this.updateUI();
		}
		else if(stage2 != null && stage2.isComplete() == true) {
			remove(stage2);
			stage2 = null;
			stage3 = new Stage3();
			add(stage3);
			this.updateUI();
		}
		if(stage3 != null && stage3.isComplete() == false) {
			this.updateUI();
		}
		else if(stage3 != null && stage3.isComplete() == true) {
			remove(stage3);
			stage3 = null;
			this.updateUI();
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
	