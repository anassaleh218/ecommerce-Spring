package eshopping.demo.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer>{
Optional<OrderEntity> findByUserId(Integer userId);

}
