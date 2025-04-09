package BankeBank;

public class Account {
    private final String firstName;
    private final String lastName;
    private String pin;
    private double balance;
    private String accountNumber;

    public Account(String firstName, String lastName, String accountNumber, String pin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }

    public String getfirstName() {
        return firstName;
    }


    public String getlastName() {
        return this.lastName;
    }

    public String getPin() {
        return this.pin;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void deposit(double amount) {
        if (amount <= 0.0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        this.balance += amount;
    }

    public double withdraw(String pin, double amount) {
        if (!pin.equals(this.pin)) {
            throw new IllegalArgumentException("Invalid PIN");
        }
        if (amount <= 0.0 || amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        this.balance -= amount;
        return this.balance;
    }

    public double balance() {
        return this.balance;
    }

    public boolean validatePin(String pin) {
        return pin.equals(this.pin);
    }

    public void transfer(String pin, Account receiver, double amount) {
        if (!validatePin(pin)) {
            throw new IllegalArgumentException("PIN does not match");
        }
        if (this.balance < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }

        this.withdraw(pin, amount);
        receiver.deposit(amount);
    }

    public void closeAccount(String pin) {
        if (!validatePin(pin)) {
            throw new IllegalArgumentException("Invalid PIN");
        }

        this.balance = 0.0;
        this.accountNumber = null;
    }

    public String changePin(String oldPin, String newPin) {
        if (!validatePin(oldPin)) {
            throw new IllegalArgumentException("Incorrect current PIN");
        }
        if (newPin == null || newPin.length() < 4) {
            throw new IllegalArgumentException("New PIN must be 4");
        }
          return this.pin = newPin;

    }


    public String getAccounts() {
        return accountNumber;
    }
}
