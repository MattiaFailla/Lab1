package clienti.Profile;

import javax.swing.*;
import java.awt.event.*;

public class Profile extends JDialog {
    private JPanel contentPane;
    private JLabel nicknameLabel;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField fullNameField;
    private JComboBox restaurantComboBox;
    private JButton saveButton;
    private JButton cancelButton;

    public Profile() {
        setContentPane(contentPane);
        setModal(true);

        WriteInfo();

        saveButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Saved!");
        });

        // region cancelButton events
        cancelButton.addActionListener(e -> onCancel());
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { onCancel(); }
        });
        // endregion
    }

    public void WriteInfo() {
    }

    private void onCancel() {
        System.out.println("Closing app..");
        dispose();
    }

    public static void main(String[] args) {
        Profile dialog = new Profile();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}