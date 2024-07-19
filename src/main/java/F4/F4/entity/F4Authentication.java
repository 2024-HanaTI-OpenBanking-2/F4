package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "f4_authentications")
public class F4Authentication {

  @Id
  @Column(name = "access_token_id", nullable = false, length = 255)
  private String accessTokenId;

  @Column(name = "expires_in")
  private Integer expiresIn;

  @Column(name = "refresh_token", length = 255)
  private String refreshToken;

  @Column(name = "auth_scope", length = 255)
  private String authScope;

  @Column(name = "user_seq_no", length = 255)
  private String userSeqNo;

  @Column(name = "created_at")
  private Timestamp createdAt;

  @Column(name = "updated_at")
  private Timestamp updatedAt;
}
