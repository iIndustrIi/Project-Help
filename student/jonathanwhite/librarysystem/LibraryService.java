/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

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
	public final List<ObjIntConsumer<Customer>> listenerCustomerAdded;
	public final List<ObjIntConsumer<Book>> listenerBookAdded;
	public final List<ObjIntConsumer<RentRecord>> listenerRentAdded;
	public final List<ObjIntConsumer<ReturnRecord>> listenerReturnAdded;
	
	public LibraryService(Library library) {
		this.library = library;
		this.listenerCustomerDeleted = new ArrayList<>();
		this.listenerBookDeleted = new ArrayList<>();
		this.listenerRentDeleted = new ArrayList<>();
		this.listenerReturnDeleted = new ArrayList<>();
		this.listenerCustomerAdded = new ArrayList<>();
		this.listenerBookAdded = new ArrayList<>();
		this.listenerRentAdded = new ArrayList<>();
		this.listenerReturnAdded = new ArrayList<>();
	}
	
	public void addBook(Book newBook) {
		library.books.add(newBook);
		for (ObjIntConsumer<Book> c: listenerBookAdded) {
			c.accept(newBook, library.books.size() - 1);
		}
	}
	
	public void addCustomer(Customer newCustomer) {
		if (newCustomer != null && !library.customers.contains(newCustomer)) {
			library.customers.add(newCustomer);
			for (ObjIntConsumer<Customer> c: listenerCustomerAdded) {
				c.accept(newCustomer, library.customers.size() - 1);
			}
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
	
	public RentRecord searchRentRecord(Predicate<RentRecord> predicate) {
		for (RentRecord record: library.rentRecords) {
			if (predicate.test(record)) {
				return record;
			}
		}
		return null;
	}
	
	public int totalPendingFee(Customer customer) {
		int fee = 0;
		for (RentRecord record: library.rentRecords) {
			if (record.customer().equals(customer)) {
				fee += record.feePayable();
			}
		}
		return fee;
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
	
	public boolean deleteReturnRecord(ReturnRecord record) {
		for (int i = 0; i < library.returnRecords.size(); i++) {
			ReturnRecord e = library.returnRecords.get(i);
			if (record.equals(e)) {
				library.returnRecords.remove(record);
				for (ObjIntConsumer<ReturnRecord> c: listenerReturnDeleted) {
					c.accept(e, i);
				}
				return true;
			}
		}
		return false;
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
		for (ObjIntConsumer<RentRecord> c: listenerRentAdded) {
			c.accept(record, library.rentRecords.size() - 1);
		}
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
		for (int i = 0; i < library.rentRecords.size(); i++) {
			RentRecord rentRecord = library.rentRecords.get(i);
			if (rentRecord.customer().equals(customer) 
					&& rentRecord.book().equals(book)) {
				LocalDate now = LocalDate.now();
				ReturnRecord returnRecord = new ReturnRecord(rentRecord, now);
				library.returnRecords.add(returnRecord);
				library.rentRecords.remove(rentRecord);
				for (ObjIntConsumer<RentRecord> e: listenerRentDeleted) {
					e.accept(rentRecord, i);
				}
				for (ObjIntConsumer<ReturnRecord> c: listenerReturnAdded) {
					c.accept(returnRecord, library.returnRecords.size() - 1);
				}
				deleteCustomer(customer);
				return true;
			}
		}
		return false;
	}



}