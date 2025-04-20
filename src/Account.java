import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {
    private String username;
    private String password;
    private double balance;
    private List<String> transactions; // Store transaction history

    public Account(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction("Deposited: " + amount + " Taka");
    }

    public boolean withdraw(double amount) {
        if (amount > balance) return false;
        balance -= amount;
        addTransaction("Withdrawn: " + amount + " Taka");
        return true;
    }

    public boolean transferTo(Account receiver, double amount) {
        if (amount > balance) return false; // Insufficient balance

        // Deduct from sender
        balance -= amount;
        addTransaction("Transferred: " + amount + " Taka " + " to " + receiver.getUsername());

        // Add to receiver
        receiver.balance += amount;
        receiver.addTransaction("Received: " + amount + " Taka " + " from " + username);

        return true;
    }

    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }
}
