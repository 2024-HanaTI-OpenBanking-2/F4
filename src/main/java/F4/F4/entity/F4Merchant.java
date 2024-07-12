package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_merchants")
public class F4Merchant {

  @Id
  @Column(name = "merchant_id", nullable = false, length = 255)
  private String merchantId;

  @Column(name = "merchant_name", nullable = false, length = 200)
  private String merchantName;

  @Column(name = "merchant_address", length = 200)
  private String merchantAddress;

  @Column(name = "merchant_phone", length = 20)
  private String merchantPhone;

  @Column(name = "latitude", precision = 5)
  private Double latitude;

  @Column(name = "longitude", precision = 5)
  private Double longitude;

  @Column(name = "category_code", length = 20)
  private String categoryCode;
}
