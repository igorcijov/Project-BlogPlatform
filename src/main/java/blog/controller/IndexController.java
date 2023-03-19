package blog.controller;

import blog.domain.Article;
import blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/")
    public String getAllTopics (Model model){
        return listByPage(model,1);
    }

    @GetMapping("/index/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable ("pageNumber") int currentPage) {

        Page<Article> pageArticles = articleService.getAllTopics(currentPage);
        long totalItems = pageArticles.getTotalElements();
        int totalPages = pageArticles.getTotalPages();

        List<Article> articleList = new ArrayList<>();
        if(pageArticles.getContent()==null){
            articleList.add(articleService.addEmptyArticle());
        }else{articleList=pageArticles.getContent();}

        List <Article> lastTenTitles = new ArrayList<>();
        if (articleService.getLastTen()==null){
            lastTenTitles.add(articleService.addEmptyArticle());
        }else {lastTenTitles = articleService.getLastTen();}

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("message", articleList);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("lastTenTitles", lastTenTitles);
        model.addAttribute("service", articleService);

        return "index";

    }
}
