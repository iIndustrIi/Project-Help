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
import javax.swing.table.DefaultTableModel;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.SubWindow;
import student.jonathanwhite.librarysystem.ui.info.InfoWindow;

public class TableSelector extends SubWindow {

	private static final long serialVersionUID = 1L;
	
	public final JTable table;
	public final JScrollPane scrollPane;
	public final DefaultTableModel model;
	
	public final JPanel buttonsPanel;
	
	public static <E> List<InfoWindow> getInfos(List<E> list, Function<E, InfoWindow> function) {	
		List<InfoWindow> infos = new ArrayList<>();
		for (E e: list) {
			infos.add(function.apply(e));
		}
		return infos;
	}
	
	public <E> TableSelector(List<E> list, Function<E, InfoWindow> function, String bigText) {	
		this(getInfos(list, function), bigText);
	}
	
	public TableSelector(List<InfoWindow> infos, String bigText) {	
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
		
		if (infos.size() > 0) {
			model.setColumnIdentifiers(infos.get(0).map.keySet().toArray());
			for (InfoWindow info: infos) {
				model.addRow(info.map.values().toArray());
				for (Component c: info.buttonsPanel.getComponents()) {
					buttonsPanel.add(c);
				}
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
		table.setFocusable(false);
		table.setIntercellSpacing(new Dimension(0, 0));
		table.setRowHeight(25);
		table.setShowVerticalLines(false);
		
		contentPane.add(label);
		contentPane.add(scrollPane);
        contentPane.add(buttonsPanel);
		
        setContentPane(contentPane);
	}
	
	public void addButton(RunnableButton button) {
		button.addActionListener(e -> {
			button.run();
		});
        button.setFont(Main.font);
        button.setFocusable(false);
        buttonsPanel.add(button);
	}
	
	public void addTableButton(IntConsumerButton button) {
		button.addActionListener(e -> {
			for (int i: table.getSelectedRows()) {
				button.accept(i);
			}
		});
        button.setFont(Main.font);
        button.setFocusable(false);
        buttonsPanel.add(button);
	}
	
	public static class IntConsumerButton extends JButton implements IntConsumer {
		private static final long serialVersionUID = 1L;
		private final IntConsumer consumer;
		public IntConsumerButton(String name, IntConsumer consumer) {
			super(name);
			this.consumer = consumer;
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
		}

		@Override
		public void run() {
			runnable.run();
		}	
	}
}
