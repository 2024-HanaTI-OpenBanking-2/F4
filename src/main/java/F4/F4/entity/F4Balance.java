package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_balances")
public class F4Balance {

  @Id
  @Column(name = "account_id", nullable = false, length = 50)
  private String accountId;

  @Column(name = "current_quantity", nullable = false)
  private Integer currentQuantity;

  @Column(name = "purchase_price", nullable = false)
  private Integer purchasePrice;

  @Column(name = "fluctuation_rate")
  private Double fluctuationRate;

  @Column(name = "evaluation_profit_loss", nullable = false)
  private Integer evaluationProfitLoss;

  @Column(name = "stock_code", nullable = false, length = 20)
  private String stockCode;
}
