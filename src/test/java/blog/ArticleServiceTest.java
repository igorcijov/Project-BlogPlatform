package blog;

import blog.domain.Article;
import blog.service.ArticleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    Article article;

    @BeforeEach
    public void init(){
        article = new Article();
        article.setTitle("Java");
        article.setText("has been the industry's standard dummy text ever since the 1500s");
        article.setTag("Frontend");
    }

    @Test
    public void testAdd(){
        List<Article> articles = articleService.getAllDesc();
        int count = articles.size();

        articleService.add(article);
        articles = articleService.getAllDesc();

        Assertions.assertTrue(count+1 == articles.size());
        Assertions.assertTrue(articles.get(0).getTitle().equals("Java"));
        Assertions.assertTrue(articles.get(0).getText().equals("has been the industry's standard dummy text ever since the 1500s"));
        Assertions.assertTrue(articles.get(0).getTag().equals("Frontend"));
    }

    @Test
    public void testGetLastArticle (){
        articleService.add(article);
        List<Article> articles = articleService.getAllDesc();
        Assertions.assertTrue(articles.get(0).getText().equals("has been the industry's standard dummy text ever since the 1500s"));

        Article newArticle = new Article("Linux", "readable content", "Linux");
        articleService.add(newArticle);
        articles = articleService.getAllDesc();
        Assertions.assertTrue(articles.get(0).getText().equals("Linux"));

    }

    @Test
    public void testUpdate(){
        Article updateArticle = new Article("updatedText", "updatedTitle", "updatedTag");
        articleService.add(article);
        Assertions.assertTrue(articleService.getById(1).getTitle().equals("Java"));

        articleService.updateOld(1,updateArticle);
        Assertions.assertTrue(articleService.getById(1).getTitle().equals("updatedTitle"));
    }
}
