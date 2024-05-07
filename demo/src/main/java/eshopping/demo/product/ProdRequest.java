package eshopping.demo.product;
import lombok.Data;
import lombok.Builder;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdRequest {
    @NotBlank(message = "Please provide a name")
    private String name;

    @NotBlank(message = "Please provide a category")
    private String category;

    @NotBlank(message = "Please provide a size")
    private String size;

    @NotBlank(message = "Please provide a color")
    private String color;

    @NotBlank(message = "Please provide a price")
    private String price;


    // @NotBlank(message = "Please provide a Available")
    // private Status status;

    @NotBlank(message = "Please provide a description")
    private String description;

    @NotBlank(message = "Please provide a quantity")
    private String quantity; // Parse string to int


    private MultipartFile file;
}
