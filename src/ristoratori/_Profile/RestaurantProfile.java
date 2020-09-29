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
	private JButton sendButton;
	public static boolean verifyClient = false;
	public static String customerName;
	public static Restaurant rst;

	public RestaurantProfile(Boolean isRestaurant) {
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

		if (isRestaurant) {
			starsComboBox.setVisible(false);
			judgmentField.setVisible(false);
			sendButton.setVisible(false);
		} else {
			sendButton.setEnabled(verifyClient);
		}
		//endregion

		//region Initializing judgmentList
		DefaultListModel<String> listModel = new DefaultListModel<>();
		judgmentList.setModel(listModel);
		printJudgment(listModel);
		//endregion

		printJudgment(listModel);

		//region sendButton events
		sendButton.addActionListener(e -> {
			Integer rating = this.starsComboBox.getSelectedIndex() + 1;
			String judgment = this.judgmentField.getText();
			if (judgment.length() > 256) {
				JOptionPane.showMessageDialog(null, "The message is too long!");
				return;
			} else if (judgment.length() <= 1) {
				JOptionPane.showMessageDialog(null, "The message is too short!");
				return;
			}

			try {
				Database.insertJudgment(customerName, nameLabel.getText(), rating, judgment);
			} catch (IOException | ClassNotFoundException ioException) {
				ioException.printStackTrace();
			}

			printJudgment(listModel);
		});
		//endregion
	}

	public static void main(Boolean isRestaurant) {
		RestaurantProfile dialog = new RestaurantProfile(isRestaurant);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Restaurant - Profile");
		dialog.setVisible(true);
	}

	private void printJudgment(DefaultListModel<String> listModel) {
		listModel.clear();
		List<Judgement> result;
		try {
			result = Database.getJudgement(rst.name);
			if (result.isEmpty()) JOptionPane.showMessageDialog(null, "No judgment found for this restaurant");
			else {
				for (Judgement jdg : result) {
					listModel.addElement(jdg.toString());
				}
			}
		} catch (IOException | DatabaseExceptions | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
