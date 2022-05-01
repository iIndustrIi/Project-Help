import java.time.LocalDate;
public class Book {

  private String title;
 private String author;
 private String publisher;
 private LocalDate releaseDate;
 private Customer borrowedBy;
 private String availability;

 public Book(String title, String author, String publisher) {
    this.title = title;
 this.author = author;
 this.publisher = publisher;
 this.releaseDate = LocalDate.now();
 this.availability = "No";
 if (this.borrowedBy != null) {
      this.availability = "Yes";
    }
}

  public Book() {
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
 this.availability = "Yes";
  }

  @Override
  public String toString() {
    return
        "Title='" + title + '\'' +
        ", borrowedBy=" + borrowedBy +
        '}';
  }
}
