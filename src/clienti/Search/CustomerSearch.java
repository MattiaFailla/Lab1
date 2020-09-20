package clienti.Search;

import _database.Database;
import _database.objects.Restaurant;
import clienti.Login.CustomerLogin;
import clienti.Registration.CustomerRegistration;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CustomerSearch extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField cityField;
	private JComboBox<String> typologyBox;
	private JRadioButton nameRadio;
	private JRadioButton cityRadio;
	private JRadioButton typologyRadio;
	private JRadioButton cityandtypologyRadio;
	private JTable searchTable;
	private JButton researchButton;
	private JButton registerButton;
	private JButton loginButton;
	public static boolean isLogged = false;

	public CustomerSearch() {
		setContentPane(contentPane);
		setModal(true);

		//region setBorder to Color.red
		nameField.setBorder(new LineBorder(Color.red));
		cityField.setBorder(new LineBorder(Color.red));
		//endregion

		//region addColumn to searchTable
		String[] columnNames = {"Name", "City", "Typology"};
		DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {
			//all cells false
			public boolean isCellEditable(int row, int column) { return false; }
			private static final long serialVersionUID = 7007554847444425016L;
		};
		tableModel.addRow(columnNames);
		searchTable.setModel(tableModel);
		//endregion

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region researchButton events
		researchButton.addActionListener(e -> {
			Restaurant.types type = null;
			tableModel.setRowCount(1);

			String name = this.nameField.getText();
			String city = this.cityField.getText();
			String typeStr = String.valueOf(this.typologyBox.getSelectedItem());
			if (typeStr != null) { type = Restaurant.types.valueOf(typeStr.toUpperCase()); }

			List<Restaurant> result;
			try {
				if (nameRadio.isSelected()) result = Database.getRestaurantByName(name);
				else if (cityRadio.isSelected()) result = Database.getRestaurantByCity(city);
				else if (typologyRadio.isSelected()) result = Database.getRestaurantByCategory(type);
				else result = Database.getRestaurantByCityAndType(city, type);

				if (result.isEmpty()) JOptionPane.showMessageDialog(null, "No result found");
				else {
					Collections.reverse(result);
					// Adding the result of the search to the table
					for (Restaurant rst : result) { tableModel.addRow(new Object[]{rst.name, rst.city, rst.type}); }
				}
			}
			catch (IOException | ClassNotFoundException ioException) { ioException.printStackTrace(); }
		});
		//endregion

		//region Switch windows
		registerButton.addActionListener(e -> CustomerRegistration.main());
		loginButton.addActionListener(e -> {
			CustomerLogin.main();
			loginButton.setEnabled(!isLogged);
		});
		//endregion

		//region RadioButton events
		nameRadio.addActionListener(e -> {
			nameRadio.setSelected(true);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
		});
		cityRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(true);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
		});
		typologyRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(true);
			cityandtypologyRadio.setSelected(false);
		});
		cityandtypologyRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(true);
		});
		//endregion

		//region Focus events
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nameField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		nameField.addCaretListener(e -> {
			Color color = Database.regexStandard(nameField.getText()) ? Color.green : Color.red;
			nameField.setBorder(new LineBorder(color));
		});

		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { cityField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		cityField.addCaretListener(e -> {
			Color color = Database.regexStandard(cityField.getText()) ? Color.green : Color.red;
			cityField.setBorder(new LineBorder(color));
		});


		//endregion
	}

	public static void main() {
		CustomerSearch dialog = new CustomerSearch();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Search");
		dialog.setVisible(true);
	}
}