package com.login;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class registration {
    private JButton registramiNelSistemaButton;
    private JPanel panel1;

    public static void main(String[] args) throws IOException {
        JFrame f = new JFrame("stackoverflow") {
            private Image backgroundImage = ImageIO.read(new File("background.jpg"));
            public void paint( Graphics g ) {
                super.paint(g);
                g.drawImage(backgroundImage, 0, 0, null);
            }
        };
    }
}
