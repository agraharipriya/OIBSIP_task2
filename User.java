public class User {
    private String userId;
    private int userPin;
    private double balance;

    public User(String userId, int userPin, double balance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public int getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    public boolean transfer(User recipient, double amount) {
        if (balance >= amount) {
            balance -= amount;
            recipient.deposit(amount);
            return true;
        } else {
            System.out.println("Insufficient balance.");
            return false;
        }
    }
}
