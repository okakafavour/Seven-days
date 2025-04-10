package BankeBank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {
        BankAppFile main = new BankAppFile();
        List<Account> accounts = main.getAccounts();
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("""
                    \n1. Create an account.
                    2. Deposit money.
                    3. Withdraw money.
                    4. Check balance.
                    5. Transfer from one account to another.
                    6. Change pin.
                    7. Close account.
                    """);

            System.out.println("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    String firstName, lastName, pin = "";

                    do {
                        System.out.print("Enter first name.(the first letter in uppercase): ");
                        firstName = input.nextLine();
                        if (firstName.isEmpty() || !firstName.matches("[a-zA-Z]+")) {
                            System.out.println("Invalid first name. Please enter only letters.");
                        }
                    } while (firstName.isEmpty() || !firstName.matches("[a-zA-Z]+"));

                    do {
                        System.out.print("Enter last name.(the first letter in uppercase): ");
                        lastName = input.nextLine();
                        if (lastName.isEmpty() || !lastName.matches("[a-zA-Z]+")) {
                            System.out.println("Invalid last name. Please enter only letters.");
                        }
                    } while (lastName.isEmpty() || !lastName.matches("[a-zA-Z]+"));

                    do {
                        System.out.print("Enter pin (4 digits): ");
                        pin = input.next();
                        if (!pin.matches("\\d{4}")) {
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                        }
                    } while (!pin.matches("\\d{4}"));

                    try {
                        String accountNumber = main.createAccount(firstName, lastName, pin);
                        Account newAccount = main.findAccountByNumber(accountNumber);
                        if (newAccount != null) {
                            accounts.add(newAccount);
                        }

                        System.out.println("Account created successfully.");
                        System.out.println("your account number is: " + accountNumber);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;

                case 2:
                    double amount = 0.0;
                    String pin2;

                    do {
                        System.out.print("Enter amount you want to deposit: ");
                        amount = input.nextDouble();
                        input.nextLine();

                        if (amount <= 0) {
                            System.out.println("Invalid amount. Please enter only positive numbers.");
                        }

                    } while (amount <= 0);
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts found. Please create an account first.");
                        break;
                    }
                    Account account = accounts.get(0);

                    do {
                        System.out.print("Enter pin to deposit: ");
                        pin2 = input.nextLine();

                        if (!pin2.matches("\\d{4}") || !pin2.equals(account.getPin())) {
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                        }
                    } while (!pin2.matches("\\d{4}") || !pin2.equals(account.getPin()));
                    main.getDeposit(account.getAccountNumber(), account.getPin(), amount);
                    System.out.println("Deposit successful. New balance: " + main.getBalance(account.getAccountNumber(), account.getPin()));

                    break;

                case 3:
                    double amount2;
                    String pin3;

                    do {
                        System.out.print("Enter amount you want to withdraw: ");
                        amount = input.nextDouble();
                        input.nextLine();

                        if (amount <= 0) {
                            System.out.println("Insufficient funds.");
                        }

                    } while (amount <= 0);

                    account = accounts.get(0);
                    do {
                        System.out.print("Enter pin to withdraw: ");
                        pin3 = input.nextLine();

                        if (!pin3.matches("\\d{4}") || !pin3.equals(account.getPin())) {
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                        }
                    } while (!pin3.matches("\\d{4}") || !pin3.equals(account.getPin()));
                    main.getWithdraw(account.getAccountNumber(), account.getPin(), amount);
                    System.out.println("Withdrawal Success");
                    break;

                case 4:
                    String balancePin;

                    do {
                        System.out.print("Enter your pin: ");
                        balancePin = input.nextLine();

                        if (accounts.isEmpty()) {
                            System.out.println("No accounts found. Please create an account first.");
                            break;
                        }

                        System.out.print("Enter your account number: ");
                        String accountNumber = input.nextLine();
                         account = main.findAccountByNumber(accountNumber);

                        Account targetAccount = null;
                        for (Account acc : accounts) {
                            if (acc.getAccountNumber().equals(accountNumber)) {
                                targetAccount = acc;
                                break;
                            }
                        }

                        if (targetAccount == null) {
                            System.out.println("Account not found!");
                        } else {
                            System.out.println("Your balance is: " + main.getBalance(targetAccount.getAccountNumber(), targetAccount.getPin()));
                        }

                        if (!balancePin.matches("\\d{4}") || !balancePin.equals(account.getPin())) {
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                        } else {
                            System.out.println("your balance is: " + main.getBalance(account.getAccountNumber(), balancePin));
                        }

                    } while (!balancePin.matches("\\d{4}") || !balancePin.equals(account.getPin()));
                    break;

                case 5:
                    String receiverAccountNumber, senderAccountNumber;
                    String senderPin;
                    do {
                        System.out.print("Enter your sender account number: ");
                        senderAccountNumber = input.nextLine();

                        if ((senderAccountNumber.length() != 10) || !senderAccountNumber.matches("\\d{10}")) {
                            System.out.println("Invalid sender account number. Please enter only 10 digits.");
                        }
                    } while ((senderAccountNumber.length() != 10) || !senderAccountNumber.matches("\\d{10}"));

                    do {
                        System.out.print("Enter the account number: ");
                        receiverAccountNumber = input.nextLine();

                        if ((receiverAccountNumber.length() != 10) || !receiverAccountNumber.matches("\\d{10}")) {
                            System.out.println("Invalid account number");
                        }
                    } while ((receiverAccountNumber.length() != 10) || !receiverAccountNumber.matches("\\d{10}"));

                    Account sender = main.findAccountByNumber(senderAccountNumber);
                    Account receiver = main.findAccountByNumber(receiverAccountNumber);

                    if (sender == null || receiver == null) {
                        System.out.println("One or both accounts not found!");
                        break;
                    }

                    do {
                        System.out.print("Enter amount you want to deposit: ");
                        amount = input.nextDouble();
                        input.nextLine();
                        if (amount <= 0) {
                            System.out.println("Invalid amount. Please enter only positive numbers.");
                        }
                    } while (amount <= 0);

                    do {
                        System.out.print("Enter sender pin: ");
                        senderPin = input.nextLine();

                        if (!senderPin.matches("\\d{4}") || !senderPin.equals(sender.getPin())) {
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                        }
                    } while (!senderPin.matches("\\d{4}") || !senderPin.equals(sender.getPin()));
                    main.getTransfer(senderAccountNumber, senderPin, receiverAccountNumber, amount);
                    System.out.println("Transfer Success");
                    break;


                case 6:
                    String accountNumber = " ";
                    String oldPin, newPin;

                    boolean accountNumberExists = false;
                    while(!accountNumberExists){

                        System.out.print("Enter your account number: ");
                         accountNumber= input.nextLine();

                         for(Account acc : accounts){
                             if (acc.getAccountNumber().equals(accountNumber)) {
                                 accountNumberExists = true;
                                 break;
                             } else if(!accountNumberExists){
                                 System.out.println("Invalid account number.");
                             }
                         }
                          
                           Account exactAccount = null;
                         for(Account acc : accounts){
                             if(acc.getAccountNumber().equals(accountNumber)){
                                 exactAccount = acc;
                                 break;
                             }
                         }
                    }

                    while(!acc.getAccountNumber.equals(accountNumber));
                        System.out.print("Enter account number");
                        accountNumber = input.nextLine();
                        
                        for (Account acc : accounts) {
                            if (!acc.getAccountNumber().equals(accountNumber)){
                               System.out.println("Invalid account number"); 
                            }
                            break;
                        }        
                    }
                    
                    do {
                        System.out.print("Enter old pin: ");
                         oldPin = input.nextLine();

                        if ((!oldPin.equals(pin)) || oldPin.matches("\\d{4}")) {
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                        }
                    } while ((!oldPin.equals(pin)) || oldPin.matches("\\d{4}"));
                    
                    do{
                        System.out.print("Enter new pin: ");
                        newPin = input.nextLine();
                        
                        if(newPin.equals(oldPin) || newPin.matches("\\d{4}")) {
                            System.out.println("Invalid pin. It must be exactly 4 digits.");
                        }
                    }  while (newPin.equals(oldPin) || newPin.matches("\\d{4}"));
                        main.getChangePin(accountNumber, newPin, oldPin);
                    
                    
                    

            }
        }
    }
}