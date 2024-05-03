package eshopping.demo.product;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface prodRepository extends CrudRepository <Prod,Integer> {

    List<Prod> findByCategory(Category category);
    
    default String findAttributesById(int id) {
        Optional<Prod> optionalEntity = findById(id);
        if (optionalEntity.isPresent()) {
            Prod entity = optionalEntity.get();
            return "name: " + entity.getName() + ", description: " + entity.getDescription() + ", quantity: " + entity.getQuantity() +
                    ", img: " + entity.getImg() + ", price: " + entity.getPrice() + ", category: " + entity.getCategory() +
                    ", status: " + entity.getStatus() + ", color: " + entity.getColor() + ", size: " + entity.getSize();
        } else {
            return "Entity not found";
        }
    }
    
}


