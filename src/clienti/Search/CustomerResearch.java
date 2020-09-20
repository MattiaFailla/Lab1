package clienti.Search;

import _database.Database;
import _database.objects.Restaurant;
import clienti.Login.CustomerLogin;
import clienti.Registration.CustomerRegistration;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class CustomerResearch extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField cityField;
	private JComboBox<String> typologyBox;
	private JRadioButton nameRadio;
	private JRadioButton cityRadio;
	private JRadioButton typologyRadio;
	private JRadioButton cityandtypologyRadio;
	private JTextArea searchArea;
	private JButton researchButton;
	private JButton registrationButton;
	private JButton loginButton;
	public static boolean isLogged = false;

	public CustomerResearch() {
		setContentPane(contentPane);
		setModal(true);

		//region setBorder to Color.red
		nameField.setBorder(new LineBorder(Color.red));
		cityField.setBorder(new LineBorder(Color.red));
		//endregion

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region researchButton events
		researchButton.addActionListener(e -> {
			String typeNull = "- nothing -";
			Restaurant.types type = null;

			String name = this.nameField.getText();
			String city = this.cityField.getText();
			String typeStr = String.valueOf(this.typologyBox.getSelectedItem());
			if (!typeStr.equals(typeNull)) { type = Restaurant.types.valueOf(typeStr.toUpperCase()); }

			List<Restaurant> result;
			try {
				if (nameRadio.isSelected()) result = Database.getRestaurantByName(name);
				else if (cityRadio.isSelected()) result = Database.getRestaurantByCity(city);
				else if (typologyRadio.isSelected()) result = Database.getRestaurantByCategory(type);
				else result = Database.getRestaurantByCityAndType(city, type);

				if (result.isEmpty()) {
					JOptionPane.showMessageDialog(null, "No result found");
				}

				Collections.reverse(result);
				//searchArea.append();
			}
			catch (IOException | ClassNotFoundException ioException) { ioException.printStackTrace(); }
		});
		//endregion

		//region Switch windows
		registrationButton.addActionListener(e -> CustomerRegistration.main());
		loginButton.addActionListener(e -> {
			CustomerLogin.main();
			loginButton.setEnabled(!isLogged);
		});
		//endregion

		//region RadioButton events
		nameRadio.addActionListener(e -> {
			cityRadio.setSelected(false);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
		});
		cityRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			typologyRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
		});
		typologyRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(false);
			cityandtypologyRadio.setSelected(false);
		});
		cityandtypologyRadio.addActionListener(e -> {
			nameRadio.setSelected(false);
			cityRadio.setSelected(false);
			typologyRadio.setSelected(false);
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
		CustomerResearch dialog = new CustomerResearch();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Search");
		dialog.setVisible(true);
	}
}