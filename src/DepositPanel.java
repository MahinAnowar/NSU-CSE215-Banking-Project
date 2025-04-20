import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DepositPanel extends JFrame {
    private JTextField amountField;
    private JButton depositButton;
    private Account currentAccount;

    public DepositPanel(Account account) {
        this.currentAccount = account;

        setTitle("Deposit Funds");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        amountField = new JTextField();
        depositButton = new JButton("Deposit");

        add(new JLabel("Enter Amount:"));
        add(amountField);
        add(depositButton);

        depositButton.addActionListener(this::handleDeposit);

        setVisible(true);
    }

    private void handleDeposit(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a valid amount!");
                return;
            }

            // Update balance
            currentAccount.deposit(amount);
            updateAccountData(currentAccount);

            // Save transaction
            FileHandler.saveTransaction(currentAccount.getUsername() + " deposited" + amount + " Taka ");
            JOptionPane.showMessageDialog(this, "Deposit Successful!");

            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Enter a numeric value.");
        }
    }

    private void updateAccountData(Account updatedAccount) {
        List<Account> accounts = FileHandler.loadAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getUsername().equals(updatedAccount.getUsername())) {
                accounts.set(i, updatedAccount);
                break;
            }
        }
        FileHandler.saveAccounts(accounts);
    }
}
