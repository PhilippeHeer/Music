import java.awt.BorderLayout;
import java.awt.Label;

import javax.swing.JFrame;

/**
 * 
 * @author Philippe Heer
 *
 */
public class Window {
	
	/**
	 * 
	 */
	public Window() {
		JFrame frame = new JFrame("FrameDemo");


		frame.getContentPane().add(new Label(), BorderLayout.CENTER);
		frame.pack();

		
		frame.setVisible(true);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
