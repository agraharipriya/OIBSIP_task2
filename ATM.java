import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
public class ATM {
    private static User currentUser;
    private static List<Transaction> transactionHistory = new ArrayList<>();

    public static void main(String[] args) {
        // Initialize user and transactions (for demo purposes)
        User user = new User("user123", 1234, 1000.0);
        currentUser = user;

        // Main ATM loop
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.println("Welcome to the ATM!");
            System.out.print("Enter your user ID: ");
            String userId = scanner.next();
            System.out.print("Enter your PIN: ");
            int pin = scanner.nextInt();

            if (userId.equals(user.getUserId()) && pin == user.getUserPin()) {
                displayMainMenu(scanner);
            } else {
                System.out.println("Invalid user ID or PIN. Please try again.");
            }
        }
    }

    private static void displayMainMenu(Scanner scanner) {
        boolean isUsingATM = true;
    
        while (isUsingATM) {
            System.out.println("\nMain Menu:");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Quit");
    
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    System.out.println("Current balance: $" + currentUser.getBalance());
                    break;
                case 2:
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    transactionHistory.add(new Transaction("Deposit", depositAmount));
                    System.out.println("Deposit successful.");
                    break;
                case 3:
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawalAmount);
                    transactionHistory.add(new Transaction("Withdrawal", withdrawalAmount));
                    break;
                case 4:
                    System.out.print("Enter recipient's user ID: ");
                    String recipientUserId = scanner.next();
                    User recipient = findUserById(recipientUserId);
                    if (recipient != null) {
                        System.out.print("Enter the transfer amount: $");
                        double transferAmount = scanner.nextDouble();
                        if (currentUser.transfer(recipient, transferAmount)) {
                            transactionHistory.add(new Transaction("Transfer to " + recipient.getUserId(), transferAmount));
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Transfer failed. Check the recipient's balance.");
                        }
                    } else {
                        System.out.println("Recipient user not found.");
                    }
                    break;
                case 5:
                    System.out.println("\nTransaction History:");
                    for (Transaction transaction : transactionHistory) {
                        System.out.println(transaction.getDescription() + " - $" + transaction.getAmount());
                    }
                    break;
                case 6:
                    isUsingATM = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
    
    // Helper method to find a user by their user ID
    private static User findUserById(String userId) {
        // For this example, we'll assume there's only one user.
        if (currentUser.getUserId().equals(userId)) {
            return currentUser;
        }
        return null;
    }
}
  