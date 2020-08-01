package com.registrazione;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private JButton loginButton;
    private JPanel panel1;
    private JButton registrazioneButton;
    private JPasswordField passwordField1;
    private JTextField textField1;

    public App() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Login phase
                JOptionPane.showMessageDialog(null, "Bottone click login");
            }
        });
        registrazioneButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // Subscription page
                JOptionPane.showMessageDialog(null, "Apertura pagina di registrazione.");
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        panel1 = new JPanel();
        loginButton = new JButton();
    }


    public static void main(String[] args) {

        JFrame frame;
        frame = new JFrame("Appliczione");

        frame.setSize(400, 600);

        Container app = new App().panel1;
        frame.setContentPane(app);


        //frame.setContentPane(new App().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);

    }

}
