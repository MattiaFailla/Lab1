package clienti.Registration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerRegistration {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JPasswordField passwordField1;
    private JButton registerButton;
    private JPanel panel1;

    public CustomerRegistration() {

        panel1.setSize(1200, 700);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {JOptionPane.showMessageDialog(null, "Registered Successfully!");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CustomerRegistration");
        frame.setContentPane(new CustomerRegistration().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}


