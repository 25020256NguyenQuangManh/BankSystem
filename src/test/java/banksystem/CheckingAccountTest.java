package banksystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CheckingAccountTest {

    @Test
    public void testDepositValidAmount() {
        CheckingAccount acc = new CheckingAccount(12345, 1000.0);
        acc.deposit(500.0);
        assertEquals(1500.0, acc.getBalance(), 0.001);
        assertEquals(1, acc.getTransactionList().size());
    }

    @Test
    public void testDepositInvalidAmount() {
        CheckingAccount acc = new CheckingAccount(12345, 1000.0);
        acc.deposit(-500.0); // Ném lỗi InvalidFundingAmountException, số dư không đổi
        assertEquals(1000.0, acc.getBalance(), 0.001);
        assertEquals(0, acc.getTransactionList().size());
    }

    @Test
    public void testWithdrawValidAmount() {
        CheckingAccount acc = new CheckingAccount(12345, 1000.0);
        acc.withdraw(400.0);
        assertEquals(600.0, acc.getBalance(), 0.001);
    }

    @Test
    public void testWithdrawInsufficientFunds() {
        CheckingAccount acc = new CheckingAccount(12345, 1000.0);
        acc.withdraw(1500.0);
        assertEquals(1000.0, acc.getBalance(), 0.001);
    }

    @Test
    public void testGetTransactionHistory() {
        CheckingAccount acc = new CheckingAccount(12345, 1000.0);
        acc.deposit(500.0);
        String history = acc.getTransactionHistory();
        assertTrue(history.contains("Lịch sử giao dịch của tài khoản 12345"));
        assertTrue(history.contains("Nạp tiền vãng lai"));
    }
}