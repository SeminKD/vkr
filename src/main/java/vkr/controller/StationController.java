package vkr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vkr.dao.MapDao;
import vkr.model.Fuel;
import vkr.model.Product;
import vkr.model.Service;
import vkr.model.Station;
import vkr.service.Util;

import java.util.ArrayList;

@Controller
public class StationController {

    @GetMapping("/admin-stations")
    public String showStations(Model model) {
        ArrayList<Station> stations = MapDao.getAllStations();
        model.addAttribute("stations", stations);
        return "admin-stations";
    }

    @GetMapping("/station-edit")
    public String getStationEditPage(@RequestParam(value = "id") int id,
                                     Model model) {
        Station station = MapDao.getEditingStation(id);
        model.addAttribute("station", station);
        return "edit-station";
    }

    @PostMapping("/station-edit")
    public String editStation(@RequestParam(value = "id") int id,
                              @RequestParam(value = "address") String address) {
        MapDao.updateStation(id, address);
        return "redirect:/station-edit?id=" + id;
    }

    @GetMapping("/add-station")
    public String addStationShowPage() {
        return "add-station";
    }

    @PostMapping("/add-station")
    public String addStation(@RequestParam(value = "address") String address) {
        MapDao.addStation(address);
        return "redirect:/admin-stations";
    }

    @GetMapping("/station-delete")
    public String deleteStation(@RequestParam(value = "id") int id) {
        MapDao.deleteStation(id);
        return "redirect:/admin-stations";
    }

    @GetMapping("station-editFPS")
    public String getFPSPage(@RequestParam(value = "id") int id,
                             Model model) {
        ArrayList<Fuel> allFuel = MapDao.getAllFuel();
        model.addAttribute("fuelList", allFuel);
        ArrayList<Service> allService = MapDao.getAllService();
        model.addAttribute("serviceList", allService);
        ArrayList<Product> allProduct = MapDao.getAllProduct();
        model.addAttribute("productList", allProduct);
        Station station = MapDao.getEditingStation(id);
        model.addAttribute("station", station);
        return "stationFPS";
    }
}
