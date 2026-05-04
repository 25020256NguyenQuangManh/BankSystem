package banksystem;

import java.util.Locale;

/**
 * Ngoại lệ khi số tiền giao dịch không hợp lệ (ví dụ: số âm).
 */
public class InvalidFundingAmountException extends BankException {

  /**
   * Khởi tạo ngoại lệ.
   *
   * @param amount Số tiền giao dịch không hợp lệ.
   */
  public InvalidFundingAmountException(double amount) {
    super(String.format(Locale.US, "Số tiền không hợp lệ: $%.2f", amount));
  }
}