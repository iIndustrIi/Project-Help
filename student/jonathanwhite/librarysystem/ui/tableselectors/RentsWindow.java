/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.tableselectors;

import java.util.List;

import javax.swing.JButton;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.info.RentInfoWindow;
import student.jonathanwhite.librarysystem.ui.registers.RentRegistryWindow;

public class RentsWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	static IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
		new RentInfoWindow(Main.library.rentRecords.get(i)).setVisible(true);
	});
	static RunnableButton rentRegistry = new RunnableButton("New rent", () -> {
		new RentRegistryWindow().setVisible(true);
	});
	
	static List<JButton> buttons = List.of(viewButton, rentRegistry);
	
	public RentsWindow() {
		super(Main.library.rentRecords, buttons, e -> new RentInfoWindow(e), "All rents");
		Main.service.listenerRentAdded.add((e, i) -> {
			addRow(new RentInfoWindow(e));
		});
		Main.service.listenerRentDeleted.add((e, i) -> {
			if (i < model.getRowCount()) {
				removeRow(i);
			}
		});
		setTitle("All rents");
		pack();
	}
	
	
}
