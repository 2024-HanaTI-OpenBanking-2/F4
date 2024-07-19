package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_auth_codes", uniqueConstraints = {
    @UniqueConstraint(columnNames = "auth_code_id"),
    @UniqueConstraint(columnNames = "access_token_id")
})
public class F4AuthCode {

  @Id
  @Column(name = "auth_code_id", nullable = false, length = 255)
  private String authCodeId;

  @Column(name = "state_code", nullable = false, length = 255)
  private String stateCode;

  @Column(name = "api_tran_id", nullable = false, length = 255)
  private String apiTranId;

  @Column(name = "access_token_id", nullable = false, length = 255)
  private String accessTokenId;
}
