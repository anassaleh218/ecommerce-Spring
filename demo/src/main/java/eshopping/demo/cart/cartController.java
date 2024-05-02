package eshopping.demo.cart;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eshopping.demo.CartProduct.CartProduct;
import eshopping.demo.product.Color;
import eshopping.demo.product.Prod;
import eshopping.demo.product.prodRepository;
import io.jsonwebtoken.Claims;
import eshopping.demo.CartProduct.CartProductRepository;

import eshopping.demo.config.JwtService;

import eshopping.demo.user.UserRepository;




@RestController
@RequestMapping("cart")
public class cartController {

    @Autowired
    private prodRepository prodRepository;

    @Autowired
    private cartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;



    private final JwtService jwtService;

    public cartController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // card product
    // need to get cart id by user id
    @PostMapping("add")
    public ResponseEntity<String> addToCart(@RequestBody Map<String, String> request, @RequestHeader("Authorization") String authHeader) {
        

        final String jwt = authHeader.substring(7);
        final Integer userId = jwtService.extractUserId(jwt);
        
        // @RequestParam Integer productId, @RequestParam int quantity,

        Integer productId=Integer.valueOf((String) request.get("productId")) ;
        Integer quantity=Integer.valueOf((String) request.get("quantity")) ;
        
        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        Optional<Prod> productOptional = prodRepository.findById(productId);

        if (cartOptional.isPresent() && productOptional.isPresent()) {
            Cart cart = cartOptional.get();
            Prod product = productOptional.get();

            // Create a new CartProduct entity
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCart(cart);
            cartProduct.setCartProduct(product);
            cartProduct.setQuantity(quantity);

            // Save the CartProduct entity
            cartProductRepository.save(cartProduct);

            return ResponseEntity.ok("{\"message\": \"added successfully\"}");
        } else {
            return ResponseEntity.badRequest().body("{\"message\": \"Invalid cart ID or product ID.\"}");
        }
    }

}
