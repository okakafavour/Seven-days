package BankeBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAppFile {
    private final ArrayList<Account> accounts = new ArrayList<>();

    public String createAccount(String firstName, String lastName, String pin){
        if(!firstName.matches("[a-zA-Z]*")){
            throw new IllegalArgumentException("Invalid first name");
        }
        if(!lastName.matches("[a-zA-Z]*")){
            throw new IllegalArgumentException("Invalid last name");
        }
        if(!pin.matches("[0-9]{4}")){
            throw new IllegalArgumentException("Invalid pin");
        }


        Random random = new Random();
        String accountNumbers = "074" + String.format("%07d", random.nextInt(10000000));
        Account account = new Account(firstName, lastName, accountNumbers, pin);
        accounts.add(account);
        return accountNumbers;

    }

    public Account findAccountByNumber(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public String getAccountNumber() {
        if (!accounts.isEmpty()) {
            return accounts.get(accounts.size() - 1).getAccountNumber();
        }
        return "No account found!";
    }


    public List<Account> getAccounts() {
        return accounts;
    }

    public double getBalance(String accountNumber, String pin) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                return account.balance();
            }
        }
        throw new IllegalArgumentException("No account found!");
    }

    public void getDeposit(String accountNumber, String pin, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                account.deposit(amount);
                return;
            }
        }
        throw new IllegalArgumentException("No account found or incorrect PIN!");
    }

    public void getWithdraw(String accountNumber, String pin, double amount) {
        for(Account account : accounts){
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                account.withdraw(pin, amount);
                return;
            }
        }
        throw new IllegalArgumentException("No account found or incorrect PIN!");
    }

    public void getTransfer(String senderAccountNumber, String senderPin, String receiverAccountNumber, double amount) {
        Account sender = findAccountByNumber(senderAccountNumber);
        Account receiver = findAccountByNumber(receiverAccountNumber);

        if (sender == null || receiver == null) {
            throw new IllegalArgumentException("Invalid account number!");
        }
        if (!sender.getPin().equals(senderPin)) {
            throw new IllegalArgumentException("Incorrect PIN!");
        }
        if (amount <= 0 || sender.balance() < amount) {
            throw new IllegalArgumentException("Insufficient funds!");
        }

        sender.withdraw(senderPin, amount);
        receiver.deposit(amount);
    }

    public String getChangePin(String accountNumber, String newPin, String oldPin) {
        for (Account account : accounts) {
            if((account.getAccountNumber().equals(accountNumber) && account.getPin().equals(oldPin))){
                oldPin.equals(newPin);
                return newPin;
            }
            throw new IllegalArgumentException("Invalid accounts");
        }
        return accountNumber;
    }

}
