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
	private JComboBox typologyBox;
	private JTextArea searchArea;
	private JButton researchButton;
	private JButton registrationButton;
	private JButton loginButton;

	public CustomerResearch() {
		setContentPane(contentPane);
		setModal(true);

		//region cancelButton events
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		//contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region switch windows
		registrationButton.addActionListener(e -> {
			addComponentListener(new ComponentAdapter() {
				@Override
				public void componentHidden(ComponentEvent e) {
					super.componentHidden(e);
				}
			});
			CustomerRegistration.main();
		});
		loginButton.addActionListener(e -> CustomerLogin.main());
		//endregion

		researchButton.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				String name = nameField.getText();
				String city = cityField.getText();
				Restaurant.types types = (Restaurant.types) typologyBox.getSelectedItem();

				//todo: request to database

				//for(Restaurant rst : Database.getRestaurantByName(name)) {

				//}
				searchArea.append("ristoranti");
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		});

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
		dialog.setVisible(true);
		System.exit(0);
	}
}