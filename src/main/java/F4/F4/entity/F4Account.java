package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_accounts")
public class F4Account {

  @Id
  @Column(name = "account_no", nullable = false, length = 255)
  private String accountNo;

  @Column(name = "customer_id", nullable = false, length = 100)
  private String customerId;

  @Column(name = "account_name", length = 255)
  private String accountName;

  @Column(name = "customer_password", length = 255)
  private String customerPassword;

  @Column(name = "balance", length = 255)
  private String balance;

  @Column(name = "create_date")
  private Timestamp createDate;

  @Column(name = "account_status", length = 255)
  private String accountStatus;

  @Column(name = "bank_code", length = 255)
  private String bankCode;
}
