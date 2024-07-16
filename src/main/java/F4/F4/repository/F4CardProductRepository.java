package F4.F4.repository;

import F4.F4.entity.F4CardProduct;
import F4.F4.entity.F4CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface F4CardProductRepository extends JpaRepository<F4CardProduct, String> {
}
