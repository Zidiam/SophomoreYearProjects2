import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Stage1 extends JPanel{
	private boolean complete;
	private JLabel story;
	private JButton confirmB;
	private ArrayList<String> words;
	
	public Stage1() {
		this.setLayout(null);
		
		setupWords();
		
		this.setBackground(Color.white);
		complete = true;
		
		confirmB = new JButton("OK");
		confirmB.setBounds(0, 50, 75, 25);
		confirmB.setBackground(Color.WHITE);
		confirmB.addActionListener(new ButtonListener());
		add(confirmB);
	}
	
	private void setupWords() {
		words = new ArrayList<String>();
		words.add("Tree's surround you as you awake.");
		words.add("There ground is rough as your hands push the ground to get up.");
		words.add("It's dark but you can make out that you are in a clearing with wierd tree's around.");
		words.add("You start to move toward one of the tree's and see wierd discoloring in it.");
		words.add("The tree seem's like its deing but not all of them are");
		words.add("As you examine the tree's you feel the intense cold hit you");
		words.add("You look around to find things to start a fire");
		words.add("You find a couple leaves and sticks and try to start a fire");
		words.add("As you rub the sticks together with no progress your hands start to blister");
		words.add("After what seemed like hours some smoke starts forming");
		words.add("Soon a small fire starts and you quikly throw some leaves and try to make the fire bigger");
		words.add("Then adding wood for it to keep burning");
		words.add("The fire is now maintained by wood");
		
		story = new JLabel(words.get(0));
		story.setBounds(0, 25, 500, 25);
		add(story);
	}
	
	private void runGame() {
		if(words.size() > 0) {
			story.setText(words.get(0));
			update();
		}
		else {
			complete = true;
		}
	}
	
	private void update() {
		this.updateUI();
	}
	
	public boolean isComplete() {
		return complete;
	}
	
	private class ButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == confirmB && words.size() > 0) {
				words.remove(0);
				runGame();
				update();
			}
		}
	}
}
