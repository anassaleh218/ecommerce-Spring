package eshopping.demo.product;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import eshopping.demo.image.ImageService;

@RestController
@RequestMapping("prod")
//localhost:8081/prod
public class prodController {
    
    @Autowired
    private ImageService imageService;

    @Autowired
    private prodService prodService;  //bean

    @GetMapping("")
    //localhost:8081/prod
    public List<Prod> getProd(){
        return prodService.getProd();
    }

    @PostMapping("create")
    //localhost:8080/prod/create
    
    public String saveProd(@RequestParam("file") MultipartFile file, @RequestParam Map<String, String> request) throws IOException {
    // public String saveProd(@RequestPart("data") Map<String, String> request, @RequestPart("file") MultipartFile file) throws IOException {
            String name = (String) request.get("name");
        String description = (String) request.get("description");
        int quantity = Integer.parseInt((String) request.get("quantity")); // Parse string to int
        // String img = (String) request.get("img");
        String img = (String) imageService.saveImage(file);

        Prod prodObj = new Prod(name, description, quantity, img);
        // Prod prodObj = new Prod( request.get("name"), request.get("description"), request.get("quantity"), request.get("img"));
        prodService.addProd(prodObj);
        return "done,prod saved successfully";
    }

    @PutMapping("/{id}/update") 
    //localhost:8080/prod/2/update
    public Prod updateTopic(@PathVariable Integer id, @RequestBody Map <String, String> request) {
        String name = (String) request.get("name");
        String description = (String) request.get("description");
        int quantity = Integer.parseInt((String) request.get("quantity")); // Parse string to int
        String img = (String) request.get("img");

        Prod prodObj = new Prod(id,name, description, quantity, img);
        // Prod topicObj = new Prod( id, request.get("name"), request.get("description"));
        Prod result = prodService.updateProd(prodObj);
        return result;
    }


    @DeleteMapping("{id}/delete")
    public String deleteProd(@PathVariable Integer id){
        prodService.deleteProd(id);
        return "Deleted successfully";
    }


    @GetMapping("{id}/view")
    public Prod findProd(@PathVariable Integer id) {
        return prodService.findProd(id);
    }
    
}