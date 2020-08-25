package clienti.Login;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
		loginButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }

			public void mousePressed(MouseEvent e) { }

			public void mouseReleased(MouseEvent e) { }

			public void mouseEntered(MouseEvent e) { onEnabled(); }

			public void mouseExited(MouseEvent e) { }
		});
		loginButton.addActionListener(e -> {
			// Lambda has been expanded to interact with the database

			// Getting data from the form
			String nickname = this.nicknameField.getText();
			String password = String.valueOf(this.passwordField.getPassword());

			//
			onLogin();
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
			public void focusLost(FocusEvent e) {
				boolean validate = notValidateNick();
				if(!validate) {
					nicknameField.setBorder(new LineBorder(Color.getColor("BBBBBB"))); // colore sbagliato
					onEnabled();
				}
				else { nicknameField.setBorder(new LineBorder(Color.red)); }
			}
		});
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) {
				boolean validate = notValidatePass();
				if(!validate) {
					passwordField.setBorder(new LineBorder(Color.getColor("BBB")));
					onEnabled();
				}
				else { passwordField.setBorder(new LineBorder(Color.red));	}
			}
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

	private boolean notValidateNick() { return nicknameField.getText().matches("\\W+|\\d+"); }

	private boolean notValidatePass() { return String.valueOf(passwordField.getPassword()).matches("^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$"); }

/*  Example of REGEX
	Minimum eight characters, at least one letter and one number:
	"^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$"

	Minimum eight characters, at least one letter, one number and one special character:
	"^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"

	Minimum eight characters, at least one uppercase letter, one lowercase letter and one number:
	"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$"

	Minimum eight characters, at least one uppercase letter, one lowercase letter, one number and one special character:
	"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$"

	Minimum eight and maximum 10 characters, at least one uppercase letter, one lowercase letter, one number and one special character:
	"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,10}$"
*/
	public static void main(String[] args) {
		CustomerLogin dialog = new CustomerLogin();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}