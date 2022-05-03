package student.jonathanwhite.librarysystem.ui.tableselectors;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.info.BookInfoWindow;

public class BooksWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	public BooksWindow() {
		super(Main.library.books, e -> new BookInfoWindow(e), "All books");
		IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
			new BookInfoWindow(Main.library.books.get(i)).setVisible(true);
		});
		addTableButton(viewButton);
		setTitle("All books");
		pack();
	}

}
