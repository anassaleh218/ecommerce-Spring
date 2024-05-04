package eshopping.demo.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    Optional<OrderEntity> findTopByUserIdOrderByIdDesc(Integer userId);
    List<OrderEntity> findAllByUserId(Integer userId);
}
