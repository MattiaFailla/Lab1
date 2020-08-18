package clienti.Login;

import javax.swing.*;
import java.awt.event.*;

public class CustomerLogin extends JDialog {
	private JPanel contentPane;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton cancelButton;
	private JButton loginButton;

	public CustomerLogin() {
		setContentPane(contentPane);
		setModal(true);

		// region loginButton events
		loginButton.addActionListener(e -> onLogin());
		loginButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }

			public void mousePressed(MouseEvent e) { }

			public void mouseReleased(MouseEvent e) { }

			public void mouseEntered(MouseEvent e) { onEnabled(); }

			public void mouseExited(MouseEvent e) { }
		});
		// endregion

		// region cancelButton events
		cancelButton.addActionListener(e -> onCancel());
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { onCancel(); }
		});
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// endregion

		// region Focus events
		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		// endregion
	}

	private void onLogin() {
		System.out.println("Login verification ongoing..");

		//@todo: Add code to connect the database

		loginButton.setText(".. verifying ..");
		dispose();
	}

	private void onCancel() {
		System.out.println("Closing app..");
		dispose();
	}

	private void onEnabled() {
		boolean checkNick = nicknameField.getText().length() > 0;
		boolean checkPass = String.valueOf(passwordField.getPassword()).length() > 0;
		loginButton.setEnabled(checkNick && checkPass);
	}

	public static void main(String[] args) {
		CustomerLogin dialog = new CustomerLogin();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}