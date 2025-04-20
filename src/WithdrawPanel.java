import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class WithdrawPanel extends JFrame {
    private JTextField amountField;
    private JButton withdrawButton;
    private Account currentAccount;

    public WithdrawPanel(Account account) {
        this.currentAccount = account;

        setTitle("Withdraw Funds");
        setSize(300, 200);
        setLayout(new GridLayout(3, 1));

        amountField = new JTextField();
        withdrawButton = new JButton("Withdraw");

        add(new JLabel("Enter Amount:"));
        add(amountField);
        add(withdrawButton);

        withdrawButton.addActionListener(this::handleWithdraw);

        setVisible(true);
    }

    private void handleWithdraw(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText().trim());
            if (amount <= 0) {
                JOptionPane.showMessageDialog(this, "Enter a valid amount!");
                return;
            }

            if (!currentAccount.withdraw(amount)) {
                JOptionPane.showMessageDialog(this, "Insufficient funds!");
                return;
            }

            // Update balance
            updateAccountData(currentAccount);

            // Save transaction
            FileHandler.saveTransaction(currentAccount.getUsername() + " withdrew" + amount + " Taka ");
            JOptionPane.showMessageDialog(this, "Withdrawal Successful!");

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
