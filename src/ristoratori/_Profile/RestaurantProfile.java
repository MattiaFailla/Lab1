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
	private JLabel ownerLabel;
	private JLabel nameLabel;
	private JLabel fullAddressLabel;
	private JLabel phoneNumberLabel;
	private JLabel websiteLabel;
	private JLabel typeLabel;
	private JList<String> judgmentList;
	private JComboBox<Byte> starsComboBox;
	private JTextField judgmentField;
	public static Restaurant rst;
	public static boolean verifyClient = false;

	public RestaurantProfile() {
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

		//region Information's restaurants
		ownerLabel.setText(rst.owner);
		nameLabel.setText(rst.name);
		fullAddressLabel.setText(rst.fullAddress);
		phoneNumberLabel.setText(String.valueOf(rst.phoneNumber));
		websiteLabel.setText(rst.url);
		typeLabel.setText(rst.type.toString());

		judgmentField.setEditable(verifyClient);
		starsComboBox.setEnabled(verifyClient);
		//endregion

		//region Initializing judgmentList
		DefaultListModel<String> listModel = new DefaultListModel<>();
		judgmentList.setModel(listModel);
		printJudgment(listModel);
		//endregion

	}

	private void printJudgment(DefaultListModel<String> listModel) {
		List<Judgement> result;
		try {
			result = Database.getJudgement(rst.name);
			if (result.isEmpty()) JOptionPane.showMessageDialog(null, "No judgment found for this restaurant");
			else {
				for (Judgement jdg : result) {
					listModel.addElement(jdg.judgement);
				}
			}
		} catch (IOException | DatabaseExceptions e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "No judgment found for this restaurant");
		}
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
