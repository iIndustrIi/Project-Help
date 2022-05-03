package student.jonathanwhite.librarysystem.ui.registers;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.SubWindow;

public abstract class Formulary extends SubWindow implements ActionListener {

	private static final long serialVersionUID = 1L;

	public final List<Field> fields;
	public final FieldPanel fieldPanel;
	
	public Formulary(String bigText, String buttonText, String... entries) {
		fieldPanel = new FieldPanel();
		fields = new ArrayList<>();
		addFields(entries);
		JButton button = new JButton(buttonText);
		button.addActionListener(this);
		
		JPanel contentPane = new JPanel();
		
		contentPane.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel(bigText);
        label.setFont(Main.font);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFont(Main.font);
        button.setFocusable(false);
        
        contentPane.add(label);
        contentPane.add(fieldPanel);
        contentPane.add(button);
		
        setContentPane(contentPane);
	}

	protected void addFields(String... entries) {
		for (int i = 0; i < entries.length; i++) {
			fields.add(fieldPanel.field(entries[i]));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String[] fieldTexts = new String[fields.size()];
		for (int i = 0; i < fields.size(); i++) {
			Field field = fields.get(i);
			if (!field.isValid()) {
				field.onInvalid();
				return;
			}
			fieldTexts[i] = field.getText();
		}
		if (!checkAndAccept(fieldTexts)) {
			return;
		}
		dispose();
	}
	
	protected abstract boolean checkAndAccept(String[] fields);
	
	public static class NonBlankField extends Field {
		private static final long serialVersionUID = 1L;
		public NonBlankField(String name) {
			super(name);
			setColumns(12);
		}
		public boolean isValidField() {
			return getText() != null && !getText().isBlank();
		}
		public void onInvalid() {
			String message = "Please, provide the " + name;
			JOptionPane.showMessageDialog(null, message);
		}
	}
	
	public abstract static class Field extends JFormattedTextField {
		private static final long serialVersionUID = 1L;
		public final String name;
		public Field(String name) {
			super();
			this.name = name;
		}
		public abstract boolean isValidField();
		public abstract void onInvalid();
	}
	
	public static class FieldPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		GridBagConstraints c = new GridBagConstraints();
		int count = 0;
		public FieldPanel() {
	        setLayout(new GridBagLayout());
	        setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
	        c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		public Field field(String name) {
	        return field(new NonBlankField(name));
		}
		
		public Field field(Field textField) {
			JLabel label = new JLabel(textField.name + ": ");
			label.setFont(Main.font);
			textField.setFont(Main.font);
			c = createGbc(0, count);
	        add(label, c);
			c = createGbc(1, count);
	        add(textField , c);
	        count++;
	        return textField;
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
}
