package BankeBank;

import BankeBank.Account;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountTest {
    Account account1 = new Account("fave", "sam", "1234567898", "1234");
    Account account2 = new Account("dave", "kelvin", "9876543211", "2299");

    @Test
    public void testThatAccount1Exist(){
         assertNotNull(account1);
    }

     @Test
    public void testThatAccount2Exist(){
        assertNotNull(account2);
     }
    @Test
    public void testToCreatAccount1(){
        assertEquals("fave", account1.getfirstName());
        assertEquals("sam", account1.getlastName());
        assertEquals("1234", account1.getPin());
    }

    @Test
    public void testToCreatAccount2(){
        assertEquals("dave", account2.getfirstName());
        assertEquals("kelvin", account2.getlastName());
        assertEquals("2299", account2.getPin());

    }
    @Test
    public void testForDepositForAccount1(){
        account1.deposit(200.0);
        assertEquals(200.0,account1.balance(), 0.0);
    }

    @Test
    public void testForDepositForAccount2(){
        account2.deposit(200.0);
        assertEquals(200.0,account2.balance(), 0.0);


    }

    @Test
    public void testForWithdrawForAccount1(){
        account1.balance();
        account1.deposit(400.0);
        assertEquals(200.0, account1.withdraw("1234", 200.0), 0.0);
    }

    @Test
    public void testForWithdrawForAccount2(){
        account2.balance();
        account2.deposit(400.0);
        assertEquals(200.0, account2.withdraw("2299", 200.0), 0.0);
    }
    @Test
    public void testSuccessfulTransferBetweenAccounts() {
        account1.deposit( 1000.0);
        account2.deposit( 500.0);

        account1.transfer("1234", account2, 300.0);

        assertEquals(700.0, account1.balance(), 0.0);
        assertEquals(800.0, account2.balance(), 0.0);
    }

    @Test
    public void testInsufficientFundsForTransfer() {
        account1.deposit(200.0);
        assertThrows(IllegalArgumentException.class, () -> account1.transfer("1234", account2, 500.0));
    }

    @Test
    public void testInvalidPINForTransfer() {
        account1.deposit(500.0);
        assertThrows(IllegalArgumentException.class, () -> account1.transfer("0000", account2, 200.0));
    }
    @Test
    public void testCheckBalanceForAccount1() {
        account1.deposit(500.0);
        assertEquals(500.0, account1.balance(), 0.0);
    }

    @Test
    public void testCheckBalanceForAccount2() {
        account2.deposit(300.0);
        assertEquals(300.0, account2.balance(), 0.0);
    }

    @Test
    public void testInvalidPINForBalanceCheck() {
        account1.deposit(400.0);
        assertThrows(IllegalArgumentException.class, () -> account1.balance());
    }

    @Test
    public void testCloseAccountSuccessfully() {
        account1.closeAccount("1234");

        assertEquals(0.0, account1.balance(), 0.001);
        assertNull(account1.getAccountNumber());
    }

    @Test
    public void testCloseAccountWithIncorrectPIN() {
        assertThrows(IllegalArgumentException.class, () -> account1.closeAccount("1570"));
    }

    @Test
    public void testCorrectPinChangeForAccount1() {
        assertEquals("5678", account1.changePin("1234", "5678"));
    }

    @Test
    public void testIncorrectOldPinForAccount1() {
        assertEquals("5678", account1.changePin("1234", "5678"));
    }

    @Test
    public void testInvalidNewPinForAccount1() {
        assertEquals("abcd", account1.changePin("1234", "abcd"));
    }

    @Test
    public void testCorrectPinChangeForAccount2() {
        assertEquals("5678", account2.changePin("2299", "5678"));
    }

    @Test
    public void testIncorrectOldPinForAccount2() {
        assertEquals("5678", account2.changePin("2299", "5678"));
    }

    @Test
    public void testInvalidNewPinForAccount2() {
        assertEquals("abcd", account2.changePin("2299", "abcd"));
    }
}