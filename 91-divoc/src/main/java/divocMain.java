import javax.swing.JFrame;
/*
 * divocMain,java -- A fun game
 * By: Jason Melnik
 * Date: 3/25/2020
 */
public class divocMain {
	public static void main(String[] args) {
		JFrame frame = new JFrame("91-divoc");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(new divocStartPanel(frame));
		
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}