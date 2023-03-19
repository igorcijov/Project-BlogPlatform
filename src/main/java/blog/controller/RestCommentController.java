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

@RestController
@RequestMapping("/api/comment")
public class RestCommentController implements CommentRestApi {

    @Autowired
    ArticleService articleService;


    @Override
    public ResponseEntity<ArticleVO> add(ArticleVO body) {
        User authorId = body.getAuthorId();
        String text = body.getText();
        String tag = body.getTag();
        Date createdDate = body.getCreatedDate();
        Integer postId = body.getId();
        Integer parentId = null;
        boolean isEnabled = true;

        Article newArticle = null;
        if (!StringUtil.isEmpty(text) && !StringUtil.isEmpty(tag) && authorId != null) {
            newArticle = new Article(authorId, text, tag, createdDate, postId, parentId, isEnabled);
            articleService.add(newArticle);
        }
        return ResponseEntity.ok(ArticleVO.valueOf(newArticle));
    }

    @Override
    public ResponseEntity<ArticleVO> reply(ArticleVO body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> delete(Integer id) {
        if (articleService.deleteComments(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
