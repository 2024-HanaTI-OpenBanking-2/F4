package F4.F4.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

@Data
@Entity
@Table(name = "f4_customers")
public class F4Customer {

  @Id
  @Column(name = "customer_id", nullable = false, length = 100)
  private String customerId;

  @Column(name = "customer_password", length = 100)
  private String customerPassword;

  @Column(name = "name", length = 100)
  private String name;

  @Column(name = "phone", length = 100)
  private String phone;

  @Column(name = "address", length = 100)
  private String address;

  @Column(name = "gender_code", length = 10)
  private String genderCode;

  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "user_type", length = 20)
  private String userType;

  @Column(name = "registration_date")
  private Date registrationDate;

  @Column(name = "access_token_id", length = 255)
  private String accessTokenId;
}
