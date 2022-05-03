package student.jonathanwhite.librarysystem.ui.info;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ReturnRecord;

public class ReturnInfoWindow extends InfoWindow {
	
	private static final long serialVersionUID = 1L;
	
	public ReturnInfoWindow(ReturnRecord record) {
		super();
		
		String borrowDate = Main.dateFormat.format(record.borrowDate());
		String dueDate = Main.dateFormat.format(record.dueDate());
		String returnDate = Main.dateFormat.format(record.returnDate());
		
		label("Book title", record.book().title());
		label("Customer name", record.customer().name());
		label("Borrow Date", borrowDate);
		label("Due Date", dueDate);
		label("Return Date", returnDate);
		label("Late fee per day", "$" + record.lateFeePerDay());
		label("Total late fee", "$" + record.totalFeePaid());
		
		setTitle("Return Information");
		pack();
	}
}
