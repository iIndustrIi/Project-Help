package student.jonathanwhite.librarysystem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;

public class LibraryService {

	public final Library library;
	
	public final List<ObjIntConsumer<Customer>> listenerCustomerDeleted;
	public final List<ObjIntConsumer<Book>> listenerBookDeleted;
	public final List<ObjIntConsumer<RentRecord>> listenerRentDeleted;
	public final List<ObjIntConsumer<ReturnRecord>> listenerReturnDeleted;
	
	public LibraryService(Library library) {
		this.library = library;
		this.listenerCustomerDeleted = new ArrayList<>();
		this.listenerBookDeleted = new ArrayList<>();
		this.listenerRentDeleted = new ArrayList<>();
		this.listenerReturnDeleted = new ArrayList<>();
	}
	
	public void addBook(Book newBook) {
		library.books.add(newBook);
	}
	
	public void addCustomer(Customer newCustomer) {
		if (newCustomer != null && !library.customers.contains(newCustomer)) {
			library.customers.add(newCustomer);
		}
	}
	
	public Book searchBook(Predicate<Book> predicate) {
		for (Book customer: library.books) {
			if (predicate.test(customer)) {
				return customer;
			}
		}
		return null;
	}
	
	public Customer searchCustomer(Predicate<Customer> predicate) {
		for (Customer customer: library.customers) {
			if (predicate.test(customer)) {
				return customer;
			}
		}
		return null;
	}
	
	public Book searchBook(String title) {
		return searchBook(e -> e.title().equalsIgnoreCase(title));
	}
	
	public Customer searchCustomer(String name) {
		return searchCustomer(e -> e.name().equalsIgnoreCase(name));
	}

	public int deleteCustomer(Customer customer) {
		if (customer == null || !library.customers.contains(customer)) {
			return -1;
		}
		if (customerBooks(customer).isEmpty()) {
			int index = library.customers.indexOf(customer);
			listenerCustomerDeleted.forEach(e -> e.accept(customer, index));
			library.customers.remove(customer);
			return 1;
		}
		return 0;
	}
	
	public List<Book> customerBooks(Customer customer) {
		List<Book> books = new ArrayList<>();
		for (RentRecord record: library.rentRecords) {
			if (record.customer().equals(customer)) {
				books.add(record.book());
			}
		}
		return books;
	}
	
	public boolean isBookBorrowed(Book book) {
		for (RentRecord record: library.rentRecords) {
			if (record.book().equals(book)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isBookBorrowedBy(Book book, Customer customer) {
		for (RentRecord record: library.rentRecords) {
			if (record.book().equals(book)) {
				if (record.customer().equals(customer)) {
					return true;
				}
			}
		}
		return false;
	}

	public void rentBook(Book book, Customer customer) {
		if (book == null || !library.books.contains(book)) {
			return;
		}
		if (customer == null || !library.customers.contains(customer)) {
			return;
		}
		if (!canCustomerRent(customer)) {
			return;
		}
		LocalDate now = LocalDate.now();
		RentRecord record = new RentRecord(book, customer, now, library.BORROW_DURATION, library.LATE_FEE_PER_DAY);
		library.rentRecords.add(record);
	}

	public boolean canCustomerRent(Customer customer) {
		return customerBooks(customer).size() < library.BOOK_BORROWING_LIMIT;
	}

	public boolean returnBook(Book book, Customer customer) {
		if (book == null || !library.books.contains(book)) {
			return false;
		}
		if (customer == null || !library.customers.contains(customer)) {
			return false;
		}
		for (RentRecord rentRecord: library.rentRecords) {
			if (rentRecord.customer().equals(customer) 
					&& rentRecord.book().equals(book)) {
				LocalDate now = LocalDate.now();
				ReturnRecord returnRecord = new ReturnRecord(rentRecord, now);
				library.returnRecords.add(returnRecord);
				library.rentRecords.remove(rentRecord);
				deleteCustomer(customer);
				return true;
			}
		}
		return false;
	}



}