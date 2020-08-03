package com.login;

import com.profile.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Registration {
    private JButton registramiNelSistemaButton;
    private JPanel panel1;

    public static void main(String[] args) throws IOException {
        JFrame frame = new JFrame("Registrati") {
            private Image backgroundImage = ImageIO.read(new File("/home/mat/Progetti/Lab1/assets/img/login_background.jpg"));
            public void paint( Graphics g ) {
                super.paint(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
        frame.setSize(600,800);
        frame.setContentPane(new Registration().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
