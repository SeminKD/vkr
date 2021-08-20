package vkr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @Column(name = "id_product")
    private int id_product;

    @Column(name = "name_product")
    private String name_product;

    @Column(name = "description_product")
    private String description_product;

    @Column(name = "price_product")
    private String price_product;

    @Column(name = "img_product")
    private String img_product;

    public Product() {
    }

    public String getImg_product() {
        return img_product;
    }

    public void setImg_product(String img_product) {
        this.img_product = img_product;
    }

    public Product(int id_product, String name_product, String description_product,
                   String price_product, String img_product) {
        this.img_product = img_product;
        this.price_product = price_product;
        this.id_product = id_product;
        this.name_product = name_product;
        this.description_product = description_product;
    }

    public String getPrice_product() {
        return price_product;
    }

    public void setPrice_product(String price_product) {
        this.price_product = price_product;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_fuel) {
        this.name_product = name_fuel;
    }

    public String getDescription_product() {
        return description_product;
    }

    public void setDescription_product(String description_fuel) {
        this.description_product = description_fuel;
    }
}
