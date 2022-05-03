package student.jonathanwhite.librarysystem.ui.info;

import student.jonathanwhite.librarysystem.Book;
import student.jonathanwhite.librarysystem.Main;

public class BookInfoWindow extends InfoWindow {
	
	private static final long serialVersionUID = 1L;
	
	public BookInfoWindow(Book book) {
		super();
		
		String releaseDate = Main.dateFormat.format(book.releaseDate());
		boolean isBorrowed = Main.service.isBookBorrowed(book);
		String availability = isBorrowed ? "Not available" : "Available";
		
		label("Title", book.title());
		label("Author", book.author());
		label("Publisher", book.publisher());
		label("Release Date", releaseDate);
		label("Availability", availability);
		
		setTitle("Book Information");
		pack();
	}

}
