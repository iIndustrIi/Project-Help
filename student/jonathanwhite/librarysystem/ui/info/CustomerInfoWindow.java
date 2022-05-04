/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.info;

import java.awt.Color;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import student.jonathanwhite.librarysystem.Book;
import student.jonathanwhite.librarysystem.Customer;
import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.tableselectors.BooksWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.TableSelector.RunnableButton;

public class CustomerInfoWindow extends InfoWindow {
	
	private static final long serialVersionUID = 1L;
	
	public CustomerInfoWindow(Customer customer, boolean showComplete) {
		super(showComplete);
		
		String registeredDate = Main.dateFormat.format(customer.registeredDate());
		List<Book> books = Main.service.customerBooks(customer);
		
		label("Name", customer.name());
		label("Address", customer.address());
		label("Phone Number", customer.phone());
		label("Registered Date", registeredDate);
		label("Books Borrowed", books.size() + "/" + Main.service.library.BOOK_BORROWING_LIMIT);
		label("Total Pending Fee", "$" + Main.service.totalPendingFee(customer));
		
		if (showComplete) {
			BooksWindow booksWindow = new BooksWindow();
			for (int i = 0; i < Main.library.books.size(); i++) {
				Book book = Main.library.books.get(i);
				if (!Main.service.isBookBorrowedBy(book, customer)) {
					booksWindow.model.removeRow(i);
				}
			}
			tablePanel.add((JPanel) booksWindow.getContentPane());
		}

		Main.service.listenerCustomerDeleted.add((e, i) -> {
			if (customer == e) {
				setVisible(false);
			}
		});
		
		RunnableButton button = new RunnableButton("Delete", () -> {
			int result = Main.service.deleteCustomer(customer);
			if (result == 0) {
				String message = "This customer has pending books";
				JOptionPane.showMessageDialog(null, message);
				return;
			} else if (result == -1) {
				String message = "This customer is not registered";
				JOptionPane.showMessageDialog(null, message);
				return;
			}
		});
		button.setBackground(Color.RED);
		button.setForeground(Color.WHITE);
		button.setFocusable(false);
		addButton(button);
		
		// TODO display a table with all the borrowed books
		
		setTitle("Customer Information");
		pack();
	}
}
