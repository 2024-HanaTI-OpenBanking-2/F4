package F4.F4.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_accounts")
public class F4Account {
  @Id
  @Column(name = "account_num", nullable = false, length = 100)
  private String accountNum;

  @Column(name = "customer_id", nullable = false, length = 100)
  private String customerId;

  @Column(name = "account_name", length = 255)
  private String accountName;

  @Column(name = "customer_password", length = 255)
  private String customerPassword;

  @Column(name = "balance")
  private BigDecimal balance;

  @Column(name = "account_issue_date")
  private Date accountIssueDate;

  @Column(name = "account_status", length = 255)
  private String accountStatus;

  @Column(name = "bank_code_std", length = 255)
  private String bankCodeStd;

  @Column(name = "activity_type", length = 10)
  private String activityType;

  @Column(name = "account_type", length = 100)
  private String accountType;

  @Column(name = "maturity_date", length = 100)
  private String maturityDate;

  @Column(name = "product_id", length = 100)
  private Integer productId;

  @Column(name = "dormancy_yn", length = 10)
  private String dormancyYn;

  @Column(name = "balance_amt", length = 255)
  private String balanceAmt;
}
