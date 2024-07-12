package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_authorization_codes")
public class F4AuthorizationCode {

  @Id
  @Column(name = "authorization_code_id", nullable = false, length = 255)
  private String authorizationCodeId;

  @Column(name = "customer_id", length = 100)
  private String customerId;
}
