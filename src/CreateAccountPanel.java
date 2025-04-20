import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CreateAccountPanel extends JFrame {
    public CreateAccountPanel() {
        setTitle("Create Account");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));
        setLocationRelativeTo(null); // ✅ Centering the window

        Font labelFont = new Font("Arial", Font.BOLD, 16); // ✅ Increased font size

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(labelFont);

        JTextField usernameField = new JTextField();
        usernameField.setFont(labelFont);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(labelFont);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setFont(labelFont);

        JButton createButton = new JButton("Create Account");
        createButton.setFont(labelFont);

        JButton backButton = new JButton("Back");
        backButton.setFont(labelFont);

        createButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            List<Account> accounts = FileHandler.loadAccounts();
            for (Account acc : accounts) {
                if (acc.getUsername().equals(username)) {
                    JOptionPane.showMessageDialog(this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            Account newAccount = new Account(username, password, 0.0);
            accounts.add(newAccount);
            FileHandler.saveAccounts(accounts);
            JOptionPane.showMessageDialog(this, "Account created successfully!");

            dispose(); // Close Create Account panel
            new LoginPanel(); // Redirect to login panel
        });

        backButton.addActionListener(e -> {
            dispose();
            new LoginPanel();
        });

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(createButton);
        add(backButton);

        setVisible(true);
    }
}
