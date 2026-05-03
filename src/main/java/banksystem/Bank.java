package banksystem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho ngân hàng, quản lý danh sách khách hàng.
 */
public class Bank {
  private static final Logger logger = LoggerFactory.getLogger(Bank.class);

  private List<Customer> customerList;

  public Bank() {
    this.customerList = new ArrayList<>();
  }

  public List<Customer> getCustomerList() {
    return customerList;
  }

  /**a.*/
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customerList = new ArrayList<>();
    } else {
      this.customerList = customerList;
    }
  }

  /**
   * Đọc danh sách khách hàng và tài khoản từ luồng đầu vào.
   *
   * @param inputStream Luồng dữ liệu đầu vào.
   */
  public void readCustomerList(InputStream inputStream) {
    logger.debug("Bắt đầu đọc dữ liệu khách hàng từ InputStream...");
    if (inputStream == null) {
      logger.warn("InputStream rỗng, hủy bỏ thao tác đọc.");
      return;
    }

    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      String line;
      Customer currentCustomer = null;

      while ((line = reader.readLine()) != null) {
        line = line.trim();
        if (line.isEmpty()) {
          continue;
        }

        int lastSpaceIndex = line.lastIndexOf(' ');
        if (lastSpaceIndex > 0) {
          String token = line.substring(lastSpaceIndex + 1).trim();
          // Nhận diện dòng chứa thông tin khách hàng (CMND 9 số)
          if (token.matches("\\d{9}")) {
            String name = line.substring(0, lastSpaceIndex).trim();
            currentCustomer = new Customer(Long.parseLong(token), name);
            customerList.add(currentCustomer);
            logger.info("Đã thêm khách hàng mới: {}", name);
          } else if (currentCustomer != null) {
            // Nhận diện dòng chứa thông tin tài khoản
            parseAccountAndAddToCustomer(line, currentCustomer);
          }
        }
      }
    } catch (Exception e) {
      logger.error("Lỗi khi đọc danh sách khách hàng: {}", e.getMessage(), e);
    }
  }

  /**
   * Hàm hỗ trợ phân tích chuỗi văn bản thành đối tượng tài khoản.
   */
  private void parseAccountAndAddToCustomer(String line, Customer currentCustomer) {
    String[] parts = line.split("\\s+");
    if (parts.length >= 3) {
      try {
        long accountNumber = Long.parseLong(parts[0]);
        double balance = Double.parseDouble(parts[2]);

        if (Account.CHECKING_TYPE.equals(parts[1])) {
          currentCustomer.addAccount(new CheckingAccount(accountNumber, balance));
        } else if (Account.SAVINGS_TYPE.equals(parts[1])) {
          currentCustomer.addAccount(new SavingsAccount(accountNumber, balance));
        }
      } catch (NumberFormatException e) {
        logger.error("Lỗi parse số liệu tài khoản từ chuỗi: {}", line, e);
      }
    }
  }

  /**a.*/
  public String getCustomersInfoByIdOrder() {
    List<Customer> sortedList = new ArrayList<>(customerList);
    sortedList.sort(Comparator.comparingLong(Customer::getIdNumber));

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < sortedList.size(); i++) {
      result.append(sortedList.get(i).getCustomerInfo());
      if (i < sortedList.size() - 1) {
        result.append("\n");
      }
    }
    return result.toString();
  }

  /**a.*/
  public String getCustomersInfoByNameOrder() {
    List<Customer> sortedList = new ArrayList<>(customerList);
    sortedList.sort((c1, c2) -> {
      int nameComparison = c1.getFullName().compareTo(c2.getFullName());
      if (nameComparison != 0) {
        return nameComparison;
      }
      return Long.compare(c1.getIdNumber(), c2.getIdNumber());
    });

    StringBuilder result = new StringBuilder();
    for (int i = 0; i < sortedList.size(); i++) {
      result.append(sortedList.get(i).getCustomerInfo());
      if (i < sortedList.size() - 1) {
        result.append("\n");
      }
    }
    return result.toString();
  }
  /**
   * Lấy đường dẫn lưu file xuất dữ liệu (Cố tình làm sai chuẩn).
   *
   * @param fileName Tên file cần xuất.
   * @return Đường dẫn file.
   */
  public String getExportFilePath(String fileName) {
    // CỐ TÌNH LỖI: Dùng dấu gạch chéo ngược cứng (\) đặc trưng của Windows
    return "export_dir\\" + fileName;
  }
}