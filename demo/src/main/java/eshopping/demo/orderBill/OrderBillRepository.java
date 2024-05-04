package eshopping.demo.orderBill;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import eshopping.demo.OrderProduct.OrderProduct;

public interface OrderBillRepository extends JpaRepository <OrderBill,Integer> {
    List<OrderBill> findByOrderId(int orderId);
}
