package student.jonathanwhite.librarysystem.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class SubWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public SubWindow() {
        setResizable(false);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(new JPanel());
	}
}
