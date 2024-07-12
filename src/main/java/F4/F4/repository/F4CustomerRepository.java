package F4.F4.repository;

import F4.F4.entity.F4Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface F4CustomerRepository extends JpaRepository<F4Customer, String> {
  F4Customer findByCustomerIdAndCustomerPassword(String customerId, String customerPassword);
}
