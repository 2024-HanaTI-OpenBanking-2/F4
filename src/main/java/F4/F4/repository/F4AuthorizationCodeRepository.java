package F4.F4.repository;

import F4.F4.entity.F4AuthorizationCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface F4AuthorizationCodeRepository extends JpaRepository<F4AuthorizationCode, String> {

}
