/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem;


import java.time.Duration;
import java.time.LocalDate;

public record RentRecord(Book book, Customer customer, LocalDate borrowDate, LocalDate dueDate, int lateFeePerDay) {
	
	public RentRecord(Book book, Customer customer, LocalDate borrowDate, Duration duration, int lateFee) {
		this(book, customer, borrowDate, borrowDate.plusDays(duration.toDays()), lateFee);
	}
	
	public boolean isOverdue() {
		return dueDate.isBefore(LocalDate.now());
	}
	
	public int feePayable() {
		LocalDate now = LocalDate.now();
		if (!isOverdue()) {
			return 0;
		}
		int days = dueDate.until(now).getDays();
		return days * lateFeePerDay();
	}
	
}
