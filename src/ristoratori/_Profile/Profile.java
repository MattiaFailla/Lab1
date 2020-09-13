package ristoratori._Profile;

import _database.Database;

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
	private JComboBox restaurantComboBox;
	private JButton saveButton;

	public Profile() {
		setContentPane(contentPane);
		setModal(true);

		// region cancelButton events
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		//contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		// endregion

		//region saveButton events
		saveButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// Getting data from the form
				String password = String.valueOf(passwordField.getPassword());
				String email = emailField.getText();

				JOptionPane.showMessageDialog(null, "Saved!");
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
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

	//todo: plus validate
	private boolean allFieldValid() {
		Color[] colorArray = {
				((LineBorder)passwordField.getBorder()).getLineColor()
		};

		for(Color color : colorArray) if(color == Color.red) return false;
		return true;
	}

	public static void main() {
		Profile dialog = new Profile();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}