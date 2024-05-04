package eshopping.demo.order;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import eshopping.demo.OrderProduct.OrderProduct;
import eshopping.demo.orderBill.OrderBill;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ConfirmationResponse {
    private OrderEntity order;
    private List<OrderBill> orderBill;
    private List<OrderProduct> orderProduct;
}
