package vkr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vkr.dao.CompanyDao;
import vkr.dao.FuelDao;
import vkr.model.Company;
import vkr.model.dto.FuelNamePriceDto;

import java.util.ArrayList;

@Controller
public class CompanyController {

    @GetMapping("/")
    public String redirectCompany(){
        return "redirect:/company";
    }

    @GetMapping("/company")
    public String showCompany(Model model){
        String company = CompanyDao.getTextCompany();
        ArrayList<FuelNamePriceDto> fuel = FuelDao.getFuelPrices();
        model.addAttribute("companyText", company);
        model.addAttribute("fuel", fuel);
        return "company";
    }

    @GetMapping("/admin-company")
    public String getAdminCompany(Model model){
        Company company = CompanyDao.getCompany();
        model.addAttribute("company", company);
        return "admin-company";
    }

    @PostMapping("/edit-company")
    public String editCompany(@RequestParam(value = "name") String name,
                              @RequestParam(value = "text") String text){
        CompanyDao.editCompany(name, text);
        return "redirect:/admin-company";
    }
}
