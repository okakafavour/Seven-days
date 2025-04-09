package BankeBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BankAppFile {
    private final ArrayList<Account> accounts = new ArrayList<>();

    public String createAccount(String firstName, String lastName, String pin){
        if(!firstName.matches("[A-Z][a-zA-Z]*")){
            throw new IllegalArgumentException("Invalid first name");
        }
        if(!lastName.matches("[A-Z][a-zA-Z]*")){
            throw new IllegalArgumentException("Invalid last name");
        }
        if(!pin.matches("[0-9]*")){
            throw new IllegalArgumentException("Invalid pin");
        }


        Random random = new Random();
        String accountNumbers = "074" + String.format("%7d", random.nextInt(1000000000));
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
                return account.getBalance();
            }
        }
        throw new IllegalArgumentException("No account found!");
    }

    public void getDeposit(String accountNumber, String pin, double amount) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                account.getDeposit(amount);
                return;
            }
        }
        throw new IllegalArgumentException("No account found or incorrect PIN!");
    }

    public void getWithdraw(String accountNumber, String pin, double amount) {
        for(Account account : accounts){
            if (account.getAccountNumber().equals(accountNumber) && account.getPin().equals(pin)) {
                account.getWithdraw(pin, amount);
                return;
            }
        }
        throw new IllegalArgumentException("No account found or incorrect PIN!");
    }


}
