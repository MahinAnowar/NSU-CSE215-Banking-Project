import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String DATA_FOLDER = "data";
    private static final String ACCOUNTS_FILE = DATA_FOLDER + "/accounts.txt";
    private static final String TRANSACTIONS_FILE = DATA_FOLDER + "/transactions.txt";

    private static void ensureDataDirectory() {
        File dataDir = new File(DATA_FOLDER);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    public static void saveAccounts(List<Account> accounts) {
        ensureDataDirectory();

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ACCOUNTS_FILE))) {
            oos.writeObject(accounts);
        } catch (IOException e) {
            System.err.println("Error saving accounts: " + e.getMessage());
        }
    }

    public static List<Account> loadAccounts() {
        ensureDataDirectory();

        File file = new File(ACCOUNTS_FILE);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ACCOUNTS_FILE))) {
            return (List<Account>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading accounts: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Save transaction history
    public static void saveTransaction(String transaction) {
        ensureDataDirectory();

        try (FileWriter fw = new FileWriter(TRANSACTIONS_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(transaction);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    // Load transaction history
    public static List<String> loadTransactions() {
        ensureDataDirectory();

        List<String> transactions = new ArrayList<>();
        File file = new File(TRANSACTIONS_FILE);
        if (!file.exists()) return transactions;

        try (BufferedReader br = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                transactions.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }
        return transactions;
    }
}
