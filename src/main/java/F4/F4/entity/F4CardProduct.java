package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_card_products")
public class F4CardProduct {

  @Id
  @Column(name = "card_product_id", nullable = false, length = 255)
  private String cardProductId;

  @Column(name = "card_name", nullable = false, length = 255)
  private String cardName;

  @Column(name = "annual_fee")
  private Integer annualFee;

  @Lob
  @Column(name = "card_image")
  private byte[] cardImage;

  @Column(name = "card_website_link", length = 1000)
  private String cardWebsiteLink;

  @Column(name = "card_description", length = 1000)
  private String cardDescription;
}
