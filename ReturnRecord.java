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
