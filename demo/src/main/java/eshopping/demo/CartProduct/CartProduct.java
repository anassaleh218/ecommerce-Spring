package eshopping.demo.CartProduct;

import java.util.List;

import eshopping.demo.cart.Cart;
import eshopping.demo.product.Prod;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
// import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// @Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart_product")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Prod product;

    private int quantity;

    // // In CartProduct class
    // @OneToMany(mappedBy = "cartProducts") // Update mappedBy value accordingly
    // private List<CartProduct> items;

    // // Getter for items
    // public List<CartProduct> getItems() {
    //     return items;
    // }

}
