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
import student.jonathanwhite.librarysystem.ui.info.CustomerInfoWindow;
import student.jonathanwhite.librarysystem.ui.registers.CustomerRegistryWindow;

public class CustomersWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	static IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
		new CustomerInfoWindow(Main.library.customers.get(i), true).setVisible(true);
	});
	static RunnableButton customerRegistry = new RunnableButton("New customer", () -> {
		new CustomerRegistryWindow().setVisible(true);
	});
	
	static List<JButton> buttons = List.of(viewButton, customerRegistry);
	
	public CustomersWindow() {
		super(Main.library.customers, buttons, e -> new CustomerInfoWindow(e, false), "All customers");
		Main.service.listenerCustomerAdded.add((e, i) -> {
			addRow(new CustomerInfoWindow(e, false));
		});
		Main.service.listenerCustomerDeleted.add((e, i) -> {
			if (i < model.getRowCount()) {
				removeRow(i);
			}
		});
		setTitle("All customers");
		pack();
	}

}
