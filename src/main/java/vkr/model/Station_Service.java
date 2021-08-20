package vkr.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "station_service")
public class Station_Service implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_station")
    private Station station;

    @Id
    @JoinColumn(name = "id_service")
    @ManyToOne(cascade = CascadeType.ALL)
    private Service service;

    public Station_Service() {
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
}
