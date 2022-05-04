/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem;

import java.time.LocalDate;

public record ReturnRecord(Book book, Customer customer, LocalDate borrowDate, LocalDate dueDate, int lateFeePerDay, int totalFeePaid, LocalDate returnDate) {

	public ReturnRecord(RentRecord rentRecord, LocalDate returnDate) {
		this(
			rentRecord.book(), 
			rentRecord.customer(), 
			rentRecord.borrowDate(), 
			rentRecord.dueDate(),
			rentRecord.lateFeePerDay(), 
			rentRecord.feePayable(), 
			returnDate
		);
	}
	
}
