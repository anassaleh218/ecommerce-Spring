package eshopping.demo.CartProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

import eshopping.demo.cart.Cart;
import jakarta.transaction.Transactional;

public interface CartProductRepository extends JpaRepository<CartProduct, Integer> {
    Optional<CartProduct> findByCart(Cart cart);

    List<CartProduct> findAllByCart(Cart cart);
    // List<CartProduct> findProductIdByCart(Cart cart);

    @Modifying // Indicate a data manipulation query
    @Query("DELETE FROM CartProduct cp WHERE cp.cart.id = :cartId")
    @Transactional  // Add this annotation
    void deleteByCartId(@Param("cartId") Integer cartId);
}
