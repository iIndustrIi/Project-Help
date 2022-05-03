package student.jonathanwhite.librarysystem.ui.info;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.RentRecord;

public class RentInfoWindow extends InfoWindow {
	
	private static final long serialVersionUID = 1L;
	
	public RentInfoWindow(RentRecord record) {
		super();
		
		String borrowDate = Main.dateFormat.format(record.borrowDate());
		String dueDate = Main.dateFormat.format(record.dueDate());
		String isOverdue = record.isOverdue() ? "Overdue" : "On time";
		
		label("Book title", record.book().title());
		label("Customer name", record.customer().name());
		label("Borrow Date", borrowDate);
		label("Due Date", dueDate);
		label("Status", isOverdue);
		label("Late fee per day", "$" + record.lateFeePerDay());
		label("Total late fee", "$" + record.feePayable());
		
		setTitle("Rent Information");
		pack();
	}
}
