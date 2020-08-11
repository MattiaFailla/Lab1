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
		//getRootPane().setDefaultButton(loginButton);

		loginButton.addActionListener(e -> onLogin());
		loginButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }

			public void mousePressed(MouseEvent e) { }

			public void mouseReleased(MouseEvent e) { }

			public void mouseEntered(MouseEvent e) { onEnabled(); }

			public void mouseExited(MouseEvent e) { }
		});

		cancelButton.addActionListener(e -> onCancel());
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { onCancel(); }
		});

		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});

		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
	}

	private void onLogin() {
		// add your code here if necessary
		System.out.println("Login verification ongoing..");
		loginButton.setText(".. verifying ..");
		System.out.println(nicknameField.getText());
		System.out.println(passwordField.getPassword());
		dispose();
	}

	private void onCancel() {
		// add your code here if necessary
		System.out.println("Closing app..");
		dispose();
	}

	private void onEnabled() {
		// add your code here if necessary
		boolean checkNick = nicknameField.getText().length() > 0;
		boolean checkPass = passwordField.getText().length() > 0;
		loginButton.setEnabled(checkNick && checkPass);
	}

	public static void main(String[] args) {
		CustomerLogin dialog = new CustomerLogin();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}