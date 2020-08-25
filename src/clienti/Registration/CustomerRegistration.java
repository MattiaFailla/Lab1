package clienti.Registration;

import database.Database;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class CustomerRegistration extends JDialog{
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField cityField;
    private JTextField provinceField;
    private JTextField emailField;
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton cancelButton;
    private JButton registerButton;

    public CustomerRegistration() {
        setContentPane(contentPane);
        setModal(true);

        // region registerButton events
        registerButton.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) { }

            public void mousePressed(MouseEvent e) { }

            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) { onEnabled(); }

            public void mouseExited(MouseEvent e) { }
        });
        registerButton.addActionListener(e -> {
            // Lambda has been expanded to interact with the database

            // Getting data from the form
            String name = this.nameField.getText();
            String surname = this.surnameField.getText();
            String city = this.cityField.getText();
            String province = this.provinceField.getText();
            String email = this.emailField.getText();
            String nickname = this.nicknameField.getText();
            String password = String.valueOf(this.passwordField.getPassword());
            // Saving the username in the database
            Database.insertClient(name, surname, city, province, email, nickname, password);

            JOptionPane.showMessageDialog(null, "Registered Successfully!");
        });
        // endregion

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

        //region Focus events
        nameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { nameField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateName();
                if(!validate) {
                    nameField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { nameField.setBorder(new LineBorder(Color.red)); }
            }
        });
        surnameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { surnameField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateSurname();
                if(!validate) { surnameField.setBorder(new LineBorder(Color.green));
                onEnabled();
                }
                else { surnameField.setBorder(new LineBorder(Color.red)); }
            }
        });
        cityField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { cityField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateCity();
                if(!validate) { cityField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { cityField.setBorder(new LineBorder(Color.red)); }}
        });
        provinceField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { provinceField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateProvince();
                if(!validate) { provinceField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { provinceField.setBorder(new LineBorder(Color.red)); }}
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
        nicknameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
            public void focusLost(FocusEvent e) {
                boolean validate = notValidateNick();
                if(!validate) { nicknameField.setBorder(new LineBorder(Color.green));
                    onEnabled();
                }
                else { nicknameField.setBorder(new LineBorder(Color.red)); }}
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
        //endregion

        // region Regex
        
        // endregion
    }

    private void onCancel() {
        System.out.println("Closing app..");
        dispose();
    }

    private boolean notValidateName() { return nameField.getText().matches("\\W+|\\d+"); }

    private boolean notValidateSurname() { return surnameField.getText().matches("\\W+|\\d+"); }

    private boolean notValidateCity() {return cityField.getText().matches("\\W+|\\d+"); }

    private boolean notValidateProvince() { return provinceField.getText().matches("\\W+|\\d+"); } // regex must be changed

    private boolean notValidateEmail() { return emailField.getText().matches("\\W+|\\d+"); } // regex must be changed

    private boolean notValidateNick() { return nicknameField.getText().matches("\\W+|\\d+"); }

    private boolean notValidatePass() { return String.valueOf(passwordField.getPassword()).matches("^(.{0,7}|[^0-9]*|[^A-Z]*|[^a-z]*|[a-zA-Z0-9]*)$"); }


    private void onEnabled() {
        boolean checkName = nameField.getText().length() > 0;
        boolean checkSurname = surnameField.getText().length() > 0;
        boolean checkCity = cityField.getText().length() > 0;
        boolean checkProvince = provinceField.getText().length() > 0;
        boolean checkEmail = emailField.getText().length() > 0;
        boolean checkNickname = nicknameField.getText().length() > 0;
        boolean checkPassword = String.valueOf(passwordField.getPassword()).length() > 0;
        boolean firstCouple = checkName && checkSurname;
        boolean secondCouple = checkCity && checkProvince;
        boolean thirdCouple = checkNickname && checkPassword;
        registerButton.setEnabled(firstCouple && secondCouple && checkEmail && thirdCouple);
    }

    public static void main(String[] args) {
        CustomerRegistration dialog = new CustomerRegistration();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}