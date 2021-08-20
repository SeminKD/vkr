package vkr.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "station_product")
public class Station_Product implements Serializable {

    @Id
    @JoinColumn(name = "id_station")
    @ManyToOne(cascade = CascadeType.ALL)
    private Station station;

    @Id
    @JoinColumn(name = "id_product")
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public Station_Product() {
    }

    public Station_Product(Station station, Product product) {
        this.station = station;
        this.product = product;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
