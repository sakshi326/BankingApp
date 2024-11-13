package bankingapp;

import java.util.Scanner;
import bankingapp.InputHandler;

public class BankingSystem {
    private DataStore dataStore;

    public BankingSystem() {
        this.dataStore = new DataStore();
    }

    public void registerUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (dataStore.getUserByUsername(username) != null) {
            System.out.println("Username already exists. Please choose a different one.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User newUser = new User(username, password);
        dataStore.addUser(newUser);
        System.out.println("User registered successfully!");
    }

    public User login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User user = dataStore.getUserByUsername(username);
        if (user != null && user.verifyPassword(password)) {
            System.out.println("Login successful!");
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
    public void viewAccounts(User user) {
        System.out.println("\n--- Your Accounts ---");
        for (Account account : user.getAccounts()) {
            System.out.printf("Account Number: %s | Type: %s | Balance: Rs.%.2f%n",
                    account.getAccountNumber(), account.getAccountType(), account.getBalance());
        }
    }

    public void deposit(User user, Scanner scanner) {
        Account account = selectAccount(user, scanner);
        if (account == null) return;
        System.out.print("Enter deposit amount: Rs.");
        double amount = InputHandler.getDoubleInput(scanner);
        if (amount <= 0) {
            System.out.println("Deposit amount must be positive.");
            return;
        }
        account.deposit(amount);
        System.out.printf("Deposit successful. New balance: Rs.%.2f%n", account.getBalance());
    }

    public void withdraw(User user, Scanner scanner) {
        Account account = selectAccount(user, scanner);
        if (account == null) return;
        System.out.print("Enter withdrawal amount: Rs.");
        double amount = InputHandler.getDoubleInput(scanner);
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }
        if (account.withdraw(amount)) {
            System.out.printf("Withdrawal successful. New balance: Rs.%.2f%n", account.getBalance());
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public void checkBalance(User user, Scanner scanner) {
        Account account = selectAccount(user, scanner);
        if (account == null) return;
        System.out.printf("Current balance: Rs.%.2f%n", account.getBalance());
    }

    public void viewStatement(User user, Scanner scanner) {
        Account account = selectAccount(user, scanner);
        if (account == null) return;
        System.out.println("\n--- Account Statement ---");
        for (Transaction transaction : account.getTransactions()) {
            System.out.println(transaction);
        }
    }

    private Account selectAccount(User user, Scanner scanner) {
        if (user.getAccounts().isEmpty()) {
            System.out.println("You don't have any accounts.");
            return null;
        }
        System.out.println("Select an account:");
        for (int i = 0; i < user.getAccounts().size(); i++) {
            Account account = user.getAccounts().get(i);
            System.out.printf("%d. %s (%s)%n", i + 1, account.getAccountNumber(), account.getAccountType());
        }
        int selection = InputHandler.getIntInput(scanner) - 1;
        if (selection < 0 || selection >= user.getAccounts().size()) {
            System.out.println("Invalid selection.");
            return null;
        }
        return user.getAccounts().get(selection);
    }

    public Account openAccount(User user, String accountHolderName,String accountType, double initialDeposit) {

            // Create and associate account with the user
            Account newAccount = new Account(accountHolderName, accountType, initialDeposit);
            user.addAccount(newAccount); // Example method to add the account to the user's list

            return newAccount;
        }

    }