package Clienti.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CustomerLogin extends JDialog {
    private JPanel contentPane;
    private JButton buttonLogin;
    private JButton buttonCancel;
    private JTextField nicknameField;
    private JPasswordField passwordField;

    public CustomerLogin() {
        setSize(1200,700);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonLogin);

        buttonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLogin();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onLogin() {
        // add your code here
        System.out.println("Login verification ongoing..");
        System.out.println(nicknameField.getText());
        System.out.println(passwordField.getPassword());
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        CustomerLogin dialog = new CustomerLogin();

        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}