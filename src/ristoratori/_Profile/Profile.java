package ristoratori._Profile;

import _database.Database;
import _database.objects.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Profile extends JDialog {
	private JPanel contentPane;
	private JLabel nicknameLabel;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField fullNameField;
	private JTextField cityField;
	private JTextField provinceField;
	private JComboBox restaurantComboBox;
	public static Client clt;

	public Profile() {
		setContentPane(contentPane);
		setModal(true);

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region Information's client
		nicknameLabel.setText(clt.nickname);
		passwordField.setText(clt.password);
		emailField.setText(clt.email);
		fullNameField.setText(clt.name + " " + clt.surname);
		cityField.setText(clt.city);
		provinceField.setText(clt.province);
		//todo: ottenere ristorante da nickname
		//restaurantComboBox.addItem(Database.getRestaurant("nome proprietario"));
		//endregion

		//region Focus events
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) {}
		});
		passwordField.addCaretListener(e->{
			Color color = Database.regexPassword(String.valueOf(passwordField.getPassword())) ? Color.green : Color.red;
			passwordField.setBorder(new LineBorder(color));
		});

		emailField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { emailField.selectAll(); }
			public void focusLost(FocusEvent e) {}
		});
		emailField.addCaretListener(e->{
			Color color = Database.regexEmail(emailField.getText()) ? Color.green : Color.red;
			emailField.setBorder(new LineBorder(color));
		});

		fullNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { fullNameField.selectAll(); }
			public void focusLost(FocusEvent e) {}
		});
		fullNameField.addCaretListener(e->{
			Color color = Database.regexStandard(fullNameField.getText()) ? Color.green : Color.red;
			fullNameField.setBorder(new LineBorder(color));
		});
		//endregion
	}

	public static void main() {
		Profile dialog = new Profile();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("EatAdvisor Profile");
		dialog.setVisible(true);
	}
}