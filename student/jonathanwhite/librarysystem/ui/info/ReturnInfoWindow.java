/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.info;

import javax.swing.JOptionPane;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ReturnRecord;
import student.jonathanwhite.librarysystem.ui.tableselectors.TableSelector.RunnableButton;

public class ReturnInfoWindow extends InfoWindow {
	
	private static final long serialVersionUID = 1L;
	
	public ReturnInfoWindow(ReturnRecord record) {
		super(true);
		
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
		
		Main.service.listenerReturnDeleted.add((e, i) -> {
			if (record == e) {
				setVisible(false);
			}
		});
		
		RunnableButton button = new RunnableButton("Delete", () -> {
			boolean result = Main.service.deleteReturnRecord(record);
			if (!result) {
				String message = "This Record does not exist";
				JOptionPane.showMessageDialog(null, message);
				return;
			}
		});
		buttonsPanel.add(button);
	
		setTitle("Return Information");
		pack();
	}
}
