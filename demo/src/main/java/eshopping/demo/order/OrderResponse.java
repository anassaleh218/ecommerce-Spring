package eshopping.demo.order;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import eshopping.demo.OrderProduct.OrderProduct;
import eshopping.demo.user.User;

public class OrderResponse {
    private int id;
    private User user;
    private OrderStatus orderStatus;
    private List<OrderProduct> orderProducts;

    public void setOrderId(int id) {
        this.id = id;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public void setStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
    public void setOrderProducts(List<OrderProduct> orderProducts2) {
        this.orderProducts = orderProducts2;
    }
}
