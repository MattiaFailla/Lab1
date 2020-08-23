package ristoratori.Registration;

import database.Database;
import database.objects.Restaurant;

import javax.swing.*;
import java.awt.event.*;

public class RestaurantRegistration extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField websiteField;
	private JComboBox qualifierComboBox;
	private JTextField streetNameField;
	private JTextField civicNumberField;
	private JTextField cityField;
	private JTextField provinceField;
	private JTextField capField;
	private JComboBox typologyBox;
	private JButton cancelButton;
	private JButton registerButton;

	public RestaurantRegistration() {
		setContentPane(contentPane);
		setModal(true);

		// region registerButton events
		registerButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }

			public void mousePressed(MouseEvent e) { }

			public void mouseReleased(MouseEvent e) { }

			public void mouseEntered(MouseEvent e) { onEnabled(); }

			public void mouseExited(MouseEvent e) { }
		});
		registerButton.addActionListener(e -> {
			// Lambda has been expanded to interact with the database

			// Getting data from the form
			String name = this.nameField.getText();
			Integer phoneNumber = Integer.valueOf(this.phoneField.getText());
			String url = this.websiteField.getText();
			String qualifier = this.qualifierComboBox.getSelectedItem().toString();
			String street = this.streetNameField.getText();
			Integer civicNumber = Integer.valueOf(this.civicNumberField.getText());
			String city = this.cityField.getText();
			String province = this.provinceField.getText();
			Integer CAP = Integer.valueOf(this.capField.getText());
			Restaurant.types type = (Restaurant.types) this.typologyBox.getSelectedItem(); // convert to string ? ask by Lori
			// Saving the username in the database
			Database.insertRestaurant(name, phoneNumber, qualifier, street, civicNumber, city, province, CAP, url, type);

			JOptionPane.showMessageDialog(null, "Registered Successfully!");
		});
		// endregion

		// region cancelButton events
		cancelButton.addActionListener(e -> onCancel());
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { onCancel(); }
		});
		// endregion

		//region Focus events
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		phoneField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { phoneField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		websiteField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { websiteField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		streetNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { streetNameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		civicNumberField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { civicNumberField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { cityField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		provinceField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { provinceField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		capField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { capField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		//endregion
	}

	private void onCancel() {
		System.out.println("Closing app..");
		dispose();
	}

	private void onEnabled() {
		boolean checkName = nameField.getText().length() > 0;
		boolean checkPhone = phoneField.getText().length() > 0;
		boolean checkWebsite = websiteField.getText().length() > 0;
		boolean checkStreet = streetNameField.getText().length() > 0;
		boolean checkCivic = civicNumberField.getText().length() > 0;
		boolean checkCity = cityField.getText().length() > 0;
		boolean checkProvince = provinceField.getText().length() > 0;
		boolean checkCAP = capField.getText().length() > 0;
		boolean firstCouple = checkName && checkPhone;
		boolean secondCouple = checkStreet && checkCivic;
		boolean thirdCouple = checkCity && checkProvince && checkCAP;
		registerButton.setEnabled(firstCouple && checkWebsite && secondCouple && thirdCouple);
	}

	public static void main() {
		RestaurantRegistration dialog = new RestaurantRegistration();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
