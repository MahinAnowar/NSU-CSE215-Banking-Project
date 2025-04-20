import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JFrame {
    private JLabel balanceLabel;
    private Account currentAccount;
    private List<Account> accounts;

    public DashboardPanel(String username) {
        setTitle("Dashboard - " + username);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 1));
        setLocationRelativeTo(null); // ✅ Centering the window

        Font buttonFont = new Font("Arial", Font.BOLD, 16); // ✅ Increased font size

        accounts = FileHandler.loadAccounts();
        for (Account acc : accounts) {
            if (acc.getUsername().equals(username)) {
                currentAccount = acc;
                break;
            }
        }

        if (currentAccount == null) {
            JOptionPane.showMessageDialog(this, "Account not found!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        balanceLabel = new JLabel("Balance: " + currentAccount.getBalance() +" Taka", SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JButton depositButton = new JButton("Deposit");
        JButton withdrawButton = new JButton("Withdraw");
        JButton transferButton = new JButton("Transfer");
        JButton historyButton = new JButton("Transaction History");
        JButton logoutButton = new JButton("Logout");

        depositButton.setFont(buttonFont);
        withdrawButton.setFont(buttonFont);
        transferButton.setFont(buttonFont);
        historyButton.setFont(buttonFont);
        logoutButton.setFont(buttonFont);

        depositButton.addActionListener(e -> handleDeposit());
        withdrawButton.addActionListener(e -> handleWithdraw());
        transferButton.addActionListener(e -> handleTransfer());
        historyButton.addActionListener(e -> handleTransactionHistory());
        logoutButton.addActionListener(e -> handleLogout());

        add(balanceLabel);
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(historyButton);
        add(logoutButton);

        setVisible(true);
    }

    private void handleDeposit() {
        String input = JOptionPane.showInputDialog(this, "Enter deposit amount:");
        if (input == null) return;

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) throw new NumberFormatException();

            currentAccount.deposit(amount);
            FileHandler.saveAccounts(accounts);
            updateBalanceLabel();
            JOptionPane.showMessageDialog(this, "Deposit successful!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.");
        }
    }

    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog(this, "Enter withdrawal amount:");
        if (input == null) return;

        try {
            double amount = Double.parseDouble(input);
            if (amount <= 0) throw new NumberFormatException();

            if (currentAccount.withdraw(amount)) {
                FileHandler.saveAccounts(accounts);
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Withdrawal successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.");
        }
    }

    private void handleTransfer() {
        String receiverUsername = JOptionPane.showInputDialog(this, "Enter receiver's username:");
        if (receiverUsername == null || receiverUsername.trim().isEmpty()) return;

        try {
            double amount = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter amount to transfer:"));
            if (amount <= 0) throw new NumberFormatException();

            Account receiver = null;
            for (Account acc : accounts) {
                if (acc.getUsername().equals(receiverUsername)) {
                    receiver = acc;
                    break;
                }
            }

            if (receiver == null) {
                JOptionPane.showMessageDialog(this, "Receiver not found.");
                return;
            }

            if (currentAccount.transferTo(receiver, amount)) {
                FileHandler.saveAccounts(accounts);
                updateBalanceLabel();
                JOptionPane.showMessageDialog(this, "Transfer successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient funds.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid amount entered.");
        }
    }

    private void handleTransactionHistory() {
        StringBuilder history = new StringBuilder("Transaction History:\n");
        for (String transaction : currentAccount.getTransactions()) {
            history.append(transaction).append("\n");
        }
        JOptionPane.showMessageDialog(this, history.toString(), "Transaction History", JOptionPane.INFORMATION_MESSAGE);
    }

    private void handleLogout() {
        dispose();
        SwingUtilities.invokeLater(LoginPanel::new);
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Balance: " + currentAccount.getBalance() + "Taka");
    }
}
