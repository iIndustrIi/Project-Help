package student.jonathanwhite.librarysystem.ui.info;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import student.jonathanwhite.librarysystem.Main;
import student.jonathanwhite.librarysystem.ui.SubWindow;
import student.jonathanwhite.librarysystem.ui.tableselectors.TableSelector.RunnableButton;

public abstract class InfoWindow extends SubWindow {

	private static final long serialVersionUID = 1L;
	
	public final InfoPanel infoPanel; 
	public final Map<String, String> map;
	public final JPanel buttonsPanel;
	public final JPanel tablePanel;
	
	public InfoWindow() {
		super();
		map = new LinkedHashMap<>();
		infoPanel = new InfoPanel();
		buttonsPanel = new JPanel();
		tablePanel = new JPanel();
		JPanel contentPane = new JPanel();

		contentPane.setBorder(BorderFactory.createEmptyBorder(25, 25,25,25));
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		contentPane.add(infoPanel);
		contentPane.add(tablePanel);
		contentPane.add(buttonsPanel);
		
		setContentPane(contentPane);
	}
	
	public void label(String key, String value) {
		map.put(key, value);
		infoPanel.label(key, value);
	}
	
	public void addButton(RunnableButton button) {
		button.addActionListener(e -> {
			button.run();
		});
        button.setFont(Main.font);
        button.setFocusable(false);
        buttonsPanel.add(button);
	}
	
	public static class InfoPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		GridBagConstraints c = new GridBagConstraints();
		int count = 0;
		public InfoPanel() {
	        setLayout(new GridBagLayout());
	        setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
	        c.fill = GridBagConstraints.HORIZONTAL;
		}
		
		public void label(String key, String value) {
			JLabel label0 = new JLabel(key + ": ");
			label0.setFont(Main.fontBold);
			JLabel label1 = new JLabel(value);
			label1.setFont(Main.font);
			c = createGbc(0, count);
	        add(label0, c);
			c = createGbc(1, count);
	        add(label1, c);
	        count++;
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
