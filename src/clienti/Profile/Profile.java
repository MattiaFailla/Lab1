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

	public Profile() {
		setContentPane(contentPane);
		setModal(true);

		//region saveButton events
		saveButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		});
		saveButton.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Saved!");
		});
		//endregion

		// region cancelButton events
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE); // call onCancel() when cross is clicked
		cancelButton.addActionListener(e -> dispose());
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		// endregion

		// region Focus events
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		emailField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { emailField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		fullNameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { fullNameField.selectAll(); }
			public void focusLost(FocusEvent e) {

			}
		});
		// endregion

		//region Input Validation
		passwordField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { passwordField.setEditable(Database.regexPassword(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		emailField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { emailField.setEditable(Database.regexNickname(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		fullNameField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { fullNameField.setEditable(Database.regexStandard(e.getKeyChar())); }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) { }
		});
		//endregion
	}

	public static void main(String[] args) {
		Profile dialog = new Profile();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}