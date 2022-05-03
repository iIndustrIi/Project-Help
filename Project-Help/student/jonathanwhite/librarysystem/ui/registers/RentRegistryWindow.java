package student.jonathanwhite.librarysystem.ui.registers;

import javax.swing.JOptionPane;

import student.jonathanwhite.librarysystem.Book;
import student.jonathanwhite.librarysystem.Customer;
import student.jonathanwhite.librarysystem.Main;

public class RentRegistryWindow extends Formulary {

	private static final long serialVersionUID = 1L;
	
	public RentRegistryWindow() {
		super("Register a new rent", "Register", "Book Title", "Customer Name");
        setTitle("Rent Registry");
        pack();
	}

	@Override
	protected boolean checkAndAccept(String[] fields) {
		Book book = Main.service.searchBook(e -> e.title().equals(fields[0]));
		if (book == null) {
			String message = "There is no book titled \"" + fields[0] + "\" in our database";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		if (Main.service.isBookBorrowed(book)) {
			String message = "This book is not available";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		Customer customer = Main.service.searchCustomer(e -> e.name().equals(fields[1]));
		if (customer == null) {
			String message = "There is no customer named \"" + fields[1] + "\" in our database";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		if (!Main.service.canCustomerRent(customer)) {
			String message = "This customer reached the maximum borrowed books";
			JOptionPane.showMessageDialog(null, message);
			return false;
		}
		Main.service.rentBook(book, customer);
		return true;
	}
	
}
