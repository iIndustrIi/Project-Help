/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.info;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.RentRecord;
import student.jonathanwhite.librarysystem.ui.registers.ReturnRegistryWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.TableSelector.RunnableButton;

public class RentInfoWindow extends InfoWindow {
	
	private static final long serialVersionUID = 1L;
	
	public RentInfoWindow(RentRecord record) {
		super(true);
		
		String borrowDate = Main.dateFormat.format(record.borrowDate());
		String dueDate = Main.dateFormat.format(record.dueDate());
		String isOverdue = record.isOverdue() ? "Overdue" : "On time";
		
		label("Book title", record.book().title());
		label("Customer name", record.customer().name());
		label("Borrow Date", borrowDate);
		label("Due Date", dueDate);
		label("Status", isOverdue);
		label("Fee per day (if late)", "$" + record.lateFeePerDay());
		label("Total fee", "$" + record.feePayable());
		
		Main.service.listenerRentDeleted.add((e, i) -> {
			if (record == e) {
				setVisible(false);
			}
		});
		
		RunnableButton button = new RunnableButton("Return", () -> {    
			ReturnRegistryWindow window = new ReturnRegistryWindow();
			window.fields.get(0).setText(record.book().title());
			window.fields.get(1).setText(record.customer().name());
			window.setVisible(true);	
		});
		
		buttonsPanel.add(button);
		
		setTitle("Rent Information");
		pack();
	}
}
