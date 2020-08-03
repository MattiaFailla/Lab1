package com.login;

import com.profile.Profile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Registration {
    private JPanel panel1;
    private JTextField marioRossiTextField;
    private JPasswordField passwordField1;
    private JButton registramiEInserisciInformazioniButton;

    public static void main(String[] args) throws IOException {
        // @fixme: This need to be fixed. The drawImage show some strange behaviour with the content of the frame
        
        JFrame frame = new JFrame("Registrazione Ristoratori") {
            private final Image backgroundImage = ImageIO.read(new File("/home/mat/Progetti/Lab1/assets/img/login_background.jpg"));
            public void paint( Graphics g ) {
                super.paint(g);
                // Setting the background with custom dimensions
                g.drawImage(backgroundImage, 0, 0, 860, 540, this);
            }
        };
        //JFrame frame = new JFrame();
        frame.setPreferredSize(new Dimension(860,540));
        frame.setContentPane(new Registration().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
