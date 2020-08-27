package ristoratori.Registration;

import database.Database;

import javax.swing.*;
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
			public void mouseEntered(MouseEvent e) { }
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

			JOptionPane.showMessageDialog(null, "Registered Successfully!");
		});
		// endregion

		// region registerRestaurantButton events
		// @todo: error, close the main window after close the RestaurantRegistration
		registerRestaurantButton.addActionListener(e -> RestaurantRegistration.main());
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

			}
		});
		surnameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { surnameField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { cityField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		provinceField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { provinceField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		emailField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { emailField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		//endregion

		//region Input Validation
		nameField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { nameField.setEditable(Database.regexStandard(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		surnameField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { surnameField.setEditable(Database.regexStandard(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		cityField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { cityField.setEditable(Database.regexStandard(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		provinceField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		emailField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		nicknameField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { nicknameField.setEditable(Database.regexNickname(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		passwordField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { passwordField.setEditable(Database.regexPassword(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		//endregion
	}

	public static void main(String[] args) {
		EatAdvisorRegistration dialog = new EatAdvisorRegistration();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}