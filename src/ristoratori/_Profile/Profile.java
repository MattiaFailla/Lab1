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
	private JLabel nicknameLabel;
	private JLabel passwordLabel;
	private JLabel emailLabel;
	private JLabel fullNameLabel;
	private JLabel cityLabel;
	private JLabel provinceLabel;
	private JButton restaurantButton;
	private JTable restaurantTable;
	public static Customer clt;

	public Profile() {
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
		nicknameLabel.setText(clt.nickname);
		passwordLabel.setText(String.valueOf(clt.password));
		emailLabel.setText(clt.email);
		fullNameLabel.setText(clt.name + " " + clt.surname);
		cityLabel.setText(clt.city);
		provinceLabel.setText(clt.province);
		//endregion

		//region Initializing searchTable
		String[] columnNames = {"Name", "City", "Typology"};
		DefaultTableModel tableModel = new DefaultTableModel(null, columnNames) {
			//all cells false
			public boolean isCellEditable(int row, int column) { return false; }
			private static final long serialVersionUID = 8785365349565461528L;
		};
		tableModel.addRow(columnNames);
		restaurantTable.setModel(tableModel);
		//endregion

		printRestaurants(tableModel);

		restaurantButton.addActionListener(e -> {
			RestaurantRegistration.owner = clt.nickname;
			RestaurantRegistration.main();
			printRestaurants(tableModel);
		});
		restaurantTable.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					try {
						int selectedRow = restaurantTable.getSelectedRow();
						if (selectedRow > 0) {
							String nameRestaurant = String.valueOf(tableModel.getValueAt(selectedRow, 0));
							RestaurantProfile.rst = Database.getRestaurant(nameRestaurant);
							RestaurantProfile.main();
						}
					} catch (IOException | ClassNotFoundException | DatabaseExceptions ioException) {
						ioException.printStackTrace();
					}
				}
			}
			public void mousePressed(MouseEvent e) { }
			public void mouseReleased(MouseEvent e) { }
			public void mouseEntered(MouseEvent e) { }
			public void mouseExited(MouseEvent e) { }
		});
	}

	private void printRestaurants(DefaultTableModel tableModel) {
		tableModel.setRowCount(1);
		List<Restaurant> result;
		try {
			result = Database.getRestaurantByOwner(clt.nickname);
			if (result.isEmpty()) JOptionPane.showMessageDialog(null, "You have not registered any restaurants yet");
			else {
				for(Restaurant rst : result) { tableModel.addRow(new Object[]{rst.name, rst.city, rst.type}); }
			}
		}
		catch (IOException | ClassNotFoundException ioException) { ioException.printStackTrace(); }
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