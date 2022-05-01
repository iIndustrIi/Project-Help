import java.util.List;

public class Library {

  private List<Book> books;
 private List<Customer> customers;

 public Library() {
}

  public Library(List<Book> books, List<Customer> customers) {
    this.books = books;
 this.customers = customers;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  @Override
  public String toString() {
    return "Library{" +
        "books=" + books +
        ", customers=" + customers +
        '}';
  }
}

