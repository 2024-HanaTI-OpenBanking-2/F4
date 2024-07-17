package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_statements")
public class F4Statement {

  @Id
  @Column(name = "transaction_no", nullable = false, length = 255)
  private String transactionNo;

  @Column(name = "account_no", nullable = false, length = 255)
  private String accountNo;

  @Column(name = "amount")
  private Integer amount;

  @Column(name = "transaction_date")
  private Timestamp transactionDate;

  @Column(name = "transaction_type", length = 255)
  private String transactionType;

}
