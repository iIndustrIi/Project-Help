import java.time.LocalDate;

public record Customer(String name, String address, String phone, LocalDate registeredDate) {

}
