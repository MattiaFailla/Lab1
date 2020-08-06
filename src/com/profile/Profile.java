package com.profile;

import javax.swing.*;
import javax.swing.plaf.IconUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Profile {
    private JPanel panel2;
    private JTextField textField1;
    private JTextField textField2;
    private JPasswordField passwordField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField fullNameTextField;
    private JTextField userFirstNameTextField;
    private JButton saveButton;

    public Profile() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Saved!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Profile");
        frame.setContentPane(new Profile().panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
