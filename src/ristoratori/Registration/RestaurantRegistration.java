package ristoratori.Registration;

import javax.swing.*;
import java.awt.event.*;

public class RestaurantRegistration extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField websiteField;
	private JTextField phoneField;
	private JTextField cityField;
	private JTextField provinceField;
	private JTextField placeField;
	private JTextField capField;
	private JComboBox tipologyBox;
	private JButton cancelButton;
	private JButton registerButton;

	public RestaurantRegistration() {
		setContentPane(contentPane);
		setModal(true);
		//getRootPane().setDefaultButton();

		registerButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Registered Successfully!"));
		registerButton.addMouseListener(new MouseListener() {
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
	}

	private void onCancel() {
		// add your code here if necessary
		System.out.println("Closing app..");
		dispose();
	}

	private void onEnabled() {
		// add your code here if necessary
		boolean checkName = nameField.getText().length() > 0;
		boolean checkWebsite = websiteField.getText().length() > 0;
		boolean checkPhone = phoneField.getText().length() > 0;
		boolean checkCity = cityField.getText().length() > 0;
		boolean checkProvince = provinceField.getText().length() > 0;
		boolean checkPlace = placeField.getText().length() > 0;
		boolean checkCap = capField.getText().length() > 0;
		boolean firstCouple = checkName && checkPhone;
		boolean secondCouple = checkCity && checkCap;
		boolean thirdCouple = checkProvince && checkPlace;
		registerButton.setEnabled(firstCouple && secondCouple && checkWebsite && thirdCouple);
	}

	public static void main() {
		RestaurantRegistration dialog = new RestaurantRegistration();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
