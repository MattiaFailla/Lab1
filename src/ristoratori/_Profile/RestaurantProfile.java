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
import java.util.StringTokenizer;

public class RestaurantProfile extends JDialog {
	private JPanel contentPane;
	//private JLabel ownerLabel
	private JLabel nameLabel;
	private JLabel typeLabel;
	private JLabel websiteLabel;
	private JLabel fullAddressLabel;
	private JScrollPane judgeScroll;
	private JList<String> judgmentList;
	private JComboBox<Byte> starsComboBox;
	private JTextField judgmentField;
	private JButton sendButton;

	public RestaurantProfile(Restaurant restaurant, boolean isEatAdvisor, String customerName) {
		//JScrollPane scrollPane = new JScrollPane(outputTextArea);
		judgeScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

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
		//ownerLabel.setText("Welcome " + rst.owner);
		nameLabel.setText(restaurant.name);
		websiteLabel.setText(restaurant.url);
		typeLabel.setText(restaurant.type.toString());
		fullAddressLabel.setText(restaurant.fullAddress);

		sendButton.setEnabled(!customerName.isEmpty());

		starsComboBox.setVisible(!isEatAdvisor);
		judgmentField.setVisible(!isEatAdvisor);
		sendButton.setVisible(!isEatAdvisor);
		//endregion

		//@todo: add scrollbar
		//region Initializing judgmentList
		DefaultListModel<String> listModel = new DefaultListModel<>();
		judgmentList.setModel(listModel);
		printJudgment(listModel, restaurant);
		//endregion

		//region sendButton events
		sendButton.registerKeyboardAction(e -> send(listModel, restaurant, customerName), KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
		sendButton.addActionListener(e -> send(listModel, restaurant, customerName));
		//endregion
	}

	public static void main(Restaurant restaurant, boolean isEatAdvisor, String customerName) {
		RestaurantProfile dialog = new RestaurantProfile(restaurant, isEatAdvisor, customerName);

		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("Restaurant - Profile");
		dialog.setVisible(true);
	}

	public String addLinebreaks(String raw_input, int maxLineLength) {
		String input = "<html>" + raw_input + "</html>";
		StringTokenizer tok = new StringTokenizer(input, " ");
		StringBuilder output = new StringBuilder(input.length());
		int lineLen = 0;
		while (tok.hasMoreTokens()) {
			String word = tok.nextToken() + " ";

			if (lineLen + word.length() > maxLineLength) {
				output.append("<br>");
				lineLen = 0;
			}
			output.append(word);
			lineLen += word.length();
		}
		return output.toString();
	}

	private void send(DefaultListModel<String> listModel, Restaurant restaurant, String customerName) {
		Integer rating = this.starsComboBox.getSelectedIndex() + 1;
		String judgment = this.judgmentField.getText();
		int jdgLength = judgment.length();
		if (jdgLength < 3 || jdgLength > 256) {
			String isToo = jdgLength < 3 ? "short" : "long";
			JOptionPane.showMessageDialog(null, "The message is too " + isToo + "!");
			return;
		}

		try {
			Database.insertJudgment(customerName, nameLabel.getText(), rating, judgment);
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		}

		printJudgment(listModel, restaurant);
	}

	private void printJudgment(DefaultListModel<String> listModel, Restaurant restaurant) {
		listModel.clear();
		List<Judgement> result;
		try {
			result = Database.getJudgement(restaurant.name);
			if (result.isEmpty())
				JOptionPane.showMessageDialog(null, "No judgments found for this restaurant, be the first one!");
			else for (Judgement jdg : result) {
				listModel.addElement(
						addLinebreaks(jdg.toString(), 100)
				);
			}
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		} catch (DatabaseExceptions dbException) {
			System.out.println("Exception database, no judgement! Continue.");
		}
	}
}
