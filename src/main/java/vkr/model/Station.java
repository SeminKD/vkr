package vkr.model;

import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "station")
public class Station implements Serializable {

    @Id
    @Column(name = "id_station")
    private int id_station;

    @Column(name = "address_station")
    private  String address_station;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_company")
    private Company company;

    public String getAddress_station() {
        return address_station;
    }

    public void setAddress_station(String address_station) {
        this.address_station = address_station;
    }

    public Station() {
    }

    public int getId_station() {
        return id_station;
    }

    public void setId_station(int id_station) {
        this.id_station = id_station;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
