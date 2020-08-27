package clienti.Registration;

import database.Database;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class CustomerRegistration extends JDialog{
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField cityField;
	private JTextField provinceField;
	private JTextField emailField;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton cancelButton;
	private JButton registerButton;

	public CustomerRegistration() {
		setContentPane(contentPane);
		setModal(true);

		// region registerButton events
		registerButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { onEnabled(); }
			public void mouseExited(MouseEvent e) { }
		});
		registerButton.addActionListener(e -> {
			// Lambda has been expanded to interact with the database

			// Getting data from the form
			String name = this.nameField.getText();
			String surname = this.surnameField.getText();
			String city = this.cityField.getText();
			String province = this.provinceField.getText();
			String email = this.emailField.getText();
			String nickname = this.nicknameField.getText();
			String password = String.valueOf(this.passwordField.getPassword());
			// Saving the username in the database
			Database.insertClient(name, surname, city, province, email, nickname, password);

			JOptionPane.showMessageDialog(null, "Registered Successfully!");
		});
		// endregion

		// region cancelButton events
		cancelButton.addActionListener(e -> onCancel());
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { onCancel(); }
		});
		// endregion

		//region Focus events
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nameField.selectAll(); }
			public void focusLost(FocusEvent e) {
				Color color = nameField.getText().length() > 3 ? Color.green : Color.red;
				nameField.setBorder(new LineBorder(color));
			}
		});
		surnameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { surnameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { cityField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
			});
        provinceField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) { provinceField.selectAll(); }
	        public void focusLost(FocusEvent e) { onEnabled(); }
        });
        emailField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) { emailField.selectAll(); }
				public void focusLost(FocusEvent e) { onEnabled(); }
        });
        nicknameField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
				public void focusLost(FocusEvent e) { onEnabled(); }
        });
        passwordField.addFocusListener(new FocusListener() {
				public void focusGained(FocusEvent e) { passwordField.selectAll(); }
				public void focusLost(FocusEvent e) { onEnabled(); }
        });
        //endregion

		// region Input Validation
		nameField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) {
				if(Database.insertInput(e)) nameField.setText(nameField.getText());
				else nameField.setText(Database.deleteLastInput(nameField.getText()));
			}
		});
		surnameField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) {
				if(Database.insertInput(e)) surnameField.setText(surnameField.getText());
				else surnameField.setText(Database.deleteLastInput(surnameField.getText()));
			}
		});
		cityField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) {
				if(Database.insertInput(e)) cityField.setText(cityField.getText());
				else cityField.setText(Database.deleteLastInput(cityField.getText()));
			}
		});
		provinceField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) { }
			public void keyPressed(KeyEvent e) { }
			public void keyReleased(KeyEvent e) {
				if(Database.insertInput(e) && provinceField.getText().matches("^.{0,2}$")) provinceField.setText(provinceField.getText());
				else provinceField.setText(Database.deleteLastInput(provinceField.getText()));
			}
		});
		// endregion
		}

		private void onCancel() {
			System.out.println("Closing app..");
			dispose();
		}

		private void onEnabled() {
			boolean checkName = nameField.getText().length() > 3;
			boolean checkSurname = surnameField.getText().length() > 3;
			boolean checkCity = cityField.getText().length() > 3;
			boolean checkProvince = provinceField.getText().length() > 1;
			boolean checkEmail = emailField.getText().length() > 5;
			boolean checkNickname = nicknameField.getText().length() > 3;
			boolean checkPassword = String.valueOf(passwordField.getPassword()).length() > 4;
			boolean firstCouple = checkName && checkSurname;
			boolean secondCouple = checkCity && checkProvince;
			boolean thirdCouple = checkNickname && checkPassword;
			registerButton.setEnabled(firstCouple && secondCouple && checkEmail && thirdCouple);
		}

		public static void main(String[] args) {
			CustomerRegistration dialog = new CustomerRegistration();
			dialog.pack();
			dialog.setVisible(true);
			System.exit(0);
		}
	}