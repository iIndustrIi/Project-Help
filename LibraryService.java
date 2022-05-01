import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class LibraryService {

  public void addCustomer(Library library, Customer newCustomer) {
List<Customer> customers = library.getCustomers();
    customers.add(newCustomer);
    library.setCustomers(customers);
  }

  public void deleteCustomer(Library library, String customerName) {
List<Customer> customers = library.getCustomers();
    Customer customerToBeDeleted = null;
 for(Customer c : customers) {
      if (c.getName().equals(customerName)) {
customerToBeDeleted = c;
      }
}
    if(customerToBeDeleted != null && customerToBeDeleted.getBookBorrowed() != null) {
customers.remove(customerToBeDeleted);
      library.setCustomers(customers);
    }
}

  public void addBook(Library library, Book newBook) {
List<Book> books = library.getBooks();
    books.add(newBook);
    library.setBooks(books);
  }

  public void rentBook(Library library, Book book, Customer customer) {
List<Book> books = library.getBooks();
    List<Customer> customers = library.getCustomers();
    Customer existingCustomer = null;
    Book existingBook = null;
 for(Customer c : customers) {
      if (c.getName().equals(customer.getName())) {
existingCustomer = c;
      }
}
    for(Book b : books) {
      if (b.getTitle().equals(book.getTitle())) {
existingBook = b;
      }
}
books.remove(existingBook);
    customers.remove(existingCustomer);

    book.setBorrowedBy(existingCustomer);
    customer.setBookBorrowed(book);

    books.add(existingBook);
    customers.add(existingCustomer);

    library.setBooks(books);
    library.setCustomers(customers);
  }

  public void returnBook(Library library, Book book, Customer customer) {
List<Book> books = library.getBooks();
    List<Customer> customers = library.getCustomers();
    Customer existingCustomer = null;
    Book existingBook = null;
 for(Customer c : customers) {
      if (c.getName().equals(customer.getName())) {
existingCustomer = c;
      }
}
    for(Book b : books) {
      if (b.getTitle().equals(book.getTitle())) {
existingBook = b;
      }
}
books.remove(existingBook);
    customers.remove(existingCustomer);

 if (calculateLateFee(customer) != 0) {
book.setBorrowedBy(null);
      customer.setBookBorrowed(null);

      books.add(book);
      customers.add(customer);

      library.setBooks(books);
      library.setCustomers(customers);
    }

}

  private long calculateLateFee(Customer customer){
    long diff = ChronoUnit.DAYS.between(LocalDate.now(),customer.getDueDate());
 return diff * 3;
  }

}