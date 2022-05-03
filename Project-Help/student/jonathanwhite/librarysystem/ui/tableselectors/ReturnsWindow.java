package student.jonathanwhite.librarysystem.ui.tableselectors;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.info.ReturnInfoWindow;

public class ReturnsWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	public ReturnsWindow() {
		super(Main.library.returnRecords, e -> new ReturnInfoWindow(e), "All returns");
		IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
			new ReturnInfoWindow(Main.library.returnRecords.get(i)).setVisible(true);
		});
		addTableButton(viewButton);
		setTitle("All returns");
		pack();
	}
}