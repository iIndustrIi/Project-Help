/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Library {

	public final int LATE_FEE_PER_DAY = 3;
	public final int BOOK_BORROWING_LIMIT = 7;
	public final Duration BORROW_DURATION = Duration.ofDays(7); 
	
	public final List<Book> books;
	public final List<Customer> customers;
	public final List<RentRecord> rentRecords;
	public final List<ReturnRecord> returnRecords;
	
	public Library() {
		books = new ArrayList<>();
		customers = new ArrayList<>();
		rentRecords = new ArrayList<>();
		returnRecords = new ArrayList<>();
	}
	
}
