package bank_system;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Đại diện cho một giao dịch trong hệ thống.
 */
public class Transaction {
    private static final Logger logger = LoggerFactory.getLogger(Transaction.class);

    public static final int TYPE_DEPOSIT_CHECKING = 1;
    public static final int TYPE_WITHDRAW_CHECKING = 2;
    public static final int TYPE_DEPOSIT_SAVINGS = 3;
    public static final int TYPE_WITHDRAW_SAVINGS = 4;

    private int type;
    private double amount;
    private double initialBalance;
    private double finalBalance;

    public Transaction(int type, double amount, double initialBalance, double finalBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        this.finalBalance = finalBalance;
    }

    // (Giữ nguyên các Getters và Setters hợp lệ)

    /**
     * Chuyển đổi mã loại giao dịch thành chuỗi ký tự mô tả.
     *
     * @param type Mã loại giao dịch.
     * @return Chuỗi mô tả loại giao dịch.
     */
    public static String getTypeString(int type) {
        switch (type) {
            case TYPE_DEPOSIT_CHECKING:
                return "Nạp tiền vãng lai";
            case TYPE_WITHDRAW_CHECKING:
                return "Rút tiền vãng lai";
            case TYPE_DEPOSIT_SAVINGS:
                return "Nạp tiền tiết kiệm";
            case TYPE_WITHDRAW_SAVINGS:
                return "Rút tiền tiết kiệm";
            default:
                return "Không rõ";
        }
    }

    public String getTransactionSummary() {
        logger.debug("Tạo bản tóm tắt cho giao dịch loại: {}", this.type);

        return String.format(Locale.US,
                "- Kiểu giao dịch: %s. Số dư ban đầu: $%.2f. Số tiền: $%.2f. Số dư cuối: $%.2f.",
                getTypeString(type), initialBalance, amount, finalBalance);
    }
}