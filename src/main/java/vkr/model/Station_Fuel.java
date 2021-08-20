package vkr.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "station_fuel")
public class Station_Fuel implements Serializable {

    @Id
    @JoinColumn(name = "id_station")
    @ManyToOne(cascade = CascadeType.ALL)
    private Station station;

    @Id
    @JoinColumn(name = "id_fuel")
    @ManyToOne(cascade = CascadeType.ALL)
    private Fuel fuel;

    public Station_Fuel() {
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }
}
