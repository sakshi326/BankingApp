package bankingapp;

import java.util.Scanner;
import bankingapp.InputHandler;

public class Main {
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            //Main Menu for application
            System.out.println("\n--- Banking Application ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = InputHandler.getIntInput(scanner);

            switch (choice) {
                case 1:
                    bankingSystem.registerUser(scanner);
                    break;
                case 2:
                    User loggedInUser = bankingSystem.login(scanner);
                    if (loggedInUser != null) {
                        userMenu(loggedInUser, bankingSystem, scanner);
                    }
                    break;
                case 3:
                    System.out.println("Thank you for using our Banking Application. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void userMenu(User user, BankingSystem bankingSystem, Scanner scanner) {
        while (true) {

            //Options for Banking Transaction
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Open New Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Check Balance");
            System.out.println("6. View Statement");
            System.out.println("7. Logout");
            System.out.print("Choose an option: ");

            int choice = InputHandler.getIntInput(scanner);

            switch (choice) {
                case 1:
                    // Prompt for account details
                    System.out.print("Enter Account Holder's Name: ");
                    String accountHolderName = scanner.nextLine();

                    System.out.print("Enter Account Type (savings/checking): ");
                    String accountType = scanner.nextLine().toLowerCase();

                    if (!accountType.equals("savings") && !accountType.equals("checking")) {
                        System.out.println("Invalid account type. Please choose 'savings' or 'checking'.");
                        break;
                    }

                    System.out.print("Enter Initial Deposit Amount: Rs.");
                    double initialDeposit = InputHandler.getDoubleInput(scanner);

                    if (initialDeposit < 1000) {
                        System.out.println("Initial deposit must be at least Rs. 1000.");
                        break;
                    }

                    // Create the account using BankingSystem and show details
                    Account newAccount = bankingSystem.openAccount(user, accountHolderName,accountType, initialDeposit);

                    if (newAccount != null) {
                        System.out.println("\nAccount created successfully!");
                        System.out.println("Account Holder Name: " + newAccount.getAccountHolderName());
                        System.out.println("Account Type: " + newAccount.getAccountType());
                        System.out.println("Initial Deposit: Rs." + newAccount.getBalance());
                    } else {
                        System.out.println("Account creation failed. Please try again.");
                    }
                    break;

                case 2:
                    bankingSystem.viewAccounts(user);
                    break;
                case 3:
                    bankingSystem.deposit(user, scanner);
                    break;
                case 4:
                    bankingSystem.withdraw(user, scanner);
                    break;
                case 5:
                    bankingSystem.checkBalance(user, scanner);
                    break;
                case 6:
                    bankingSystem.viewStatement(user, scanner);
                    break;
                case 7:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}