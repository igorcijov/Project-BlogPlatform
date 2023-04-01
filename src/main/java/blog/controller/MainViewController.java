package blog.controller;

import blog.domain.Article;
import blog.domain.User;
import blog.service.ArticleService;
import liquibase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class MainViewController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/main")
    public String getAllTopics (Model model){
        return listByPage(model,1);
    }

    @GetMapping("/main/{pageNumber}")
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
            @RequestParam String title,
            @RequestParam String tag,
            @RequestParam String text,
            @RequestParam boolean isEnabled,
            Model model) {
        Date date = new Date();
        Article article = new Article(user, title, tag, text, date, isEnabled);
        articleService.add(article);
        return "redirect:/main";
    }

    @GetMapping("/add_comment/{id}")
    public String addComment(Model model, @PathVariable final Integer id) {
        model.addAttribute("article", articleService.getAllDesc());
        Article article = articleService.getById(id);
        model.addAttribute("article", article);
        return "add_comment";
    }

    @PostMapping("/add_comment/{id}")
    public String addComment(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam Integer postId,
            boolean isEnabled,
            Model model) {
        Date date = new Date();
        Article article = new Article(user, text, tag, date, postId, null, isEnabled);
        articleService.add(article);
        return "redirect:/main";
    }

    @GetMapping("/reply_comment/{id}")
    public String replyComment(Model model, @PathVariable final Integer id) {
        model.addAttribute("article", articleService.getAllDesc());
        Article article = articleService.getById(id);
        model.addAttribute("article", article);
        return "reply_comment";
    }

    @PostMapping("/reply_comment/{id}")
    public String replyComment(
            @AuthenticationPrincipal User user,
            @RequestParam String text,
            @RequestParam String tag,
            @RequestParam Integer postId,
            @RequestParam Integer parentId,
            boolean isEnabled,
            Model model) {
        Date date = new Date();
        Article parentArticle = articleService.getById(parentId); //TODO
        Article article = new Article(user, text, tag, date, postId, parentId, isEnabled);
        articleService.add(article);
        model.addAttribute("parentId", parentArticle);
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
            articleService.updateOld(id, article);

            return "redirect:/list_posts";
        }

        model.addAttribute("errorMessage", "First Name & Last Name is required!");
        return "update_post";
    }

    @GetMapping ("/about")
    public String about (){
        return "about";
    }
}
