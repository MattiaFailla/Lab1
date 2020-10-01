package clienti.Login;

import _database.Database;
import _database.DatabaseExceptions;
import _database.objects.Customer;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CustomerLogin extends JDialog {
	private String customerName = "";
	private JPanel contentPane;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton loginButton;

	public CustomerLogin() {
		setContentPane(contentPane);
		setModal(true);

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		//endregion

		//region setBorder to Color.red
		nicknameField.setBorder(new LineBorder(Color.red));
		passwordField.setBorder(new LineBorder(Color.red));
		//endregion

		//region loginButton events
		loginButton.registerKeyboardAction(e -> login(), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		loginButton.addActionListener(e -> login());
		//endregion

		//region Focus events
		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				nicknameField.selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
		});
		nicknameField.addCaretListener(e -> {
			Color color = Database.regexNickname(nicknameField.getText()) ? Color.green : Color.red;
			nicknameField.setBorder(new LineBorder(color));
		});

		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				passwordField.selectAll();
			}

			public void focusLost(FocusEvent e) {
			}
		});
		passwordField.addCaretListener(e -> {
			Color color = Database.regexPassword(String.valueOf(passwordField.getPassword())) ? Color.green : Color.red;
			passwordField.setBorder(new LineBorder(color));
		});
		//endregion
	}

	public static String main() {
		CustomerLogin dialog = new CustomerLogin();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Customer - Login");
		dialog.setVisible(true);
		return dialog.customerName;
	}

	private void login() {
		if (!allFieldValid()) {
			JOptionPane.showMessageDialog(null, "Field not valid!");
			return;
		}

		// Getting data from the form
		String nickname = this.nicknameField.getText();
		String password = String.valueOf(this.passwordField.getPassword());

		try {
			Customer customer = Database.getCustomer(nickname);
			if (customer.nickname.equals(nickname) && customer.password.equals(password)) {
				this.customerName = nickname;
				JOptionPane.showMessageDialog(null, "Login successful");
				dispose();
			} else JOptionPane.showMessageDialog(null, "Customer not found!");
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		} catch (DatabaseExceptions dbExceptions) {
			JOptionPane.showMessageDialog(null, "Customer not found!");
		}
	}

	private boolean allFieldValid() {
		Color[] colorArray = {
				((LineBorder) nicknameField.getBorder()).getLineColor(),
				((LineBorder) passwordField.getBorder()).getLineColor()
		};

		for (Color color : colorArray) if (color == Color.red) return false;
		return true;
	}
}