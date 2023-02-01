package blog.controller;

import blog.domain.Article;
import blog.domain.User;
import blog.service.ArticleService;
import liquibase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
public class MainViewController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/")
    public String greeting(Model model) {
        Article article = articleService.getLastArticle();
        List<Article> lastTenTitles = articleService.getLastTen();
        model.addAttribute("message", article);
        model.addAttribute("lastTenTitles", lastTenTitles);
        return "index";
    }

    @GetMapping("/main")
    public String main(Model model) {
        Article article = articleService.getLastArticle();
        List<Article> lastTenTitles = articleService.getLastTen();
        model.addAttribute("message", article);
        model.addAttribute("lastTenTitles", lastTenTitles);
        return "main";
    }

    @GetMapping("/list_posts")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       @RequestParam(required = false, defaultValue = "") String param_error, Model model) {
        Iterable<Article> messages = articleService.getAllDesc();
        if (filter != null && !filter.isEmpty()) {
            messages = articleService.getByTitle(filter);
        } else {
            messages = articleService.getAllDesc();
        }
        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);
        return "list_posts";
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @PostMapping("/add")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String title,
            @RequestParam String tag,
            @RequestParam boolean isEnabled,
            Model model) {
        Date date = new Date();
        Article article = new Article(user, text, title, tag, date, isEnabled);
        articleService.add(article);
        return "redirect:/main";
    }

    @GetMapping("/update_post/{id}")
    public String updateArticleForm(Model model, @PathVariable final Integer id){
        model.addAttribute("article", articleService.getAllDesc());
        Article article = articleService.getById(id);
        model.addAttribute("article", article);
        return "update_post";
    }

    @RequestMapping(value = "/update_post/{id}", method = RequestMethod.POST)
    public String updateArticle(Model model, @ModelAttribute("article") Article article, @PathVariable Integer id, @AuthenticationPrincipal User editorUser,
                                @RequestParam boolean isEnabled) {
        Date date = new Date();

        String title = article.getTitle();
        String text = article.getText();
        String tag = article.getTag();
        article.setEditedDate(date);
        article.setEditorId(editorUser);
        article.setEnabled(isEnabled);

        if (!StringUtil.isEmpty(title) && !StringUtil.isEmpty(text)) {
            articleService.update(id, article);

            return "redirect:/list_posts";
        }

        model.addAttribute("errorMessage", "First Name & Last Name is required!");
        return "update_post";
    }

//    @PostMapping(value = "/add_post")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String text,
//            @RequestParam String title,
//            @RequestParam String tag,
//            Model model) {
//        Date date = new Date();
//        Article article = new Article(user, text, title, tag, date);
//        articleService.add(article);
//        Iterable<Article> messages = articleService.getAll();
//        model.addAttribute("messages", messages);
//        return "main";
//    }
}
