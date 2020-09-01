package ristoratori.Registration;

import _database.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class EatAdvisorRegistration extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField cityField;
	private JTextField provinceField;
	private JTextField emailField;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton cancelButton;
	private JButton registerButton;
	private JButton registerRestaurantButton;

	public EatAdvisorRegistration() {
		setContentPane(contentPane);
		setModal(true);

		// region registerButton events
		registerButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { registerButton.setEnabled(allFieldValid()); }
			public void mouseExited(MouseEvent e) { }
		});
		registerButton.addActionListener(e -> {
			// Lambda has been expanded to interact with the database

			// Getting data from the form
			String name = this.nameField.getText();
			String surname = this.surnameField.getText();
			String city = this.cityField.getText();
			String province = this.provinceField.getText();
			String email = this.emailField.getText();
			String nickname = this.nicknameField.getText();
			String password = String.valueOf(this.passwordField.getPassword());

			// Saving the username in the database
			Database.insertClient(name, surname, city, province, email, nickname, password);
			JOptionPane.showMessageDialog(null, "Registered successfully!");
		});
		// endregion

		// region cancelButton events
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // call onCancel() when cross is clicked
		cancelButton.addActionListener(e -> dispose());
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		// endregion

		//region Focus events
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nameField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = Database.regexStandard(nameField.getText()) ? Color.green : Color.red;
				nameField.setBorder(new LineBorder(color));
			}
		});
		surnameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { surnameField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = Database.regexStandard(surnameField.getText()) ? Color.green : Color.red;
				surnameField.setBorder(new LineBorder(color));
			}
		});
		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { cityField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = Database.regexStandard(cityField.getText()) ? Color.green : Color.red;
				cityField.setBorder(new LineBorder(color));
			}
		});
		provinceField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { provinceField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = Database.regexProvince(provinceField.getText()) ? Color.green : Color.red;
				provinceField.setBorder(new LineBorder(color));
			}
		});
		emailField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { emailField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = Database.regexEmail(emailField.getText()) ? Color.green : Color.red;
				emailField.setBorder(new LineBorder(color));
			}
		});
		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = Database.regexNickname(nicknameField.getText()) ? Color.green : Color.red;
				nicknameField.setBorder(new LineBorder(color));
			}
		});
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = Database.regexPassword(String.valueOf(passwordField.getPassword())) ? Color.green : Color.red;
				passwordField.setBorder(new LineBorder(color));
			}
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
		EatAdvisorRegistration dialog = new EatAdvisorRegistration();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}