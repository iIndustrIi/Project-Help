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
import student.jonathanwhite.librarysystem.ui.info.BookInfoWindow;
import student.jonathanwhite.librarysystem.ui.registers.BookRegistryWindow;

public class BooksWindow extends TableSelector {

	private static final long serialVersionUID = 1L;
	
	static RunnableButton bookRegistry = new RunnableButton("New book", () -> {
		new BookRegistryWindow().setVisible(true);
	});
	static IntConsumerButton viewButton = new IntConsumerButton("View", i -> {
		new BookInfoWindow(Main.library.books.get(i), true).setVisible(true);
	});
	
	static List<JButton> buttons = List.of(viewButton, bookRegistry);
	
	public BooksWindow() {
		super(Main.library.books, buttons, e -> new BookInfoWindow(e, false), "All books");
		Main.service.listenerBookAdded.add((e, i) -> {
			addRow(new BookInfoWindow(e, false));
		});
		Main.service.listenerBookDeleted.add((e, i) -> {
			if (i < model.getRowCount()) {
				removeRow(i);
			}
		});
		setTitle("All books");
		pack();
	}

}
