package F4.F4.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_customer_approvals")
public class F4CustomerApproval {

  @Id
  @Column(name = "approval_number", nullable = false, length = 255)
  private String approvalNumber;

  @Column(name = "approval_date")
  private Timestamp approvalDate;

  @Column(name = "approval_amount")
  private BigDecimal approvalAmount;

  @Column(name = "merchant_id", nullable = false, length = 255)
  private String merchantId;

  @Column(name = "benefit_amount")
  private BigDecimal benefitAmount;

  @Column(name = "approval_status_code", length = 255)
  private String approvalStatusCode;

  @Column(name = "payment_category", length = 255)
  private String paymentCategory;

  @Column(name = "customer_card_id", nullable = false, length = 255)
  private String customerCardId;
}
