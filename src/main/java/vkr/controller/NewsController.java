package vkr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vkr.dao.NewsDao;
import vkr.model.dto.NewsDto;
import vkr.service.Util;

import java.util.ArrayList;

@Controller
public class NewsController {

    @GetMapping("/news")
    public String getFilteredNews(Model model,
                                  @RequestParam(value = "year") String year,
                                  @RequestParam(value = "month") String month){
        Util.whichNews(model, year, month);
        return "news";
    }

    @GetMapping("/admin-news")
    public String getAdminNews(Model model){
        ArrayList<NewsDto> articles = NewsDao.getAllNews();
        model.addAttribute("articles", articles);
        return "admin-news";
    }

    @GetMapping("/news-delete")
    public String deleteArticle(@RequestParam(value = "id") int id){
        NewsDao.deleteArticle(id);
        return "redirect:/admin-news";
    }

    @GetMapping("/news-edit")
    public String getEditPage(@RequestParam(value = "id") int id, Model model){
        NewsDto article = NewsDao.getEditingArticle(id);
        model.addAttribute("article", article);
        return "edit-article";
    }

    @PostMapping("/edit-article")
    public String editArticle(@RequestParam(value = "id") int id,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "prev") String prev,
                              @RequestParam(value = "text") String text,
                              @RequestParam(value = "date") String date){
        NewsDao.updateArticle(id, name, prev, text, Util.getDate(date));
        return "redirect:/news-edit?id="+id;
    }

    @GetMapping("/add-article")
    public String showAddPage(){
        return "add-article";
    }

    @PostMapping("/add-article")
    public String addArticle(@RequestParam(value = "name") String name,
                             @RequestParam(value = "prev") String prev,
                             @RequestParam(value = "text") String text,
                             @RequestParam(value = "date") String date){
        NewsDao.addArticle(name,prev,text,Util.getDate(date));
        return "redirect:/admin-news";
    }
}
