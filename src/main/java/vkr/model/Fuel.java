package vkr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "fuel")
public class Fuel implements Serializable {

    @Id
    @Column(name = "id_fuel")
    private int id_fuel;

    @Column(name = "name_fuel")
    private String name_fuel;

    @Column(name = "description_fuel")
    private String description_fuel;

    @Column(name = "price_fuel")
    private String price_fuel;

    @Column(name = "img_fuel")
    private String img_fuel;

    public Fuel() {
    }

    public String getImg_fuel() {
        return img_fuel;
    }

    public void setImg_fuel(String img_fuel) {
        this.img_fuel = img_fuel;
    }

    public String getPrice_fuel() {
        return price_fuel;
    }

    public void setPrice_fuel(String price_fuel) {
        this.price_fuel = price_fuel;
    }

    public int getId_fuel() {
        return id_fuel;
    }

    public void setId_fuel(int id_fuel) {
        this.id_fuel = id_fuel;
    }

    public String getName_fuel() {
        return name_fuel;
    }

    public void setName_fuel(String name_fuel) {
        this.name_fuel = name_fuel;
    }

    public String getDescription_fuel() {
        return description_fuel;
    }

    public void setDescription_fuel(String description_fuel) {
        this.description_fuel = description_fuel;
    }
}
