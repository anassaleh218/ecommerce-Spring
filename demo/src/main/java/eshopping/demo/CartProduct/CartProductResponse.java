package eshopping.demo.CartProduct;

import eshopping.demo.product.Prod;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
public class CartProductResponse {
    private Prod product;
    private int quantity;
    public Prod getProduct() {
        return product;
    }
    public int getQuantity() {
        return quantity;
    }


}
