import java.time.LocalDate;

public class LibraryService {

	public final Library library;
	
	public LibraryService(Library library) {
		this.library = library;
	}
	
	public void addBook(Book newBook) {
		library.books.add(newBook);
	}
	
	public void addCustomer(Customer newCustomer) {
		if (newCustomer != null && !library.customers.contains(newCustomer)) {
			library.customers.add(newCustomer);
		}
	}

	public void deleteCustomer(Customer customer) {
		if (customer != null && isCostumerBorrowed(customer)) {
			library.customers.remove(customer);
		}
	}
	
	//Change to a better name
	public boolean isCostumerBorrowed(Customer customer) {
		throw new UnsupportedOperationException("Not implemented yet");
	}



	public void rentBook(Book book, Customer customer) {
		if (book == null || !library.books.contains(book)) {
			return;
		}
		if (customer == null || !library.customers.contains(customer)) {
			return;
		}
		LocalDate now = LocalDate.now();
		RentRecord record = new RentRecord(book, customer, now, library.BORROW_DURATION, library.LATE_FEE_PER_DAY);
		library.rentRecords.add(record);
	}

	public void returnBook(Book book, Customer customer) {
		if (book == null || !library.books.contains(book)) {
			return;
		}
		if (customer == null || !library.customers.contains(customer)) {
			return;
		}
		for (RentRecord rentRecord: library.rentRecords) {
			if (rentRecord.customer().equals(customer) 
					&& rentRecord.book().equals(book)) {
				LocalDate now = LocalDate.now();
				ReturnRecord returnRecord = new ReturnRecord(rentRecord, now);
				library.returnRecords.add(returnRecord);
				library.rentRecords.remove(rentRecord);
			}
		}

	}



}