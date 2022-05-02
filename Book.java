import java.time.LocalDate;

public class Book {

	private String title;
	private String author;
	private String publisher;
	private LocalDate releaseDate;
	private Customer borrowedBy;
	private boolean available;

	public Book() {
		this.releaseDate = LocalDate.now();
		this.available = true;
	}
	
	public Book(String title, String author, String publisher) {
		this();
		this.title = title;
		this.author = author;
		this.publisher = publisher;
	}
  
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public Customer getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(Customer borrowedBy) {
		this.borrowedBy = borrowedBy;
	    this.available = borrowedBy == null;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + ", releaseDate="
				+ releaseDate + ", available=" + available  + ", borrowedBy=" + borrowedBy + "]";
	}
  
  @Override
	public int hashCode() {
		return Objects.hash(author, publisher, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		return Objects.equals(author, other.author) && Objects.equals(publisher, other.publisher)
				&& Objects.equals(title, other.title);
	}

}
