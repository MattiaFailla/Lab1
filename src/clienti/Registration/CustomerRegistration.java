package clienti.Registration;

import _database.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

import static java.lang.String.valueOf;
import static javax.swing.JOptionPane.showMessageDialog;

public class CustomerRegistration extends JDialog{
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField cityField;
	private JTextField provinceField;
	private JTextField emailField;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton registerButton;

	public CustomerRegistration() {
		setContentPane(contentPane);
		setModal(true);

		// region cancelButton events
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		//contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		// endregion

		// region registerButton events
		registerButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// Getting data from the form
				try {
					String name = nameField.getText();
					String surname = surnameField.getText();
					String city = cityField.getText();
					String province = provinceField.getText();
					String email = emailField.getText();
					String nickname = nicknameField.getText();
					String password = valueOf(passwordField.getPassword());

					// Trying to register the customer
					Database.insertClient(name, surname, city, province, email, nickname, password);
				}
				finally {
					// Saving the username in the database
					showMessageDialog(null, "Registered successfully!");
				}
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { registerButton.setEnabled(allFieldValid()); }
			public void mouseExited(MouseEvent e) { }
		});
		// endregion

		//region Action events
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nameField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		nameField.addCaretListener(e -> {
			Color color = Database.regexStandard(nameField.getText()) ? Color.green : Color.red;
			nameField.setBorder(new LineBorder(color));
		});

		surnameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { surnameField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		surnameField.addCaretListener(e -> {
			Color color = Database.regexStandard(surnameField.getText()) ? Color.green : Color.red;
			surnameField.setBorder(new LineBorder(color));
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

		emailField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { emailField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		emailField.addCaretListener(e -> {
			Color color = Database.regexEmail(emailField.getText()) ? Color.green : Color.red;
			emailField.setBorder(new LineBorder(color));
		});

		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		nicknameField.addCaretListener(e -> {
			Color color = Database.regexNickname(nicknameField.getText()) ? Color.green : Color.red;
			nicknameField.setBorder(new LineBorder(color));
		});

		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		passwordField.addCaretListener(e -> {
			Color color = Database.regexPassword(valueOf(passwordField.getPassword())) ? Color.green : Color.red;
			passwordField.setBorder(new LineBorder(color));
		});
		//endregion
	}

	private boolean allFieldValid() {
		Color[] colorArray = {
				((LineBorder)nameField.getBorder()).getLineColor(),
				((LineBorder)surnameField.getBorder()).getLineColor(),
				((LineBorder)cityField.getBorder()).getLineColor(),
				((LineBorder)provinceField.getBorder()).getLineColor(),
				((LineBorder)emailField.getBorder()).getLineColor(),
				((LineBorder)nicknameField.getBorder()).getLineColor(),
				((LineBorder)passwordField.getBorder()).getLineColor()
		};

		for(Color color : colorArray) if(color == Color.red) return false;
		return true;
	}

	public static void main() {
		CustomerRegistration dialog = new CustomerRegistration();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}