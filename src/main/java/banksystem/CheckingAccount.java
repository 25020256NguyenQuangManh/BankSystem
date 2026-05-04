package banksystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản vãng lai.
 */
public class CheckingAccount extends Account {
  private static final Logger logger = LoggerFactory.getLogger(CheckingAccount.class);

  public CheckingAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    logger.debug("Bắt đầu giao dịch nạp tiền tài khoản vãng lai: {}", getAccountNumber());
    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      logger.info("Nạp tiền tài khoản vãng lai {} thành công: +{}", getAccountNumber(), amount);
    } catch (BankException e) {
      logger.error("Lỗi nạp tiền tài khoản vãng lai {}: {}", getAccountNumber(), e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    logger.debug("Bắt đầu giao dịch rút tiền tài khoản vãng lai: {}", getAccountNumber());
    double initialBalance = getBalance();
    try {
      doWithdrawing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      logger.info("Rút tiền tài khoản vãng lai {} thành công: -{}", getAccountNumber(), amount);
    } catch (BankException e) {
      logger.error("Lỗi rút tiền tài khoản vãng lai {}: {}", getAccountNumber(), e.getMessage());
    }
  }
}