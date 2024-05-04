package eshopping.demo.cart;


import java.util.HashSet;
import java.util.Set;

import eshopping.demo.CartProduct.CartProduct;
import eshopping.demo.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")

public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    private User user;

// // In Cart class
// @OneToMany(mappedBy = "cartItems") // Update field name if needed
// private Set<CartProduct> cartProducts;

    public int getId() {
        return id;
    }
}
