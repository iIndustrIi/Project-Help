/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.registers;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import student.jonathanwhite.librarysystem.Book;
import student.jonathanwhite.librarysystem.Main;

public class BookRegistryWindow extends Formulary {

	private static final long serialVersionUID = 1L;
	
	public BookRegistryWindow() {
		super("Register a new book", "Register", "Title", "Author", "Publisher");
		setTitle("Book Registry");
		pack();
	}

	@Override
	protected boolean checkAndAccept(String[] fields) {
		LocalDate localDate = LocalDate.parse(fields[3], Main.dateFormat);
		Book book = new Book(fields[0], fields[1], fields[2], localDate);
		Main.service.addBook(book);
		return true;
	}
	
	@Override
	protected void addFields(String... entries) {
		super.addFields(entries);
		try {
			fields.add(fieldPanel.field(new DateField()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    private static final class DateField extends Field {

        private static final long serialVersionUID = 8997075146338662662L;
        
        public DateField() throws ParseException {
            super("Release Date", createFormatter("##/##/##"));
            setColumns(8);
            addKeyListener(new KeyAdapter() {
            	public void keyPressed(KeyEvent ke) {
	

                }
             });
        }
        
		@Override
		public boolean isValidField() {
			return getText() != null && !getText().isBlank() && validateDate(getText());
		}

		@Override
		public void onInvalid() {
			String message = "The provided date is not valid";
			JOptionPane.showMessageDialog(null, message);
		}

    }
    
    protected static MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException exc) {
            System.err.println("formatter is bad: " + exc.getMessage());
            System.exit(-1);
        }
        return formatter;
    }
    
	public static boolean validateDate(String strDate) {
		if (strDate.isBlank()) {
			return false;
		} else {
			try {
				LocalDate.parse(strDate, Main.dateFormat);
			} catch (Exception e) {
				return false;
			}
			return true;
		}
	}

}
