import java.time.LocalDate;

public record Book(String title, String author, String publisher, LocalDate releaseDate) {

	public Book(String title, String author, String publisher) {
		this(title, author, publisher, LocalDate.now());
	}
	
}
