package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_hana_accounts")
public class F4HanaAccount {

  @Id
  @Column(name = "account_id", nullable = false, length = 50)
  private String accountId;

  @Column(name = "customer_id", nullable = false, length = 100)
  private String customerId;

  @Column(name = "financial_company", length = 30)
  private String financialCompany;

  @Column(name = "account_number", nullable = false, length = 50)
  private String accountNumber;

  @Column(name = "cash", nullable = false)
  private Integer cash;

  @Column(name = "total_assets", nullable = false)
  private Integer totalAssets;
}
