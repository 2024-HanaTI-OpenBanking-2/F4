package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_customer_insurances")
public class F4CustomerInsurance {

  @Id
  @Column(name = "contract_id", nullable = false, length = 255)
  private String contractId;

  @Column(name = "customer_id", nullable = false, length = 100)
  private String customerId;

  @Column(name = "insurance_id", nullable = false, length = 255)
  private String insuranceId;

  @Column(name = "special_contract_status", length = 255)
  private String specialContractStatus;

  @Column(name = "subscription_date")
  private Timestamp subscriptionDate;

  @Column(name = "end_date")
  private Timestamp endDate;

  @Column(name = "total_fee")
  private Integer totalFee;

  @Column(name = "current_fee")
  private Integer currentFee;

  @Column(name = "insurance_status", length = 255)
  private String insuranceStatus;
}
