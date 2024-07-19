package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_insurance_products")
public class F4InsuranceProduct {

  @Id
  @Column(name = "insurance_id", nullable = false, length = 255)
  private String insuranceId;

  @Column(name = "insurance_name", nullable = false, length = 255)
  private String insuranceName;

  @Column(name = "total_fee", nullable = false)
  private Integer totalFee;

  @Column(name = "insurance_type", nullable = false, length = 255)
  private String insuranceType;
}
