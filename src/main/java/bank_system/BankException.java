package bank_system;

/**
 * Ngoại lệ chung trong hệ thống ngân hàng.
 */
public class BankException extends Exception {

    /**
     * Khởi tạo ngoại lệ với thông báo lỗi.
     *
     * @param message Thông báo chi tiết về lỗi.
     */
    public BankException(String message) {
        super(message);
    }
}