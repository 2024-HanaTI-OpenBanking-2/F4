package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_domestic_stockcodes")
public class F4DomesticStockcode {

  @Id
  @Column(name = "stock_code", nullable = false, length = 20)
  private String stockCode;

  @Column(name = "stock_name", nullable = false, length = 100)
  private String stockName;

  @Column(name = "industry", length = 100)
  private String industry;

  @Column(name = "reference_date", nullable = false)
  private Date referenceDate;

  @Column(name = "issued_shares")
  private Integer issuedShares;

}
