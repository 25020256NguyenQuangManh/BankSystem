package bank_system;

import java.util.Locale;

/**
 * Ngoại lệ khi số dư tài khoản không đủ để thực hiện giao dịch (rút tiền).
 */
public class InsufficientFundsException extends BankException {

    /**
     * Khởi tạo ngoại lệ.
     *
     * @param amount Số tiền yêu cầu rút gây ra lỗi.
     */
    public InsufficientFundsException(double amount) {
        super(String.format(Locale.US,
                "Số dư tài khoản không đủ $%.2f để thực hiện giao dịch", amount));
    }
}