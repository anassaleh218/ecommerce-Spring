package eshopping.demo.order;

import org.springframework.web.bind.annotation.RestController;

import eshopping.demo.CartProduct.CartProduct;
import eshopping.demo.CartProduct.CartProductRepository;
import eshopping.demo.OrderProduct.OrderProduct;
import eshopping.demo.OrderProduct.OrderProductRepository;
import eshopping.demo.auth.AuthenticationResponse;
import eshopping.demo.auth.AuthorizationAspect;
import eshopping.demo.cart.Cart;
import eshopping.demo.cart.CartRepository;
import eshopping.demo.config.JwtService;
import eshopping.demo.orderBill.OrderBill;
import eshopping.demo.orderBill.OrderBillRepository;

import eshopping.demo.user.User;
import eshopping.demo.user.UserRepository;
import lombok.AllArgsConstructor;

import java.sql.Timestamp;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@AllArgsConstructor
@RestController
@RequestMapping("order")
public class OrderController {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OrderBillRepository orderBillRepository;
    private final OrderProductRepository orderProductRepository;
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    private AuthorizationAspect authorizationAspect;
    @PostMapping("add")
    public ResponseEntity<String> newOrder(@RequestHeader("Authorization") String authHeader) {
        User user = authorizationAspect.user;
        Integer userId = user.getId();

        OrderEntity newOrder = OrderEntity.builder()
                .user(user)
                .orderStatus(OrderStatus.UnderReview) // Assuming OrderStatus enum
                .build();

        orderRepository.save(newOrder);

        // save order for user - table: order entity
        ////////////////////////////////////////////////
        // save order products for user - table: order products

        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);

        if (!cartOptional.isPresent()) {
            // Handle empty cart scenario (e.g., return empty response)
            return new ResponseEntity<>("Cart is empty. Cannot create order.", HttpStatus.BAD_REQUEST);
        }

        Cart cart = cartOptional.get();
        Integer cartId = cart.getId();

        // Optional<CartProduct> cartProductOptional =
        // cartProductRepository.findByCart(cart);

        List<CartProduct> cartProducts = cartProductRepository.findAllByCart(cart); // Assuming Cart has a getItems()
                                                                                    // method

        // List<CartProduct> cartProducts = cart.getItems(); // Assuming Cart has a
        // getItems() method

        List<OrderProduct> orderProducts = new ArrayList<>();
        for (CartProduct cartProduct : cartProducts) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setOrder(newOrder);
            orderProduct.setProduct(cartProduct.getProduct()); // Assuming Product object
            orderProduct.setQuantity(cartProduct.getQuantity());

            try {
                orderProductRepository.save(orderProduct);
            } catch (DataAccessException e) {
                // Handle order product saving error appropriately
            }
            orderProducts.add(orderProduct);
        }
        cartProductRepository.deleteByCartId(cartId); // Assuming a method to clear cart

        // // from here not working and response 403 but it working
        // OrderResponse response = new OrderResponse();
        // response.setOrderId(newOrder.getId());
        // response.setUser(user);
        // response.setStatus(newOrder.getOrderStatus());
        // response.setOrderProducts(orderProducts);

        return new ResponseEntity<>("successfully", HttpStatus.CREATED);
    }

    
    @PostMapping("bill")
    public OrderBill saveOrder(@RequestParam Map<String, String> request,
    @RequestHeader("Authorization") String authHeader) throws IOException {
        User user = authorizationAspect.user;
        Integer userId = user.getId();

        Optional<OrderEntity> orderOptional = orderRepository.findTopByUserIdOrderByIdDesc(userId);
        System.err.println(orderOptional.get().getId());
        OrderEntity order = orderOptional.get();

        int flatNo = Integer.parseInt((String) request.get("flatNo")); // Parse string to int
        int buildingNo = Integer.parseInt((String) request.get("buildingNo"));
        String street = (String) request.get("street");
        String city = (String) request.get("city");
        String country = (String) request.get("country");
        String phone = (String) request.get("phone");
        String email = (String) request.get("email");
        String creditCardHolderName = (String) request.get("creditCardHolderName");
        String creditCardType = (String) request.get("creditCardType");
        Long creditCardNumber = Long.parseLong((String) request.get("creditCardNumber"));
        int expMonth = Integer.parseInt((String) request.get("expMonth"));
        int expYear = Integer.parseInt((String) request.get("expYear"));
        int cvv = Integer.parseInt((String) request.get("cvv"));

        // remaining: timestamp
        LocalDateTime now = LocalDateTime.now();
        Timestamp createdAt = Timestamp.valueOf(now);


        OrderBill orderBill = new OrderBill(flatNo, buildingNo, street, city, country, phone, email,
                creditCardHolderName, creditCardType, creditCardNumber, expMonth, expYear, cvv, user, order, createdAt);

                OrderBill savedOrder = orderBillRepository.save(orderBill);

                // You can now return the savedOrder object or perform further actions
                return savedOrder;
    }

    @GetMapping("confirmation/{order}")
    public ConfirmationResponse getMethodName(@PathVariable Integer order) {
        OrderEntity myOrder = orderRepository.findById(order).get();
        
        return ConfirmationResponse.builder()
        .order(myOrder)
        .orderProduct(orderProductRepository.findByOrderId(myOrder.getId()))
        .orderBill(orderBillRepository.findByOrderId(myOrder.getId()))
        .build();
    }

    @GetMapping("my")
    public List<OrderEntity> getMyOrders(@RequestHeader("Authorization") String authHeader) {
        User user = authorizationAspect.user;        
        return orderRepository.findAllByUserId(user.getId());
    }
    

}
