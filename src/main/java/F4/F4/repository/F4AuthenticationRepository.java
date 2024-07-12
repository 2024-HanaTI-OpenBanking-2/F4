package F4.F4.repository;

import F4.F4.entity.F4Authentication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface F4AuthenticationRepository extends
    JpaRepository<F4Authentication, String> {

}
