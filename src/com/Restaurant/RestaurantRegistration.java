package com.Restaurant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RestaurantRegistration {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton registerButton;
    private JPanel panel1;
    private JComboBox<String> comboBox1;

    public RestaurantRegistration() {
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Registered Successfully!");
            }
        });

        textField2.setText("Street, House Number, City, Province Abbreviation, ZIP Code");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("RestaurantRegistration");
        frame.setContentPane(new RestaurantRegistration().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(400,300);
        frame.setResizable(true);

        /*
        //adding address menu
        JMenuBar myMenuBar = new JMenuBar();
        frame.setJMenuBar(myMenuBar);
        JMenu f = new JMenu("Address");
        JMenuItem e1 = new JMenuItem("Street");
        JMenuItem e2 = new JMenuItem("House Number");
        JMenuItem e3 = new JMenuItem("City");
        JMenuItem e4 = new JMenuItem("Province Abbreviation");
        JMenuItem e5 = new JMenuItem("ZIP Code");
        f.add(e1);
        f.add(e2);
        f.add(e3);
        f.add(e4);
        f.add(e5);
        myMenuBar.add(f);

        //Typology Menu
        JMenu d = new JMenu("Typology");
        JMenuItem d1 = new JMenuItem("Italian");
        JMenuItem d2 = new JMenuItem("Ethnic");
        JMenuItem d3 = new JMenuItem("Fusion");
        d.add(d1);
        d.add(d2);
        d.add(d3);
        myMenuBar.add(d);

         */
    }
}

