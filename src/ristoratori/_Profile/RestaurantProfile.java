package ristoratori._Profile;

import _database.Database;
import _database.DatabaseExceptions;
import _database.objects.Judgement;
import _database.objects.Restaurant;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

public class RestaurantProfile extends JDialog {
	private JPanel contentPane;
	private JLabel restaurantLabel;
	private JTextArea judgmentArea;
	private JComboBox starsComboBox;
	private JTextField judgmentField;
	public static Restaurant rst;
	public static boolean verifyClient;

	public RestaurantProfile() {
		restaurantLabel.setText("Connection");
		setContentPane(contentPane);
		setModal(true);

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		try {
			List<Judgement> list = Database.getJudgement(restaurantLabel.getText());
			judgmentArea.append(list.toString());
		}
		catch (IOException | ClassNotFoundException e) { e.printStackTrace(); }
		catch (DatabaseExceptions databaseExceptions) {
			JOptionPane.showMessageDialog(null, "Restaurant not found");
		}

		judgmentField.setEditable(verifyClient);
		starsComboBox.setEnabled(verifyClient);
	}

	public static void main() {
		RestaurantProfile dialog = new RestaurantProfile();
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Restaurant - Profile");
		dialog.setVisible(true);
	}
}
