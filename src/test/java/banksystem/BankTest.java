package banksystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;

public class BankTest {

    @Test
    public void testExportFilePath() {
        Bank bank = new Bank();
        String fileName = "data.csv";
        String expectedPath = "export_dir" + File.separator + fileName;
        assertEquals(expectedPath, bank.getExportFilePath(fileName),
                "Đường dẫn file không khớp chuẩn của hệ điều hành");
    }

    @Test
    public void testReadCustomerList() {
        String data = "Phung Thanh Do 123456789\n" +
                "111 CHECKING 1000.0\n" +
                "222 SAVINGS 6000.0\n" +
                "Do Mixi 987654321\n" +
                "333 CHECKING 200.0\n";

        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8));
        Bank bank = new Bank();
        bank.readCustomerList(inputStream);

        assertEquals(2, bank.getCustomerList().size());

        Customer c1 = bank.getCustomerList().get(0);
        assertEquals("Phung Thanh Do", c1.getFullName());
        assertEquals(123456789, c1.getIdNumber());
        assertEquals(2, c1.getAccountList().size());
    }

    @Test
    public void testSortingMethods() {
        Bank bank = new Bank();
        bank.getCustomerList().add(new Customer(999999999, "Vu"));
        bank.getCustomerList().add(new Customer(111111111, "Tay"));

        String infoById = bank.getCustomersInfoByIdOrder();
        assertTrue(infoById.startsWith("Số CMND: 111111111"));

        String infoByName = bank.getCustomersInfoByNameOrder();
        assertTrue(infoByName.startsWith("Số CMND: 111111111"));
    }
}