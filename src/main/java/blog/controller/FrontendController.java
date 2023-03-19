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
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class FrontendController {

    @Autowired
    private ArticleService articleService;

    @RequestMapping("/frontend")
    public String getFrontend (Model model){
        return listByPage(model,1);
    }

    @GetMapping("/frontend/{pageNumber}")
    public String listByPage(Model model,
                             @PathVariable ("pageNumber") int currentPage) {

        Page<Article> pageArticles = articleService.getAllFrontend(currentPage);
        long totalItems = pageArticles.getTotalElements();
        int totalPages = pageArticles.getTotalPages();
        List<Article> articleList = pageArticles.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("message", articleList);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("service", articleService);

        return "frontend";

    }
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public String deleteComment(int id) {
        articleService.deleteAllByParent(id);
        return "redirect:/frontend";
    }
}
