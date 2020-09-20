package ristoratori._Profile;

import _database.objects.Client;

import javax.swing.*;
import java.awt.event.*;

public class Profile extends JDialog {
	private JPanel contentPane;
	private JLabel nicknameLabel;
	private JLabel passwordLabel;
	private JLabel emailLabel;
	private JLabel fullNameLabel;
	private JLabel cityLabel;
	private JLabel provinceLabel;
	private JComboBox<String> restaurantComboBox;
	public static Client clt;

	public Profile() {
		setContentPane(contentPane);
		setModal(true);

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) { dispose(); }
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region Information's client
		nicknameLabel.setText(clt.nickname);
		passwordLabel.setText(String.valueOf(clt.password));
		emailLabel.setText(clt.email);
		fullNameLabel.setText(clt.name + " " + clt.surname);
		cityLabel.setText(clt.city);
		provinceLabel.setText(clt.province);
		//todo: ottenere ristorante da nickname
		//restaurantComboBox.addItem(Database.getRestaurant("nome proprietario"));
		//endregion
	}

	public static void main() {
		Profile dialog = new Profile();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("EatAdvisor Profile");
		dialog.setVisible(true);
	}
}