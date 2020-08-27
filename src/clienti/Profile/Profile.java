package clienti.Profile;

import database.Database;

import javax.swing.*;
import java.awt.event.*;

import database.Database;

public class Profile extends JDialog {
	private JPanel contentPane;
	private JLabel nicknameLabel;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField fullNameField;
	private JComboBox restaurantComboBox;
	private JButton saveButton;
	private JButton cancelButton;
	private JTextField NicknameField;

	public Profile() {
		setContentPane(contentPane);
		setModal(true);

		WriteInfo();

		saveButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Saved!");
		});

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

		NicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { NicknameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		emailField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { emailField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		fullNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { fullNameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
	}

	private boolean notValidateNick() { return NicknameField.getText().matches("\\W+|\\d+"); } //should not allow blank field

	private boolean notValidatePass() { return String.valueOf(passwordField.getPassword()).matches("^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$"); }

	private boolean notValidateEmail() { return emailField.getText().matches("\\W+|\\d+"); } // regex must be changed, same as nickname

	private boolean notValidateFname() { return fullNameField.getText().matches("\\W+|\\d+"); } // same as nickname

	public void WriteInfo() {
	}

	private void onCancel() {
		System.out.println("Closing app..");
		dispose();
	}

	private void onEnabled() {
		boolean checkName = NicknameField.getText().length() > 0;
		boolean checkFullName = fullNameField.getText().length() > 0;
		boolean checkEmail = emailField.getText().length() > 0;
		boolean checkPassword = String.valueOf(passwordField.getPassword()).length() > 0;
		boolean firstCouple = checkName && checkFullName;
		boolean secondCouple = checkEmail && checkPassword;
		saveButton.setEnabled(firstCouple && secondCouple);
	}

	public static void main(String[] args) {
		Profile dialog = new Profile();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}