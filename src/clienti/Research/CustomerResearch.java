package clienti.Research;

import _database.Database;
import _database.objects.*;
import clienti.Registration.*;
import clienti.Login.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class CustomerResearch extends JDialog {
	private JPanel contentPane;
	private JTextField nameField;
	private JTextField cityField;
	private JComboBox<String> typologyBox;
	private JTextArea searchArea;
	private JButton researchButton;
	private JButton registrationButton;
	public static JButton loginButton;

	public CustomerResearch() {
		setContentPane(contentPane);
		setModal(true);

		//region setBorder to Color.red
		nameField.setBorder(new LineBorder(Color.red));
		cityField.setBorder(new LineBorder(Color.red));

		typologyBox.setSelectedIndex(-1);
		//endregion

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region researchButton events
		researchButton.addActionListener(e -> {
			String name = this.nameField.getText();
			String city = this.cityField.getText();
			String typeStr = String.valueOf(this.typologyBox.getSelectedItem());
			Restaurant.types type = null;
			if (typeStr != null) { type = Restaurant.types.valueOf(typeStr.toUpperCase()); }

			//Database.getRestaurantByCategory(type);

		});
		//endregion

		//region Switch windows
		registrationButton.addActionListener(e -> CustomerRegistration.main());
		loginButton.addActionListener(e -> CustomerLogin.main());
		//endregion

		//region Focus Events
		nameField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { nameField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		nameField.addCaretListener(e -> {
			Color color = Database.regexStandard(nameField.getText()) ? Color.green : Color.red;
			nameField.setBorder(new LineBorder(color));
		});

		cityField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) { cityField.selectAll(); }
			public void focusLost(FocusEvent e) { }
		});
		cityField.addCaretListener(e -> {
			Color color = Database.regexStandard(cityField.getText()) ? Color.green : Color.red;
			cityField.setBorder(new LineBorder(color));
		});
		//endregion
	}

	public static void main() {
		CustomerResearch dialog = new CustomerResearch();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Research");
		dialog.setVisible(true);
	}
}