package eshopping.demo.cart;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

// import eshopping.demo.user.User;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    // Optional<Cart> findByuser(User user);
    Optional<Cart> findByUserId(Integer userId);



}
