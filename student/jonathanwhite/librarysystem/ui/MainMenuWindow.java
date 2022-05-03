package student.jonathanwhite.librarysystem.ui;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import student.jonathanwhite.librarysystem.ui.registers.BookRegistryWindow;
import student.jonathanwhite.librarysystem.ui.registers.CustomerRegistryWindow;
import student.jonathanwhite.librarysystem.ui.registers.RentRegistryWindow;
import student.jonathanwhite.librarysystem.ui.registers.ReturnRegistryWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.BooksWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.CustomersWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.RentsWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.ReturnsWindow;

public class MainMenuWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;

	public MainMenuWindow() {
		
		JPanel registryPanel = new JPanel();
		registryPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		registryPanel.setLayout(new BoxLayout(registryPanel, BoxLayout.Y_AXIS));
		
		JButton bookRegistry = new JButton("New book");
		JButton customerRegistry = new JButton("New customer");
		JButton rentRegistry = new JButton("New rent");
		JButton returnRegistry = new JButton("New return");
		
		registryPanel.add(bookRegistry);
		registryPanel.add(customerRegistry);
		registryPanel.add(rentRegistry);
		registryPanel.add(returnRegistry);
		
		bookRegistry.addActionListener(e -> new BookRegistryWindow().setVisible(true));
		customerRegistry.addActionListener(e -> new CustomerRegistryWindow().setVisible(true));
		rentRegistry.addActionListener(e -> new RentRegistryWindow().setVisible(true));
		returnRegistry.addActionListener(e -> new ReturnRegistryWindow().setVisible(true));
		
		JPanel tablesPanel = new JPanel();
		tablesPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		tablesPanel.setLayout(new BoxLayout(tablesPanel, BoxLayout.Y_AXIS));
		
		JButton booksTable = new JButton("All books");
		JButton customersTable = new JButton("All customers");
		JButton rentsTable = new JButton("All rents");
		JButton returnsTable = new JButton("All returns");
		
		tablesPanel.add(booksTable);
		tablesPanel.add(customersTable);
		tablesPanel.add(rentsTable);
		tablesPanel.add(returnsTable);
		
		booksTable.addActionListener(e -> new BooksWindow().setVisible(true));
		customersTable.addActionListener(e -> new CustomersWindow().setVisible(true));
		rentsTable.addActionListener(e -> new RentsWindow().setVisible(true));
		returnsTable.addActionListener(e -> new ReturnsWindow().setVisible(true));
		
		JPanel contentPane = new JPanel();
		registryPanel.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		registryPanel.setLayout(new BoxLayout(registryPanel, BoxLayout.Y_AXIS));
		
		contentPane.add(registryPanel);
		contentPane.add(tablesPanel);
		
		setContentPane(contentPane);
		
        setResizable(false);
        setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
	}
	
}
