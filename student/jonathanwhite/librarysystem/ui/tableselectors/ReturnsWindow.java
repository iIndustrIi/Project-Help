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
import student.jonathanwhite.librarysystem.ui.info.ReturnInfoWindow;
import student.jonathanwhite.librarysystem.ui.registers.ReturnRegistryWindow;

public class ReturnsWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	static IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
		new ReturnInfoWindow(Main.library.returnRecords.get(i)).setVisible(true);
	});
	static RunnableButton returnRegistry = new RunnableButton("New return", () -> {
		new ReturnRegistryWindow().setVisible(true);
	});
	
	static List<JButton> buttons = List.of(viewButton, returnRegistry);
	
	public ReturnsWindow() {
		super(Main.library.returnRecords, buttons, e -> new ReturnInfoWindow(e), "All returns");
		Main.service.listenerReturnAdded.add((e, i) -> {
			addRow(new ReturnInfoWindow(e));
		});
		Main.service.listenerReturnDeleted.add((e, i) -> {
			if (i < model.getRowCount()) {
				removeRow(i);
			}
		});
		setTitle("All returns");
		pack();
	}
}