package clienti.Research;

import _database.objects.*;
import clienti.Registration.*;
import clienti.Login.*;


import javax.swing.*;
import java.awt.event.*;

public class CustomerResearch extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField cityField;
	private JComboBox typologyBox;
	private JTextArea searchArea;
	private JButton researchButton;
	private JButton registrationButton;
	private JButton loginButton;

	public CustomerResearch() {
		setContentPane(contentPane);
		setModal(true);

		//region switch windows
		registrationButton.addActionListener(e -> CustomerRegistration.main());
		loginButton.addActionListener(e -> CustomerLogin.main());
		//endregion

		researchButton.addActionListener(e -> {
			String name = nameField.getText();
			String city = cityField.getText();
			Restaurant.types types = (Restaurant.types) this.typologyBox.getSelectedItem();

			//todo: request to database
			searchArea.append("ristoranti");
		});

		// region cancelButton events
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		// endregion
	}

	public static void main() {
		CustomerResearch dialog = new CustomerResearch();
		dialog.pack();
		dialog.setVisible(true);
		System.exit(0);
	}
}
