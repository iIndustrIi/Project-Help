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
