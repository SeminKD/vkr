package vkr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vkr.dao.MapDao;
import vkr.model.Fuel;
import vkr.model.Product;
import vkr.model.Service;
import vkr.service.Util;

import java.util.ArrayList;

@Controller
public class MapController {

    @GetMapping("/map")
    public String showMap(Model model) {
        ArrayList<Fuel> allFuel = MapDao.getAllFuel();
        model.addAttribute("fuelList", allFuel);
        ArrayList<Service> allService = MapDao.getAllService();
        model.addAttribute("serviceList", allService);
        ArrayList<Product> allProduct = MapDao.getAllProduct();
        model.addAttribute("productList", allProduct);
        return "map";
    }
}
