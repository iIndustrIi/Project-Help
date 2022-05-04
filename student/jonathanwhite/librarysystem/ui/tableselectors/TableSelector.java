/*
 * Student Name: Jonathan White
 * Date Due: 05/03/2022
 * Date Submitted: 05/03/2022
 * Program Name: Library Reservation System
 * Program Description:  A library reservation system capable of handling the renting and returning of books, as well as the creation and detailing of both said books and customers.
*/

package student.jonathanwhite.librarysystem.ui.tableselectors;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntConsumer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.SubWindow;
import student.jonathanwhite.librarysystem.ui.info.InfoWindow;

public class TableSelector extends SubWindow {

	private static final long serialVersionUID = 1L;
	
	public final JTable table;
	public final JScrollPane scrollPane;
	public final DefaultTableModel model;
	boolean alreadyAddedInfo = false;
	public final JPanel buttonsPanel;
	
	List<InfoWindow> infos;
	
	public static <E> List<InfoWindow> getInfos(List<E> list, Function<E, InfoWindow> function) {	
		List<InfoWindow> infos = new ArrayList<>();
		for (E e: list) {
			infos.add(function.apply(e));
		}
		return infos;
	}
	
	public <E> TableSelector(List<E> list, List<JButton> buttons, Function<E, InfoWindow> function, String bigText) {	
		this(getInfos(list, function), buttons, bigText);
	}
	
	public TableSelector(List<InfoWindow> infos, List<JButton> buttons, String bigText) {	
		model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		table = new JTable(model);
		scrollPane = new JScrollPane(table);
		buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		this.infos = infos;
		
		for (JButton c: buttons) {
			addButton(c);
		}
		if (infos.size() > 0) {
			alreadyAddedInfo = true;
			model.setColumnIdentifiers(infos.get(0).map.keySet().toArray());
			for (InfoWindow info: infos) {
				model.addRow(info.map.values().toArray());
			}
		}
		
		JPanel contentPane = new JPanel();
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		contentPane.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel(bigText);
        label.setFont(Main.font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        
		table.setFont(Main.smallFont);
		table.setBorder(BorderFactory.createEmptyBorder());
		table.getTableHeader().setBackground(Color.lightGray);
		table.getTableHeader().setFont(Main.smallFont);
		table.getTableHeader().setOpaque(true);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setFocusable(false);
		table.setFocusable(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(25);
		table.setShowVerticalLines(true);
		table.setShowHorizontalLines(true);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	if (table.getSelectedRow() > -1) {
		        	infoButtonPanel(infos.get(table.getSelectedRow()));
	        	}
	        }
	    });
		
		scrollPane.setPreferredSize(new Dimension(1000, 500));
		
		contentPane.add(label);
		contentPane.add(scrollPane);
		contentPane.add(buttonsPanel);

        setContentPane(contentPane);
	}
	
	public void removeRow(int i) {
		model.removeRow(i);
		infos.remove(i);
	}
	
	public void addRow(InfoWindow info) {
		if (!alreadyAddedInfo) {
			model.setColumnIdentifiers(info.map.keySet().toArray());
		}
		infos.add(info);
		model.addRow(info.map.values().toArray());
	}
	
	public JPanel lastPanel;
	
	public JPanel infoButtonPanel(InfoWindow info) {
		if (lastPanel != null)
			getContentPane().remove(lastPanel);
		getContentPane().revalidate();
		getContentPane().repaint();
		getContentPane().add(info.buttonsPanel);
		getContentPane().validate();
		getContentPane().repaint();
		validate();
		repaint();
		return buttonsPanel;
	}
	
	public void addButton(JButton button) {
        button.setFont(Main.font);
        button.setFocusable(false);
		if (button instanceof IntConsumerButton b) {
			addTableButton(b);
		}
        buttonsPanel.add(button);
	}
	
	private void addTableButton(IntConsumerButton button) {
		button.addActionListener(e -> {
			for (int i: table.getSelectedRows()) {
				button.accept(i);
			}
		});
	}
	
	public static class IntConsumerButton extends JButton implements IntConsumer {
		private static final long serialVersionUID = 1L;
		private final IntConsumer consumer;
		public IntConsumerButton(String name, IntConsumer consumer) {
			super(name);
			this.consumer = consumer;
			setFocusable(false);
			setBackground(Color.lightGray);
			setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		}

		@Override
		public void accept(int i) {
			consumer.accept(i);
		}	
	}
	
	public static class RunnableButton extends JButton implements Runnable {
		private static final long serialVersionUID = 1L;
		private final Runnable runnable;
		public RunnableButton(String name, Runnable runnable) {
			super(name);
			this.runnable = runnable;
			setFocusable(false);
			setBackground(Color.lightGray);
			setFont(Main.font);
			setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
			addActionListener(e -> {
				run();
			});
		}

		@Override
		public void run() {
			runnable.run();
		}	
	}
}
