//package blog.controller;
//
//import blog.domain.Article;
//import blog.domain.User;
//import blog.service.ArticleService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Date;
//
//@Controller
//public class CommentsController {
//
//    @Autowired
//    ArticleService articleService;
//
//    @PostMapping("/add")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String text,
//
//
//            @RequestParam boolean isEnabled,
//            Model model) {
//        Date date = new Date();
//        Article article = new Article(user, text, date, isEnabled, null);
//        articleService.add(article);
//        return "redirect:/main";
//    }
//}
