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

import javax.swing.JOptionPane;

import student.jonathanwhite.librarysystem.Customer;
import student.jonathanwhite.librarysystem.Main;

public class CustomerRegistryWindow extends Formulary {

	private static final long serialVersionUID = 1L;
	
	public CustomerRegistryWindow() {
		super("Register a new customer", "Register", "Name", "Address");
        setTitle("Book Registry");
        pack();
	}
	
	@Override
	protected void addFields(String... entries) {
		super.addFields(entries);
		try {
			fields.add(fieldPanel.field(new PhoneField()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean checkAndAccept(String[] fields) {
		Customer customer = new Customer(fields[0], fields[1], fields[2]);
		Main.service.addCustomer(customer);
		return true;
	}
	
    private static final class PhoneField extends Field {

        private static final long serialVersionUID = 8997075146338662662L;

        public PhoneField() throws ParseException {
            super("Phone Number");
            setColumns(8);
            addKeyListener(new KeyAdapter() {
            	public void keyPressed(KeyEvent ke) {
            		if (ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
                		setEditable(true);
            			setText(format(getText()));
            		} else if (ke.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                		setEditable(true);
            		} else if (ke.getKeyCode() == KeyEvent.VK_DELETE) {
                		setEditable(true);
            		} else {
                		setEditable(false);
            		}
                }
            	public String format(String string) {
            		String onlyNumbers = string.replaceAll("\\D+","");
            		String formated = onlyNumbers;
            		
               		if (string.length() >= 14) {
                		setEditable(false);
                		return string.substring(0, 14);
            		} else {
                		setEditable(true);
            		}
            		
            		if (onlyNumbers.length() > 0) {
            			formated = "(" + formated;
            		} else return formated;
            		if (onlyNumbers.length() >= 3) {
            			formated = addChar(formated, ')', 4);
            		} else return formated;
               		if (onlyNumbers.length() >= 4) {
            			formated = addChar(formated, ' ', 5);
            		} else return formated;
               		if (onlyNumbers.length() >= 7) {
            			formated = addChar(formated, '-', 9);
            		}
               		return formated;
            	}
            	public static String addChar(String string, char ch, int index) {
            		return string.substring(0,index)+ch+string.substring(index);
            	}
             });
        }

		@Override
		public boolean isValidField() {
			return getText() != null && !getText().isBlank() && getText().length() == 14;
		}

		@Override
		public void onInvalid() {
			String message = "The provided phone number is not valid";
			JOptionPane.showMessageDialog(null, message);
		}

    }

}
