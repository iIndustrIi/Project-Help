/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.info;

import javax.swing.JPanel;

import student.jonathanwhite.librarysystem.Book;
import student.jonathanwhite.librarysystem.Customer;
import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.RentRecord;
import student.jonathanwhite.librarysystem.ui.tableselectors.CustomersWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.TableSelector.RunnableButton;

public class BookInfoWindow extends InfoWindow {
	
	private static final long serialVersionUID = 1L;
	
	public BookInfoWindow(Book book, boolean showComplete) {
		super(showComplete);
		
		String releaseDate = Main.dateFormat.format(book.releaseDate());
		RentRecord record = Main.service.searchRentRecord(e -> e.book().equals(book));
		boolean isBorrowed = record != null;
		String availability = isBorrowed ? "Not available" : "Available";
		
		if (showComplete) {
			CustomersWindow customersWindow = new CustomersWindow();
			for (int i = 0; i < Main.library.customers.size(); i++) {
				Customer customer = Main.library.customers.get(i);
				if (!Main.service.returnBook(book, customer) && Main.service.isBookBorrowedBy(book, customer)) {
					customersWindow.model.removeRow(i);
				}
			}
			tablePanel.add((JPanel) customersWindow.getContentPane());
		}

		if (isBorrowed) {
			RunnableButton button = new RunnableButton("Open Rent Record", () -> {
				new RentInfoWindow(record).setVisible(true);
			});
			buttonsPanel.add(button);
		}
		
		label("Title", book.title());
		label("Author", book.author());
		label("Publisher", book.publisher());
		label("Release Date", releaseDate);
		label("Availability", availability);
		
		setTitle("Book Information");
		pack();
	}

}
