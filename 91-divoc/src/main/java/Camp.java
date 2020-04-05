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
	private boolean complete;
	private CampFire wood;
	
	public Camp() {
		this.setLayout(null);
		
		setupComponents();
		
		this.setBackground(Color.white);
		
		this.setPreferredSize(new Dimension(550, 700));
		
		complete = false;
	}
	
	private void setupComponents() {
		wood = new CampFire();
		
		wood.setBounds(225, 275, 75, 75);
		
		add(wood);
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
		}
	}
}
