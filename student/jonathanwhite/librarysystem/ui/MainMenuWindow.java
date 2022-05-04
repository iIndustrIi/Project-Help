/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.tableselectors.BooksWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.CustomersWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.RentsWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.ReturnsWindow;

public class MainMenuWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public MainMenuWindow() {
		JPanel tablesPanel = new JPanel();
		tablesPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		tablesPanel.setLayout(new GridBagLayout());
		
		JButton booksTable = new JButton("Books");        
		JButton customersTable = new JButton("Customers");        
		JButton rentsTable = new JButton("Rents");        
		JButton returnsTable = new JButton("Returns");
		
		booksTable.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		customersTable.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		rentsTable.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		returnsTable.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		
		booksTable.setFocusable(false);
		customersTable.setFocusable(false);
		rentsTable.setFocusable(false);
		returnsTable.setFocusable(false);
		
		booksTable.setBackground(Color.lightGray);
		customersTable.setBackground(Color.lightGray);
		rentsTable.setBackground(Color.lightGray);
		returnsTable.setBackground(Color.lightGray);
		
		booksTable.setFont(Main.font);
		customersTable.setFont(Main.font);
		rentsTable.setFont(Main.font);
		returnsTable.setFont(Main.font);
		
		booksTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		customersTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		rentsTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		returnsTable.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c = createGbc(0, 0);
		tablesPanel.add(booksTable, c);
		c = createGbc(1, 0);
		tablesPanel.add(customersTable, c);
		c = createGbc(0, 1);
		tablesPanel.add(rentsTable, c);
		c = createGbc(1, 1);
		tablesPanel.add(returnsTable, c);
		
		booksTable.addActionListener(e -> new BooksWindow().setVisible(true));
		customersTable.addActionListener(e -> new CustomersWindow().setVisible(true));
		rentsTable.addActionListener(e -> new RentsWindow().setVisible(true));
		returnsTable.addActionListener(e -> new ReturnsWindow().setVisible(true));
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
	
		
		JLabel label = new JLabel("Library System"); 
		label.setFont(Main.fontBold); 
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		tablesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		contentPane.add(label);
		contentPane.add(tablesPanel);
		
		setContentPane(contentPane);
		
        setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
        setLocationRelativeTo(null);
	}
	
	private GridBagConstraints createGbc(int x, int y) {
		GridBagConstraints gbc = new GridBagConstraints();
		Insets WEST_INSETS = new Insets(5, 0, 5, 5);
		Insets EAST_INSETS = new Insets(5, 5, 5, 0);
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;

		gbc.anchor = (x == 0) ? GridBagConstraints.WEST : GridBagConstraints.EAST;
		gbc.fill = (x == 0) ? GridBagConstraints.BOTH : GridBagConstraints.HORIZONTAL;

		gbc.insets = (x == 0) ? WEST_INSETS : EAST_INSETS;
		gbc.weightx = (x == 0) ? 0.1 : 1.0;
		gbc.weighty = 1.0;
		return gbc;
	}
	
}
