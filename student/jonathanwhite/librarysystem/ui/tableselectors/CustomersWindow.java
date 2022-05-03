package student.jonathanwhite.librarysystem.ui.tableselectors;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.info.CustomerInfoWindow;

public class CustomersWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	public CustomersWindow() {
		super(Main.library.customers, e -> new CustomerInfoWindow(e), "All customers");
		IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
			new CustomerInfoWindow(Main.library.customers.get(i)).setVisible(true);
		});
		Main.service.listenerCustomerDeleted.add((e, i) -> {
			model.removeRow(i);
		});
		addTableButton(viewButton);
		setTitle("All customers");
		pack();
	}

}
