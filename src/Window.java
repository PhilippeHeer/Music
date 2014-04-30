import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Philippe Heer
 * 
 */
public class Window implements ActionListener {
	public static final int BUTTON_MAX = 7;

	private JButton jButton[] = new JButton[BUTTON_MAX];

	/**
	 * 
	 */
	public Window() {
		JFrame frame = new JFrame("Assignment 2");

		JPanel jPanel = new JPanel();

		String names[] = { "A", "B", "C", "D", "E", "F", "G" };

		for (int i = 0; i < names.length; i++) {
			jButton[i] = new JButton(names[i]);
			jButton[i].addActionListener(this);
			jPanel.add(jButton[i]);
		}

		frame.add(jPanel);
		frame.pack();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 768);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < BUTTON_MAX; i++) {
			if (e.getSource() == jButton[i]) {
				System.out.println(jButton[i].getLabel());
				break;
			}
		}
	}
}
