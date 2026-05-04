package banksystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    public void testAddAndRemoveAccount() {
        Customer c = new Customer(123456789, "Phung Thanh Do");
        Account acc = new CheckingAccount(111, 1000);

        c.addAccount(acc);
        assertEquals(1, c.getAccountList().size());

        c.removeAccount(acc);
        assertEquals(0, c.getAccountList().size());
    }

    @Test
    public void testGetCustomerInfo() {
        Customer c = new Customer(123456789, "Test Name");
        assertEquals("Số CMND: 123456789. Họ tên: Test Name.", c.getCustomerInfo());
    }
}