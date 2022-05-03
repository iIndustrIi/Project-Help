package student.jonathanwhite.librarysystem;

import java.time.LocalDate;

public record Customer(String name, String address, String phone, LocalDate registeredDate) {

	public Customer(String name, String address, String phone) {
		this(name, address, phone, LocalDate.now());
	}
	
}
