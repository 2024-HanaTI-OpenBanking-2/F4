package F4.F4.repository;

import F4.F4.entity.F4AuthCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface F4AuthCodeRepository extends JpaRepository<F4AuthCode, String> {

}
