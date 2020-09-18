package clienti.Login;

import _database.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class CustomerLogin extends JDialog {
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
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region loginButton events
		loginButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				// Getting data from the form
				String nickname = nicknameField.getText();
				String password = String.valueOf(passwordField.getPassword());

				//todo: Ask to db about this client
				try {
					if(Database.checkClient(nickname))
						JOptionPane.showMessageDialog(null, "Login successfully!");
					else
						JOptionPane.showMessageDialog(null, "Not found this client");

				} catch (IOException | ClassNotFoundException exception) { exception.printStackTrace(); }
				finally { dispose(); }
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { loginButton.setEnabled(allFieldValid()); }
			public void mouseExited(MouseEvent e) { }
		});
		//endregion

		//region Focus Events
		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		nicknameField.addCaretListener(e -> {
			Color color = Database.regexNickname(nicknameField.getText()) ? Color.green : Color.red;
			nicknameField.setBorder(new LineBorder(color));
		});

		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		passwordField.addCaretListener(e -> {
			Color color = Database.regexPassword(String.valueOf(passwordField.getPassword())) ? Color.green : Color.red;
			passwordField.setBorder(new LineBorder(color));
		});
		//endregion
	}

	private boolean allFieldValid() {
		Color[] colorArray = {
				((LineBorder) nicknameField.getBorder()).getLineColor(),
				((LineBorder) passwordField.getBorder()).getLineColor()
		};

		for(Color color : colorArray) if(color == Color.red) return false;
		return true;
	}

	public static void main() {
		CustomerLogin dialog = new CustomerLogin();
		dialog.pack();
		dialog.setVisible(true);
	}
}