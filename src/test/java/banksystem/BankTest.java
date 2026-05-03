package banksystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import org.junit.jupiter.api.Test;

/**
 * Lớp kiểm thử cho Bank.
 */
public class BankTest {

    /**
     * Kiểm thử đường dẫn file xem có tương thích hệ điều hành không.
     */
    @Test
    public void testExportFilePath() {
        Bank bank = new Bank();
        String fileName = "data.csv";

        // Đường dẫn chuẩn do Java tự nhận diện theo hệ điều hành đang chạy
        String expectedPath = "export_dir" + File.separator + fileName;

        assertEquals(expectedPath, bank.getExportFilePath(fileName),
                "Đường dẫn file không khớp chuẩn của hệ điều hành!");
    }
}