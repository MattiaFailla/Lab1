package clienti.Search;

import _database.Database;
import _database.DatabaseExceptions;
import _database.objects.Restaurant;
import clienti.Login.CustomerLogin;
import clienti.Registration.CustomerRegistration;
import ristoratori._Profile.RestaurantProfile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomerSearch extends JDialog {
	private String customerName = "";
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
	private JRadioButton clearRadio;
	private List<Restaurant> result = new ArrayList<>();

	public CustomerSearch() {
		setContentPane(contentPane);
		setModal(true);

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		//endregion

		//region setBorder to Color.red
		nameField.setBorder(new LineBorder(Color.red));
		cityField.setBorder(new LineBorder(Color.red));
		//endregion

		//region Initializing searchTable
		DefaultTableModel tableModel = new DefaultTableModel(0, 3) {
			private static final long serialVersionUID = -6744499930217269875L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addRow(new String[]{"Name", "City", "Typology"});
		searchTable.setModel(tableModel);
		printRestaurants(tableModel);
		//endregion

		//region Initializing radioButton
		nameRadio.addActionListener(e -> {
			nameRadio.setSelected(true);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
			clearRadio.setSelected(false);
		});

		cityRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(true);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
			clearRadio.setSelected(false);
		});

		typologyRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(true);
			cityandtypologyRadio.setSelected(false);
			clearRadio.setSelected(false);
		});

		cityandtypologyRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(true);
			clearRadio.setSelected(false);
		});

		clearRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
			clearRadio.setSelected(true);
		});
		//endregion

		//region researchButton events
		researchButton.registerKeyboardAction(e -> search(tableModel), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		researchButton.addActionListener(e -> search(tableModel));
		//endregion

		//region searchTable events
		searchTable.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					try {
						int selectedRow = searchTable.getSelectedRow();
						if (selectedRow > 0) {
							String nameRestaurant = String.valueOf(tableModel.getValueAt(selectedRow, 0));
							Restaurant restaurant = Database.getRestaurant(nameRestaurant);
							RestaurantProfile.main(restaurant, false, customerName);
						}
					} catch (IOException | ClassNotFoundException ioException) {
						ioException.printStackTrace();
					} catch (DatabaseExceptions dbException) {
						JOptionPane.showMessageDialog(null, "Restaurant not found.");
					}
				}
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}
		});
		//endregion

		//region Switch window
		registerButton.addActionListener(e -> CustomerRegistration.main());
		loginButton.addActionListener(e -> {
			this.customerName = CustomerLogin.main();
			loginButton.setEnabled(customerName.isEmpty());
		});
		//endregion

		//region Focus events
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nameField.selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
		});
		nameField.addCaretListener(e -> {
			Color color = Database.regexStandard(nameField.getText()) ? Color.green : Color.red;
			nameField.setBorder(new LineBorder(color));
		});

		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				cityField.selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
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

	private void search(DefaultTableModel tableModel) {
		Restaurant.types type = null;

		String name = this.nameField.getText();
		String city = this.cityField.getText();
		String typeStr = String.valueOf(this.typologyBox.getSelectedItem());
		if (typeStr != null) type = Restaurant.types.valueOf(typeStr.toUpperCase());

		try {
			if (nameRadio.isSelected()) this.result = Database.getRestaurantByName(name);
			else if (cityRadio.isSelected()) this.result = Database.getRestaurantByCity(city);
			else if (typologyRadio.isSelected()) this.result = Database.getRestaurantByCategory(type);
			else if (cityandtypologyRadio.isSelected()) this.result = Database.getRestaurantByCityAndType(city, type);
			else {
				this.result.clear();
				printRestaurants(tableModel);
			}

			if (!this.result.isEmpty()) printRestaurants(tableModel);
			else JOptionPane.showMessageDialog(null, "No result found");
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		}
	}

	private void printRestaurants(DefaultTableModel tableModel) {
		tableModel.setRowCount(1);
		if (this.result.isEmpty()) {
			try {
				this.result = Database.getRestaurants();
			} catch (IOException | ClassNotFoundException ioException) {
				ioException.printStackTrace();
			}
		}
		for (Restaurant res : this.result) tableModel.addRow(new Object[]{res.name, res.city, res.type});
	}
}