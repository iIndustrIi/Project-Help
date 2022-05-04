/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class SubWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public SubWindow() {
        setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setContentPane(new JPanel());
	}
	
	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
        setLocationRelativeTo(null);
	}
}
