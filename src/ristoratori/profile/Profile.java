package ristoratori.Profile;

import database.Database;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Profile extends JDialog {
    private JPanel contentPane;
    private JLabel nicknameLabel;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField fullNameField;
    private JComboBox restaurantComboBox;
    private JButton saveButton;

    public Profile() {
        setContentPane(contentPane);
        setModal(true);

        WriteInfo();

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saved!");
            }
        });
    }

    private void WriteInfo() {
        Connection connect;
        try {
            // create a connection to the database
            String url = "address";
            connect = DriverManager.getConnection(url);

            System.out.println("Connection to database has been established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Profile");
        frame.setContentPane(new Profile().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
