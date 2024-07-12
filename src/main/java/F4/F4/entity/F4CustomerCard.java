package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity
@Table(name = "f4_customer_cards")
public class F4CustomerCard {

  @Id
  @Column(name = "customer_card_id", nullable = false, length = 255)
  private String customerCardId1;

  @Column(name = "expiration_date")
  private Date expirationDate;

  @Column(name = "customer_id", nullable = false, length = 100)
  private String customerId;

  @Column(name = "last_month_performance")
  private Integer lastMonthPerformance;

  @Column(name = "customer_performance_segment", length = 255)
  private String customerPerformanceSegment;

  @Column(name = "card_type_code", length = 255)
  private String cardTypeCode;

  @Column(name = "card_status_code", length = 255)
  private String cardStatusCode;

  @Column(name = "card_product_id", nullable = false, length = 255)
  private String cardProductId;
}
