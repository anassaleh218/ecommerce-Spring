package eshopping.demo.product;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;

import eshopping.demo.config.TripleDesEncryptor;
import eshopping.demo.order.OrderProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name = "product")

public class Prod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Convert(converter = TripleDesEncryptor.class)
    private String name;
    @Convert(converter = TripleDesEncryptor.class)
    private String description;
    @Convert(converter = TripleDesEncryptor.class)
    private int quantity;
    @Convert(converter = TripleDesEncryptor.class)
    private String img;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private Status status;

    // @ManyToMany(mappedBy = "products")
    // private Set<Order> orders = new HashSet<>();

    @OneToMany(mappedBy = "product")
    private Set<OrderProduct> orderProducts = new HashSet<>();


    // private enum Status {
    // available, outofstock
    // };

    // private enum Color {
    // red, green, blue
    // };

    // public Status getStat() {
    // return stat;
    // }
    // public void setStat(Status stat) {
    // this.stat = stat;
    // }

    // public Color getRgb() {
    // return rgb;
    // }
    // public void setRgb(Color rgb) {
    // this.rgb = rgb;
    // }

    // public Prod(int id, String name, String description, int quantity, String
    // img, Status stat, Color rgb) {
    // this.id = id;
    // this.name = name;
    // this.description = description;
    // this.quantity = quantity;
    // this.img = img;
    // this.stat = stat;
    // this.rgb = rgb;
    // }

    // public Prod(String name, String description, int quantity, String img, Status
    // stat, Color rgb) {
    // this.name = name;
    // this.description = description;
    // this.quantity = quantity;
    // this.img = img;
    // this.stat = stat;
    // this.rgb = rgb;
    // }

    // Default constructor
    public Prod() {
    }

    public Prod(String name, String description, int quantity, String img) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.img = img;
    }

    public Prod(int id, String name, String description, int quantity, String img) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.img = img;

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

}
