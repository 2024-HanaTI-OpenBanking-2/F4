package F4.F4.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_statements")
public class F4Statement {

  @Id
  @Column(name = "transaction_no", nullable = false, length = 255)
  private String transactionNo;

  @Column(name = "account_num", nullable = false, length = 100)
  private String accountNum;

  @Column(name = "amount")
  private BigDecimal amount;

  @Column(name = "tran_date", length = 100)
  private String tranDate;

  @Column(name = "transaction_type", length = 255)
  private String transactionType;

  @Column(name = "tran_time", length = 100)
  private String tranTime;

  @Column(name = "inout_type", length = 20)
  private String inoutType;

  @Column(name = "print_content", length = 255)
  private String printContent;

  @Column(name = "tran_amt", length = 255)
  private String tranAmt;

  @Column(name = "after_balance_amt", length = 255)
  private String afterBalanceAmt;

  @Column(name = "branch_name", length = 100)
  private String branchName;
}
