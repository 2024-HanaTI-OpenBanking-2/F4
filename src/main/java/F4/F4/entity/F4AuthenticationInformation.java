package F4.F4.entity;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "f4_authentication_informations")
public class F4AuthenticationInformation {

  @Id
  @Column(name = "client_id", nullable = false, length = 255)
  private String clientId;

  @Column(name = "client_secret", length = 255)
  private String clientSecret;

  @Column(name = "client_name", length = 255)
  private String clientName;
}
