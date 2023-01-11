package blog.controller;

import blog.domain.Article;
import blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class MainViewController {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String greeting(Model model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main (Model model){
       Iterable<Article> messages = articleRepository.findAll();
       model.addAttribute("messages", messages);
       return "main";
    }

    @PostMapping(value = "/add")
    public String add (@RequestParam String text,  @RequestParam String tag, Model model){
        Date date = new Date();
        Article article = new Article(text, tag, date);
        articleRepository.save(article);
        Iterable<Article> messages = articleRepository.findAll();
        model.addAttribute("messages", messages);

        return "main";
    }

    @PostMapping ("filter")
    public String filter (@RequestParam String filter, Model model){
        Iterable<Article> articles;
        if (filter != null && !filter.isEmpty()){
            articles = articleRepository.findByTag(filter);
        } else{
            articles = articleRepository.findAll();
        }
        model.addAttribute("messages", articles);
        return "main";
    }
}
