import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TransactionHistoryPanel extends JFrame {
    private JTextArea historyArea;
    private Account currentAccount;

    public TransactionHistoryPanel(Account account) {
        this.currentAccount = account;

        setTitle("Transaction History - " + account.getUsername());
        setSize(400, 300);
        setLayout(new BorderLayout());

        historyArea = new JTextArea();
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);

        loadTransactionHistory();

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    private void loadTransactionHistory() {
        List<String> transactions = FileHandler.loadTransactions();
        StringBuilder history = new StringBuilder();

        for (String transaction : transactions) {
            if (transaction.startsWith(currentAccount.getUsername())) {
                history.append(transaction).append("\n");
            }
        }

        if (history.length() == 0) {
            history.append("No transactions found.");
        }

        historyArea.setText(history.toString());
    }
}
