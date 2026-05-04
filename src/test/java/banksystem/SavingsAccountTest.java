package banksystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SavingsAccountTest {

    @Test
    public void testWithdrawValidAmount() {
        SavingsAccount acc = new SavingsAccount(999, 6000.0);
        acc.withdraw(500.0);
        assertEquals(5500.0, acc.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawExceedMax() {
        SavingsAccount acc = new SavingsAccount(999, 10000.0);
        acc.withdraw(1500.0);
        assertEquals(10000.0, acc.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawViolateMinBalance() {
        SavingsAccount acc = new SavingsAccount(999, 5400.0);
        acc.withdraw(500.0);
        assertEquals(5400.0, acc.getBalance(), 0.001);
    }
}