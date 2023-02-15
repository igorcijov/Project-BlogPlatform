package blog.controller;

import blog.controller.vo.ArticleVO;
import blog.domain.Article;
import blog.domain.User;
import blog.service.ArticleService;
import blog.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/article/")
public class RestArticleController implements ArticleRestApi{

    @Autowired
    ArticleService articleService;

    @Override
    public ResponseEntity<ArticleVO> add(ArticleVO body) {
        User authorId = body.getAuthorId();
        String title = body.getTitle();
        String tag = body.getTag();
        String text = body.getText();
        Date createdDate = body.getCreatedDate();
        boolean isEnabled = body.getIsEnabled();


        Article newArticle = null;
        if (!StringUtil.isEmpty(text) && !StringUtil.isEmpty(title) && authorId != null) {
            newArticle = new Article(authorId, title, tag, text, createdDate, isEnabled);
            articleService.add(newArticle);
        }
        return ResponseEntity.ok(ArticleVO.valueOf(newArticle));

    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {  //TODO
        return null;
    }

    @Override
    public ResponseEntity<ArticleVO> get(Integer id) {
        Article article = articleService.getById(id);
        if (article != null){
            return ResponseEntity.ok(ArticleVO.valueOf(article));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<ArticleVO>> getAll() {
        List<ArticleVO> articleVo = articleService.getAllDesc().stream()
                .map(e -> ArticleVO.valueOf(e))
                .collect(Collectors.toList());
        return ResponseEntity.ok(articleVo);
    }

    @Override
    public ResponseEntity<List<ArticleVO>> getByParent(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<ArticleVO> getLastArticle() {
        Article article = articleService.getLastArticle();
        if (article != null){
            return ResponseEntity.ok(ArticleVO.valueOf(article));
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<ArticleVO> update(Integer id, ArticleVO body) {
        Article article = articleService.update(id, body);
        return ResponseEntity.ok(ArticleVO.valueOf(article));
    }
}
