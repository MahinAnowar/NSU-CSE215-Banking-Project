import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class TransferPanel extends JFrame {
    private JTextField recipientField, amountField;
    private JButton transferButton;
    private Account senderAccount;

    public TransferPanel(Account sender) {
        this.senderAccount = sender;

        setTitle("Transfer Money");
        setSize(300, 200);
        setLayout(new GridLayout(4, 1));

        recipientField = new JTextField();
        amountField = new JTextField();
        transferButton = new JButton("Transfer");

        add(new JLabel("Recipient Username:"));
        add(recipientField);
        add(new JLabel("Amount:"));
        add(amountField);
        add(transferButton);

        transferButton.addActionListener(this::handleTransfer);

        setVisible(true);
    }

    private void handleTransfer(ActionEvent e) {
        String recipientUsername = recipientField.getText().trim();
        double amount;

        try {
            amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a valid amount!");
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Enter a numeric value.");
            return;
        }

        List<Account> accounts = FileHandler.loadAccounts();
        Account recipientAccount = null;

        for (Account acc : accounts) {
            if (acc.getUsername().equals(recipientUsername)) {
                recipientAccount = acc;
                break;
            }
        }

        if (recipientAccount == null) {
            JOptionPane.showMessageDialog(this, "Recipient not found!");
            return;
        }

        if (!senderAccount.withdraw(amount)) {
            JOptionPane.showMessageDialog(this, "Insufficient funds!");
            return;
        }

        // Transfer money
        recipientAccount.deposit(amount);
        updateAccounts(senderAccount, recipientAccount);

        // Save transaction
        FileHandler.saveTransaction(senderAccount.getUsername() + " transferred " + amount + " Taka" + " to " + recipientAccount.getUsername());
        JOptionPane.showMessageDialog(this, "Transfer Successful!");

        dispose();
    }

    private void updateAccounts(Account sender, Account recipient) {
        List<Account> accounts = FileHandler.loadAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(sender.getUsername())) {
                accounts.set(i, sender);
            }
            if (accounts.get(i).getUsername().equals(recipient.getUsername())) {
                accounts.set(i, recipient);
            }
        }
        FileHandler.saveAccounts(accounts);
    }
}
