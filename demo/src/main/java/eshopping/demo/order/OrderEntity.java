package eshopping.demo.order;

import java.util.HashSet;
import java.util.Set;

import eshopping.demo.OrderProduct.OrderProduct;
import eshopping.demo.orderBill.OrderBill;

// import java.util.Set;

import eshopping.demo.user.User;
// import jakarta.persistence.CascadeType;
// import jakarta.persistence.Entity;
// import jakarta.persistence.EnumType;
// import jakarta.persistence.Enumerated;
// import jakarta.persistence.Id;
// import jakarta.persistence.JoinColumn;
// import jakarta.persistence.ManyToMany;
import jakarta.persistence.*;

// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Table;
// import lombok.AllArgsConstructor;
// import lombok.NoArgsConstructor;
// import lombok.Builder;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_entity")

public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order")
    private Set<OrderBill> orderBills = new HashSet<>();

    @OneToMany(mappedBy = "order")
    private Set<OrderProduct> orderProducts = new HashSet<>();



}
