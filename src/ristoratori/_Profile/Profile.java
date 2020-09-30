package ristoratori._Profile;

import _database.Database;
import _database.DatabaseExceptions;
import _database.objects.Customer;
import _database.objects.Restaurant;
import ristoratori.Registration.RestaurantRegistration;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;

public class Profile extends JDialog {
	private JPanel contentPane;
	private JLabel welcomeLabel;
	private JTable restaurantTable;
	private JButton restaurantButton;

	public Profile(Customer eatAdvisor, boolean isEatAdvisor) {
		setContentPane(contentPane);
		setModal(true);

		//region closing app events
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		contentPane.registerKeyboardAction(e -> dispose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT); // call onCancel() on ESCAPE
		//endregion

		//region Information's client
		welcomeLabel.setText(eatAdvisor.nickname);
		//endregion

		//region Initializing searchTable
		DefaultTableModel tableModel = new DefaultTableModel(0, 3) {
			private static final long serialVersionUID = 4884297043138956782L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableModel.addRow(new String[]{"Name", "City", "Typology"});
		restaurantTable.setModel(tableModel);
		printRestaurants(tableModel, eatAdvisor);
		//endregion

		restaurantButton.addActionListener(e -> {
			RestaurantRegistration.main(eatAdvisor.nickname);
			printRestaurants(tableModel, eatAdvisor);
		});
		restaurantTable.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					try {
						int selectedRow = restaurantTable.getSelectedRow();
						if (selectedRow > 0) {
							String nameRestaurant = String.valueOf(tableModel.getValueAt(selectedRow, 0));
							Restaurant restaurant = Database.getRestaurant(nameRestaurant);
							RestaurantProfile.main(restaurant, isEatAdvisor);
						}
					} catch (IOException | ClassNotFoundException ioException) {
						ioException.printStackTrace();
					} catch (DatabaseExceptions dbException) {
						JOptionPane.showMessageDialog(null, "Restaurant not found.");
					}
				}
			}

			public void mousePressed(MouseEvent e) {

			}

			public void mouseReleased(MouseEvent e) {

			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}
		});
	}

	public static void main(Customer eatAdvisor, boolean isEatAdvisor) {
		Profile dialog = new Profile(eatAdvisor, isEatAdvisor);
		dialog.pack();
		dialog.setResizable(false);
		dialog.setLocationRelativeTo(null);
		dialog.setTitle("EatAdvisor Profile");
		dialog.setVisible(true);
	}

	private void printRestaurants(DefaultTableModel tableModel, Customer eatAdvisor) {
		tableModel.setRowCount(1);
		List<Restaurant> result;
		try {
			result = Database.getRestaurantByOwner(eatAdvisor.nickname);
			if (result.isEmpty()) JOptionPane.showMessageDialog(null, "You have not registered any restaurants yet");
			else for (Restaurant rst : result) tableModel.addRow(new Object[]{rst.name, rst.city, rst.type});
		} catch (IOException | ClassNotFoundException ioException) {
			ioException.printStackTrace();
		}
	}
}