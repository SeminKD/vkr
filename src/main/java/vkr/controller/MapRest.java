package vkr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vkr.dao.MapDao;
import vkr.model.dto.Addresses;
import vkr.model.dto.FPS;
import vkr.service.Util;

import java.util.ArrayList;

@RestController
public class MapRest {

    @GetMapping("/mapFiltered")
    public Addresses showNewStations(@RequestParam(value = "fuel") String fuel,
                                     @RequestParam(value = "service") String service,
                                     @RequestParam(value = "product") String product) {
        String q = Util.whichStation(fuel, service, product);
        ArrayList<String> list = MapDao.getStations(q);
        Addresses addresses = new Addresses();
        addresses.setAddresses(list);
        return addresses;
    }

    @GetMapping("/getFPS")
    public ArrayList<ArrayList<FPS>> getFPSFuel(@RequestParam(value = "id") int id){
        ArrayList<ArrayList<FPS>> list = new ArrayList<>();
        list.add(MapDao.getFPSFuel(id));
        list.add(MapDao.getFPSProduct(id));
        list.add(MapDao.getFPSService(id));
        return list;
    }

    @PostMapping("/updateFPS")
    public void updateFPS(@RequestParam(value = "id") int id,
                            @RequestParam(value = "fuel") String fuel,
                            @RequestParam(value = "service") String service,
                            @RequestParam(value = "product") String product) {
        MapDao.deleteStationFuel(id);
        MapDao.updateFPSFuel(id, fuel);

        MapDao.deleteStationService(id);
        MapDao.updateFPSService(id, service);

        MapDao.deleteStationProduct(id);
        MapDao.updateFPSProduct(id, product);
    }
}
