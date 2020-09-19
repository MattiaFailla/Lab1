package ristoratori._Profile;

import _database.Database;
import _database.objects.Client;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile extends JDialog {
	private JPanel contentPane;
	private JLabel nicknameLabel;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField fullNameField;
	private JComboBox restaurantComboBox;
	private JButton saveButton;
	public static Client clt;

	public Profile() {
		nicknameLabel.setText(clt.nickname);
		setContentPane(contentPane);
		setModal(true);

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region saveButton events
		saveButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		});
		saveButton.addActionListener(e -> {
			// Getting data from the form
			String password = String.valueOf(passwordField.getPassword());
			String email = emailField.getText();
			String fullname = fullNameField.getText();

			//regex to split fullname in name and surname
			Pattern pattern = Pattern.compile("^([a-zA-Z]+)\\s(['a-zA-Z]+)$");
			Matcher matcher = pattern.matcher(fullname);
			String name = matcher.group(1);
			String surname = matcher.group(2);

			// Saving the new eat advisor's info in the database
			Database.insertClient(name, surname, clt.city, clt.province, email, clt.nickname, password);
			JOptionPane.showMessageDialog(null, "Saved!");
		});
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