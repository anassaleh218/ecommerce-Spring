package eshopping.demo.product;

import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface prodRepository extends CrudRepository <Prod,Integer> {
    List<Prod> findByCategory(Category category);
}