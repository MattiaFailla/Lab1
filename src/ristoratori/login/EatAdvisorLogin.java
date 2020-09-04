package ristoratori.Login;

import _database.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
			public void mouseEntered(MouseEvent e) { loginButton.setEnabled(allFieldValid()); }
			public void mouseExited(MouseEvent e) { }
		});
		loginButton.addActionListener(e -> {
			// Lambda has been expanded to interact with the database

			// Getting data from the form
			String nickname = this.nicknameField.getText();
			String password = String.valueOf(this.passwordField.getPassword());

			// @todo: Ask to db about this client

			JOptionPane.showMessageDialog(null, "Registered successfully!");
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

		//region Focus Events
		nicknameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
			public void focusLost(FocusEvent e) {}
		});
		nicknameField.addCaretListener(e->{
			Color color = Database.regexStandard(nicknameField.getText()) ? Color.green : Color.red;
			nicknameField.setBorder(new LineBorder(color));
		});

		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { passwordField.selectAll(); }
			public void focusLost(FocusEvent e) {}
		});
		passwordField.addCaretListener(e->{
			Color color = Database.regexPassword(String.valueOf(passwordField.getPassword())) ? Color.green : Color.red;
			passwordField.setBorder(new LineBorder(color));
		});
		//endregion
	}

	private boolean allFieldValid() {
		Color colorNick = ((LineBorder)nicknameField.getBorder()).getLineColor();
		Color colorPass = ((LineBorder)passwordField.getBorder()).getLineColor();
		return colorNick == Color.green && colorPass == Color.green;
	}

	public static void main() {
		EatAdvisorLogin dialog = new EatAdvisorLogin();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}