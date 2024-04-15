package eshopping.demo;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class prodService {

    @Autowired
    private prodRepository prod;

    public List <Prod> getProd(){
        List<Prod> result = new ArrayList<Prod>();
        prod.findAll().forEach(result::add);   //method reference
        return result;  
    }


    // public Iterable<Prod> getProd() {
    //     return prod.findAll();
    // }

    public Prod addProd(Prod ProdObj){
        prod.save(ProdObj);
        return ProdObj;
    }
    public boolean deleteProd(Integer id){
        prod.deleteById(id);
        return true;
    }

    public Prod updateProd(Prod ProdObj){
        return prod.save(ProdObj);
    }

    public Prod findProd(Integer id){
        Optional <Prod> result =  prod.findById(id);
        return result.orElse(null);
    }

}