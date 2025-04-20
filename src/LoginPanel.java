import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class LoginPanel extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, createAccountButton;

    public LoginPanel() {
        setTitle("Banking System - Login");
        setSize(350, 200);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);

        Font font = new Font("Arial", Font.BOLD, 16);

        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account"); // New Button ✅

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(createAccountButton);

        loginButton.addActionListener(this::handleLogin);
        createAccountButton.addActionListener(e -> openCreateAccountPanel()); // Open Signup ✅

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        List<Account> accounts = FileHandler.loadAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username) && acc.getPassword().equals(password)) {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                new DashboardPanel(username);
                dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid username or password!");
    }

    private void openCreateAccountPanel() {
        new CreateAccountPanel();
        dispose(); // Close
    }

    public static void main(String[] args) {
        new LoginPanel();
    }
}
