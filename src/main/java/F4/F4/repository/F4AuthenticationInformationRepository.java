package F4.F4.repository;

import F4.F4.entity.F4AuthenticationInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface F4AuthenticationInformationRepository extends
    JpaRepository<F4AuthenticationInformation, String> {

}
