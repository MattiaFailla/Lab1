package clienti.Registration;

import javax.swing.*;
import java.awt.event.*;

public class CustomerRegistration extends JDialog{
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField municipalityField;
    private JTextField provinceField;
    private JTextField emailField;
    private JTextField nicknameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton buttonCancel;

    public CustomerRegistration() {
        setContentPane(contentPane);
        setModal(true);
        //getRootPane().setDefaultButton(buttonLogin);

        registerButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Registered Successfully!"));
        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            public void mouseEntered(MouseEvent e) { onEnabled(); }

            @Override
            public void mouseExited(MouseEvent e) { }
        });

        buttonCancel.addActionListener(e -> onCancel());
        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) { onCancel(); }
        });

        //region FOCUS
        nameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { nameField.selectAll(); }
            public void focusLost(FocusEvent e) { onEnabled(); }
        });
        surnameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { surnameField.selectAll(); }
            public void focusLost(FocusEvent e) { onEnabled(); }
        });
        municipalityField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { municipalityField.selectAll(); }
            public void focusLost(FocusEvent e) { onEnabled(); }
        });
        provinceField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { provinceField.selectAll(); }
            public void focusLost(FocusEvent e) { onEnabled(); }
        });
        emailField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { emailField.selectAll(); }
            public void focusLost(FocusEvent e) { onEnabled(); }
        });
        nicknameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { nicknameField.selectAll(); }
            public void focusLost(FocusEvent e) { onEnabled(); }
        });
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) { passwordField.selectAll(); }
            public void focusLost(FocusEvent e) { onEnabled(); }
        });
        //endregion

    }

    private void onCancel() {
        // add your code here if necessary
        System.out.println("Closing app..");
        dispose();
    }

    private void onEnabled() {
        // add your code here if necessary
        boolean checkName = nameField.getText().length() > 0;
        boolean checkSurname = surnameField.getText().length() > 0;
        boolean checkMunicipality = municipalityField.getText().length() > 0;
        boolean checkProvince = provinceField.getText().length() > 0;
        boolean checkEmail = emailField.getText().length() > 0;
        boolean checkNickname = nicknameField.getText().length() > 0;
        boolean checkPassword = passwordField.getText().length() > 0;
        boolean firstCouple = checkName && checkSurname;
        boolean secondCouple = checkMunicipality && checkProvince;
        boolean thirdCouple = checkNickname && checkPassword;
        registerButton.setEnabled(firstCouple && secondCouple && checkEmail && thirdCouple);
    }

    public static void main(String[] args) {
        CustomerRegistration dialog = new CustomerRegistration();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);

        /*
        JFrame frame = new JFrame("Customer Registration");
        frame.setContentPane(new CustomerRegistration().contentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        */
    }
}


