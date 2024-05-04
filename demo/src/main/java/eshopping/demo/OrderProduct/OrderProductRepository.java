package eshopping.demo.OrderProduct;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
    List<OrderProduct> findByOrderId(int orderId);

}
