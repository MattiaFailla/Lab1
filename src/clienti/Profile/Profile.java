package clienti.Profile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
    private JTextField NicknameField;

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

        NicknameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { NicknameField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateNick();
                if(!validate) {
                    NicknameField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { NicknameField.setBorder(new LineBorder(Color.red)); }
            }
        });
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { passwordField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidatePass();
                if(!validate) { passwordField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { passwordField.setBorder(new LineBorder(Color.red)); }
            }
        });
        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { emailField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateEmail();
                if(!validate) { emailField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { emailField.setBorder(new LineBorder(Color.red)); }}
        });
        fullNameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { fullNameField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateFname();
                if(!validate) { fullNameField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { fullNameField.setBorder(new LineBorder(Color.red)); }}
        });

    }

    private boolean notValidateNick() { return NicknameField.getText().matches("\\W+|\\d+"); }

    private boolean notValidatePass() { return String.valueOf(passwordField.getPassword()).matches("^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$"); }

    private boolean notValidateEmail() { return emailField.getText().matches("\\W+|\\d+"); } // regex must be changed

    private boolean notValidateFname() { return NicknameField.getText().matches("\\W+|\\d+"); }


    public void WriteInfo() {
    }

    private void onCancel() {
        System.out.println("Closing app..");
        dispose();
    }

    private void onEnabled() {
        boolean checkName = NicknameField.getText().length() > 0;
        boolean checkFullName = fullNameField.getText().length() > 0;
        boolean checkEmail = emailField.getText().length() > 0;
        boolean checkPassword = String.valueOf(passwordField.getPassword()).length() > 0;
        boolean firstCouple = checkName && checkFullName;
        boolean secondCouple = checkEmail && checkPassword;
        saveButton.setEnabled(firstCouple && secondCouple);
    }

    public static void main(String[] args) {
        Profile dialog = new Profile();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}