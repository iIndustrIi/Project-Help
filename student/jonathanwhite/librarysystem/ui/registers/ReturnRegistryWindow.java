/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.registers;

import javax.swing.JOptionPane;

import student.jonathanwhite.librarysystem.Book;
import student.jonathanwhite.librarysystem.Customer;
import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.info.ReturnInfoWindow;

public class ReturnRegistryWindow extends Formulary {

	private static final long serialVersionUID = 1L;
	
	public ReturnRegistryWindow() {
		super("Register a new return", "Register", "Book Title", "Customer Name");
        setTitle("Return Registry");
        pack();
	}

	@Override
	protected boolean checkAndAccept(String[] fields) {
		Book book = Main.service.searchBook(fields[0]);
		if (book == null) {
			String message = "There is no book titled \"" + fields[0] + "\" in our database";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		Customer customer = Main.service.searchCustomer(fields[1]);
		if (customer == null) {
			String message = "There is no customer named \"" + fields[1] + "\" in our database";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		if (!Main.service.returnBook(book, customer)) {
			String message = "The book " + fields[0] + " is not currently borrowed";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		int lastIndex = Main.library.returnRecords.size() - 1;
		new ReturnInfoWindow(Main.library.returnRecords.get(lastIndex)).setVisible(true);
		return true;
	}
}
