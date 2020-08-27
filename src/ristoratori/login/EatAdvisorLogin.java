package ristoratori.Login;

import database.Database;

import javax.swing.*;
import java.awt.event.*;

public class EatAdvisorLogin extends JDialog {
	private JPanel contentPane;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton cancelButton;
	private JButton loginButton;

	public EatAdvisorLogin() {
		setContentPane(contentPane);
		setModal(true);

		// region loginButton events
		loginButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		});
		loginButton.addActionListener(e -> {

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

		// region Focus events
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
		// endregion

		// region Input Validation
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
		// endregion
	}

	public static void main(String[] args) {
		EatAdvisorLogin dialog = new EatAdvisorLogin();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}