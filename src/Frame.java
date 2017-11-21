package brickBreakerGame;
import javax.swing.*;
/**
 * Frame to hold the Brick Breaker game
 * @author Sarah Schultz
 *
 */
public class Frame {

	/**
	 * Create the frame, add the board, make it exit when the window is closed
	 * size the frame and make it visible
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Brick Breaker");
		frame.add(new Board());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 800);
		frame.setVisible(true);

	}

}
