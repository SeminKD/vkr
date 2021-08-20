package vkr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vkr.dao.FuelDao;
import vkr.dao.ProductsDao;
import vkr.model.Fuel;
import vkr.model.Product;
import vkr.model.dto.FuelNamePriceDto;
import vkr.service.Util;

import java.util.ArrayList;

@Controller
public class ProductsController {

    @GetMapping("/products")
    public String getMenuProducts(){
        return "products";
    }

    @GetMapping("/fuel")
    public String showFuel(Model model){
        ArrayList<FuelNamePriceDto> fuel = FuelDao.getFuelPrices();
        model.addAttribute("fuel", fuel);
        ArrayList<Fuel> specialFuel = FuelDao.getSpecialFuel();
        model.addAttribute("specialFuel", specialFuel);
        return "fuel";
    }

    @GetMapping("/otherProducts")
    public String showOtherProducts(Model model){
        ArrayList<FuelNamePriceDto> fuel = FuelDao.getFuelPrices();
        model.addAttribute("fuel", fuel);
        ArrayList<Product> otherProd = ProductsDao.getProductsWithRub();
        model.addAttribute("otherProd", otherProd);
        return "otherProducts";
    }

    @GetMapping("/admin-products")
    public String showAdminProducts(){
        return "admin-products";
    }

    @GetMapping("/admin-fuel")
    public String showAdminFuel(Model model){
        ArrayList<Fuel> fuel = FuelDao.getFuel();
        model.addAttribute("fuel", fuel);
        return "admin-fuel";
    }

    @GetMapping("/fuel-delete")
    public String deleteFuel(@RequestParam(value = "id") int id){
        FuelDao.deleteFuel(id);
        return "redirect:/admin-fuel";
    }

    @GetMapping("/fuel-edit")
    public String getEditFuelPage(@RequestParam(value = "id") int id,
                           Model model){
        Fuel fuel = FuelDao.getEditingFuel(id);
        model.addAttribute("fuel", fuel);
        ArrayList<String> images = Util.getFiles("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\static\\media\\db\\fuel", "../media/db/fuel/",fuel.getImg_fuel());
        model.addAttribute("images", images);
        return "edit-fuel";
    }

    @PostMapping("/fuel-edit")
    public String updateFuel(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "desc") String desc,
                             @RequestParam(value = "price") String price,
                             @RequestParam(value = "img") String img){
        if(desc.equals(""))
            desc=null;
        if(img.equals(""))
            img=null;
        FuelDao.updateFuel(id,name,desc,Double.parseDouble(price),img);
        return "redirect:/fuel-edit?id="+id;
    }

    @GetMapping("/add-fuel")
    public String addFuel(Model model){
        ArrayList<String> images = Util.getFiles("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\static\\media\\db\\fuel", "../media/db/fuel/","");
        model.addAttribute("images", images);
        return "add-fuel";}

    @PostMapping("/add-fuel")
    public String addNewFuel(@RequestParam(value = "name") String name,
                             @RequestParam(value = "desc") String desc,
                             @RequestParam(value = "price") String price,
                             @RequestParam(value = "img") String img){
        if(desc.equals(""))
            desc=null;
        if(img.equals(""))
            img=null;
        FuelDao.addFuel(name, desc, Double.parseDouble(price), img);
        return "redirect:/admin-fuel";
    }

    @GetMapping("/admin-otherProducts")
    public String showAdminOtherProducts(Model model){
        ArrayList<Product> products = ProductsDao.getProducts();
        model.addAttribute("products", products);
        return "admin-otherProducts";
    }

    @GetMapping("/product-delete")
    public String deleteProduct(@RequestParam(value = "id") int id){
        ProductsDao.deleteProduct(id);
        return "redirect:/admin-otherProducts";
    }

    @GetMapping("/product-edit")
    public String getEditProductPage(@RequestParam(value = "id") int id,
                                  Model model){
        Product product = ProductsDao.getEditingProduct(id);
        model.addAttribute("product", product);
        ArrayList<String> images = Util.getFiles("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\static\\media\\db\\product", "../media/db/product/",product.getImg_product());
        model.addAttribute("images", images);
        return "edit-product";
    }

    @PostMapping("/product-edit")
    public String updateProduct(@RequestParam(value = "id") int id,
                             @RequestParam(value = "name") String name,
                             @RequestParam(value = "desc") String desc,
                             @RequestParam(value = "price") String price,
                             @RequestParam(value = "img") String img){
        ProductsDao.updateProduct(id,name,desc,Double.parseDouble(price),img);
        return "redirect:/product-edit?id="+id;
    }

    @GetMapping("/add-product")
    public String addProduct(Model model){
        ArrayList<String> images = Util.getFiles("C:\\Users\\user\\Desktop\\vkr\\src\\main\\resources\\static\\media\\db\\product", "../media/db/product/","");
        model.addAttribute("images", images);
        return "add-product";}

    @PostMapping("/add-product")
    public String addNewProduct(@RequestParam(value = "name") String name,
                             @RequestParam(value = "desc") String desc,
                             @RequestParam(value = "price") String price,
                             @RequestParam(value = "img") String img){
        ProductsDao.addProduct(name, desc, Double.parseDouble(price), img);
        return "redirect:/admin-otherProducts";
    }

}
