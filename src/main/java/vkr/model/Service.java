package vkr.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "service")
public class Service implements Serializable {

    @Id
    @Column(name = "id_service")
    private int id_service;

    @Column(name = "name_service")
    private String name_service;

    @Column(name = "description_service")
    private String description_service;

    @Column(name = "price_service")
    private String price_service;

    @Column(name = "img_service")
    private String img_service;

    public Service() {
    }

    public String getImg_service() {
        return img_service;
    }

    public void setImg_service(String img_service) {
        this.img_service = img_service;
    }

    public String getPrice_service() {
        return price_service;
    }

    public void setPrice_service(String price_service) {
        this.price_service = price_service;
    }

    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public String getName_service() {
        return name_service;
    }

    public void setName_service(String name_service) {
        this.name_service = name_service;
    }

    public String getDescription_service() {
        return description_service;
    }

    public void setDescription_service(String description_service) {
        this.description_service = description_service;
    }
}
