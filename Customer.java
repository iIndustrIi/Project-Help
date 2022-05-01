import java.time.LocalDate;
import java.util.Date;

public class Customer {

  private String name;
 private String address;
 private String phone;
 private LocalDate registeredDate;
 private Book bookBorrowed;

 private LocalDate dueDate;

 public Customer() {
}

  public Customer(String name, String booksBorrowed, String phone) {
    this.name = name;
 this.address = booksBorrowed;
 this.phone = phone;
  }
  public LocalDate getDueDate() {
    return dueDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public LocalDate getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = LocalDate.now();
 this.dueDate = LocalDate.now().plusDays(7);
  }

  public Book getBookBorrowed() {
    return bookBorrowed;
  }

  public void setBookBorrowed(Book bookBorrowed) {
    this.bookBorrowed = bookBorrowed;
  }

  @Override
  public String toString() {
    return
        "Customer=" + name + '\'' +
        ", bookBorrowed=" + bookBorrowed +
        ", dueDate=" + dueDate +
        '}';
  }
}

