package eshopping.demo.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

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

    // private enum Status {
    // available, outofstock
    // };
    // private enum Color {
    // red, green, blue
    // };

    // private Status stat;
    // private Color rgb;

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
