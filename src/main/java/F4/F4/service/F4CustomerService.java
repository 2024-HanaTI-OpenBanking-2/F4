package F4.F4.service;

import F4.F4.entity.F4Customer;
import F4.F4.repository.F4CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class F4CustomerService {

  @Autowired
  private F4CustomerRepository f4CustomerRepository;

  public F4Customer authenticate(String customerId, String customerPassword) {
    return f4CustomerRepository.findByCustomerIdAndCustomerPassword(customerId, customerPassword);
  }
}
