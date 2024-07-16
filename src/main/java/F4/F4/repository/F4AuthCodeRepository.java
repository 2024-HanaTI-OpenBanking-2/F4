package F4.F4.repository;

import F4.F4.entity.F4AuthCode;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface F4AuthCodeRepository extends JpaRepository<F4AuthCode, String> {
  Optional<F4AuthCode> findByAccessTokenId(String accessTokenId);
  F4AuthCode findByAuthCodeId(String authCodeId);

}