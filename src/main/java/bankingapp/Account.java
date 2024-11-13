package bankingapp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private String accountNumber;
    private String accountHolderName;
    private String accountType;
    private double balance;
    private List<Transaction> transactions;
    private long lastInterestCalculationTime;

    public Account(String accountHolderName, String accountType, double initialDeposit) {
        this.accountNumber = UUID.randomUUID().toString();
        this.accountHolderName = accountHolderName;
        this.accountType = accountType;
        this.balance = initialDeposit;
        this.transactions = new ArrayList<>();
        this.lastInterestCalculationTime = System.currentTimeMillis();
        addTransaction(new Transaction("Deposit", initialDeposit));
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        addTransaction(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            addTransaction(new Transaction("Withdrawal", amount));
            return true;
        }
        return false;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void calculateInterest() {
        if (accountType.equalsIgnoreCase("savings")) {
            long currentTime = System.currentTimeMillis();
            long oneMonthInMillis = 30L * 24 * 60 * 60 * 1000; // Approximately one month
            if (currentTime - lastInterestCalculationTime >= oneMonthInMillis) {
                double interestRate = 0.05; // 5% annual interest rate
                double monthlyInterest = balance * (interestRate / 12);
                balance += monthlyInterest;
                addTransaction(new Transaction("Interest", monthlyInterest));
                lastInterestCalculationTime = currentTime;
            }
        }
    }
}