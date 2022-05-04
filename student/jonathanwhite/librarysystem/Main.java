/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem;

import java.awt.Font;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import student.jonathanwhite.librarysystem.ui.MainMenuWindow;

public class Main {
	
	public static final Library library = new Library();
	public static final LibraryService service = new LibraryService(library);
	public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
	public static final Font fontBold = new Font("Segoe UI", Font.BOLD, 30);
	public static final Font font = new Font("Segoe UI", Font.PLAIN, 30);
	public static final Font smallFont = new Font("Segoe UI", Font.PLAIN, 16);
	
	public static void main(String[] args) {
		new MainMenuWindow().setVisible(true);
	}

}
