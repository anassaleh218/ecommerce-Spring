package eshopping.demo.cart;



import eshopping.demo.product.Prod;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private Prod cartProduct;


    private int quantity;


}
