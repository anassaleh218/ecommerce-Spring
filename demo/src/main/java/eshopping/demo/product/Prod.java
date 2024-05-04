package eshopping.demo.product;

import java.util.HashSet;
import java.util.Set;

import eshopping.demo.CartProduct.CartProduct;
import eshopping.demo.OrderProduct.OrderProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")

public class Prod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private int quantity;
    private String img;

    private float price;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Size size;


    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts = new HashSet<>();
    
    @OneToMany(mappedBy = "product")
    private Set<CartProduct> cartProducts = new HashSet<>();


    



    public Prod(String name, String description, int quantity, String img, float price, Category category,
            Status status, Color color, Size size) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
        this.price = price;
        this.category = category;
        this.status = status;
        this.color = color;
        this.size = size;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public Color getColor() {
        return color;
    }



    public Size getSize() {
        return size;
    }



    public void setStatus(Status status) {
        this.status = status;
    }



}
