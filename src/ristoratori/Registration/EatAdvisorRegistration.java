package ristoratori.Registration;

import javax.swing.*;
import java.awt.event.*;

public class EatAdvisorRegistration extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField municipalityField;
	private JTextField provinceField;
	private JTextField emailField;
	private JTextField nicknameField;
	private JPasswordField passwordField;
	private JButton registerButton;
	private JButton buttonCancel;

	public EatAdvisorRegistration() {
		setContentPane(contentPane);
		setModal(true);
		//getRootPane().setDefaultButton(buttonLogin);

		registerButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Registered Successfully!"));
		registerButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) { }

			public void mousePressed(MouseEvent e) { }

			public void mouseReleased(MouseEvent e) { }

			public void mouseEntered(MouseEvent e) { onEnabled(); }

			public void mouseExited(MouseEvent e) { }
		});

		buttonCancel.addActionListener(e -> onCancel());
		// call onCancel() on ESCAPE
		contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { onCancel(); }
		});

		//region FOCUS
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		surnameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { surnameField.selectAll(); }
			public void focusLost(FocusEvent e) { onEnabled(); }
		});
		municipalityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { municipalityField.selectAll(); }
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
	}

	private void onCancel() {
		// add your code here if necessary
		System.out.println("Closing app..");
		dispose();
	}

	private void onEnabled() {
		// add your code here if necessary
		boolean checkName = nameField.getText().length() > 0;
		boolean checkSurname = surnameField.getText().length() > 0;
		boolean checkMunicipality = municipalityField.getText().length() > 0;
		boolean checkProvince = provinceField.getText().length() > 0;
		boolean checkEmail = emailField.getText().length() > 0;
		boolean checkNickname = nicknameField.getText().length() > 0;
		boolean checkPassword = passwordField.getText().length() > 0;
		boolean firstCouple = checkName && checkSurname;
		boolean secondCouple = checkMunicipality && checkProvince;
		boolean thirdCouple = checkNickname && checkPassword;
		registerButton.setEnabled(firstCouple && secondCouple && checkEmail && thirdCouple);
	}

	public static void main(String[] args) {
		EatAdvisorRegistration dialog = new EatAdvisorRegistration();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}

/*
		import javax.imageio.ImageIO;
		import javax.swing.*;
		import java.awt.*;
		import java.io.File;
		import java.io.IOException;

public class Registration {
	private JPanel panel1;
	private JTextField marioRossiTextField;
	private JPasswordField passwordField1;
	private JButton registramiEInserisciInformazioniButton;

	public static void main(String[] args) throws IOException {
		// @fixme: This need to be fixed. The drawImage show some strange behaviour with the content of the frame
		JFrame frame = new JFrame("Registrazione Ristoratori") {
			private final Image backgroundImage = ImageIO.read(new File("/home/mat/Progetti/Lab1/assets/img/login_background.jpg"));

			public void paint(Graphics g) {
				super.paint(g);
				// Setting the background with custom dimensions
				g.drawImage(backgroundImage, 0, 0, 860, 540, this);
			}
}*/
