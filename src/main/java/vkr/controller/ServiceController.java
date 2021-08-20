package vkr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vkr.dao.FuelDao;
import vkr.dao.ProductsDao;
import vkr.dao.ServiceDao;
import vkr.model.Product;
import vkr.model.Service;
import vkr.model.dto.FuelNamePriceDto;
import vkr.service.Util;

import java.util.ArrayList;

@Controller
public class ServiceController {

    @GetMapping("/service")
    public String showService(Model model){
        ArrayList<FuelNamePriceDto> fuel = FuelDao.getFuelPrices();
        model.addAttribute("fuel", fuel);
        ArrayList<Service> service = ServiceDao.getServiceWithRub();
        model.addAttribute("service", service);
        return "service";
    }

    @GetMapping("/admin-service")
    public String showAdminService(Model model){
        ArrayList<Service> service = ServiceDao.getService();
        model.addAttribute("service", service);
        return "admin-service";
    }

    @GetMapping("/service-delete")
    public String deleteService(@RequestParam(value = "id") int id){
        ServiceDao.deleteService(id);
        return "redirect:/admin-service";
    }

    @GetMapping("/service-edit")
    public String getEditServicePage(@RequestParam(value = "id") int id,
                                     Model model){
        Service service = ServiceDao.getEditingService(id);
        model.addAttribute("service", service);
        ArrayList<String> images = Util.getFiles("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\static\\media\\db\\service", "../media/db/service/",service.getImg_service());
        model.addAttribute("images", images);
        return "edit-service";
    }

    @PostMapping("/service-edit")
    public String updateService(@RequestParam(value = "id") int id,
                                @RequestParam(value = "name") String name,
                                @RequestParam(value = "desc") String desc,
                                @RequestParam(value = "price") String price,
                                @RequestParam(value = "img") String img){
        ServiceDao.updateService(id,name,desc,Double.parseDouble(price),img);
        return "redirect:/service-edit?id="+id;
    }

    @GetMapping("/add-service")
    public String addService(Model model){
        ArrayList<String> images = Util.getFiles("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\static\\media\\db\\service", "../media/db/service/","");
        model.addAttribute("images", images);
        return "add-service";}

    @PostMapping("/add-service")
    public String addNewService(@RequestParam(value = "name") String name,
                                @RequestParam(value = "desc") String desc,
                                @RequestParam(value = "price") String price,
                                @RequestParam(value = "img") String img){
        ServiceDao.addService(name, desc, Double.parseDouble(price), img);
        return "redirect:/admin-service";
    }
}
