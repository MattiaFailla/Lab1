package ristorante.Registration;

import _database.Database;
import _database.objects.Restaurant;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
	private JButton registerButton;

	public RestaurantRegistration() {
		setContentPane(contentPane);
		setModal(true);

		//region setBorder to Color.red
		nameField.setBorder(new LineBorder(Color.red));
		phoneField.setBorder(new LineBorder(Color.red));
		websiteField.setBorder(new LineBorder(Color.red));
		streetNameField.setBorder(new LineBorder(Color.red));
		civicNumberField.setBorder(new LineBorder(Color.red));
		cityField.setBorder(new LineBorder(Color.red));
		provinceField.setBorder(new LineBorder(Color.red));
		capField.setBorder(new LineBorder(Color.red));
		//endregion

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region registerButton events
		registerButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { registerButton.setEnabled(allFieldValid()); }
			public void mouseExited(MouseEvent e) { }
		});
		registerButton.addActionListener(e -> {
			// Getting data from the form
			String name = this.nameField.getText();
			Long phoneNumber = Long.valueOf(this.phoneField.getText());
			String url = this.websiteField.getText();
			String qualifier = String.valueOf(this.qualifierComboBox.getSelectedItem());
			String street = this.streetNameField.getText();
			Integer civicNumber = Integer.valueOf(this.civicNumberField.getText());
			String city = this.cityField.getText();
			String province = this.provinceField.getText();
			Integer CAP = Integer.valueOf(this.capField.getText());
			Restaurant.types type = (Restaurant.types) this.typologyBox.getSelectedItem();

			// Saving the username in the database
			Database.insertRestaurant(name, phoneNumber, qualifier, street, civicNumber, city, province, CAP, url, type);
			JOptionPane.showMessageDialog(null, "Registered successful");
			dispose();
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

		phoneField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { phoneField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		phoneField.addCaretListener(e -> {
			Color color = Database.regexPhone(phoneField.getText()) ? Color.green : Color.red;
			phoneField.setBorder(new LineBorder(color));
		});

		websiteField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { websiteField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		websiteField.addCaretListener(e -> {
			Color color = Database.regexURL(websiteField.getText()) ? Color.green : Color.red;
			websiteField.setBorder(new LineBorder(color));
		});

		streetNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { streetNameField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		streetNameField.addCaretListener(e -> {
			Color color = Database.regexStandard(streetNameField.getText()) ? Color.green : Color.red;
			streetNameField.setBorder(new LineBorder(color));
		});

		civicNumberField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { civicNumberField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		civicNumberField.addCaretListener(e -> {
			Color color = Database.regexNumber(civicNumberField.getText(), "{1,3}") ? Color.green : Color.red;
			civicNumberField.setBorder(new LineBorder(color));
		});

		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { cityField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		cityField.addCaretListener(e -> {
			Color color = Database.regexStandard(cityField.getText()) ? Color.green : Color.red;
			cityField.setBorder(new LineBorder(color));
		});

		provinceField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { provinceField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		provinceField.addCaretListener(e -> {
			Color color = Database.regexProvince(provinceField.getText()) ? Color.green : Color.red;
			provinceField.setBorder(new LineBorder(color));
		});

		capField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { capField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		capField.addCaretListener(e -> {
			Color color = Database.regexNumber(capField.getText(), "{5}") ? Color.green : Color.red;
			capField.setBorder(new LineBorder(color));
		});
		//endregion
	}

	private boolean allFieldValid() {
		Color[] colorArray = {
				((LineBorder)nameField.getBorder()).getLineColor(),
				((LineBorder)phoneField.getBorder()).getLineColor(),
				((LineBorder)websiteField.getBorder()).getLineColor(),
				((LineBorder)streetNameField.getBorder()).getLineColor(),
				((LineBorder)civicNumberField.getBorder()).getLineColor(),
				((LineBorder)cityField.getBorder()).getLineColor(),
				((LineBorder)provinceField.getBorder()).getLineColor(),
				((LineBorder)capField.getBorder()).getLineColor()
		};

		for(Color color : colorArray) if(color == Color.red) return false;
		return true;
	}

	public static void main() {
		RestaurantRegistration dialog = new RestaurantRegistration();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Restaurant - Registration");
		dialog.setVisible(true);
	}
}