package student.jonathanwhite.librarysystem.ui.registers;

import student.jonathanwhite.librarysystem.Book;
import student.jonathanwhite.librarysystem.Main;

public class BookRegistryWindow extends Formulary {

	private static final long serialVersionUID = 1L;
	
	public BookRegistryWindow() {
		super("Register a new book", "Register", "Title", "Author", "Publisher");
		setTitle("Book Registry");
		pack();
	}

	@Override
	protected boolean checkAndAccept(String[] fields) {
		Book book = new Book(fields[0], fields[1], fields[2]);
		Main.service.addBook(book);
		return true;
	}

}
