package student.jonathanwhite.librarysystem.ui.tableselectors;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.info.RentInfoWindow;

public class RentsWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	public RentsWindow() {
		super(Main.library.rentRecords, e -> new RentInfoWindow(e), "All rents");
		IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
			new RentInfoWindow(Main.library.rentRecords.get(i)).setVisible(true);
		});
		addTableButton(viewButton);
		setTitle("All rents");
		pack();
	}
}
