package blog.service;

import blog.domain.Article;
import blog.domain.User;
import blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository emptyArticle;

    public Article getLastArticle(){
        return emptyArticle.findTopByAndIsEnabledIsTrueOrderByIdDesc();
    }

    public List<Article> getAllDesc(){
        return emptyArticle.findAllByOrderByIdDesc();
    }

    public List<Article> getByTitle(String tag){
        return emptyArticle.findByTitleContainingIgnoreCase(tag);
    }

    public List <Article> getLastTen(){
        return emptyArticle.findFirst10ByAndIsEnabledIsTrueOrderByIdDesc();
    }
    public void add (Article article){
        emptyArticle.save(article);
    }

    public Article getById(int idx) {
        Optional<Article> result = emptyArticle.findById(idx);
        return result.isPresent() ? result.get() : null;
    }

    public void update(int idx, Article article) {
        Article old = emptyArticle.findById(idx).get();
        old.setTitle(article.getTitle());
        old.setText(article.getText());
        old.setTag(article.getTag());
        old.setEditedDate(article.getEditedDate());
        old.setEditorId(article.getEditorId());
        old.setEnabled(article.getIsEnabled());
        emptyArticle.save(old);
      //  logger.info("Updated article {}", old);
    }

    public List<Article> getAllByParent(int id){
        return emptyArticle.findAllByParentIdEquals(id); //TODO de adus la conditii
    }

    public Article addEmptyArticle(){
        Article emptyArticle = new Article();
        emptyArticle.setTitle("Empty");
        emptyArticle.setText("Empty");
        emptyArticle.setAuthorId(new User());
        emptyArticle.setEditorId(new User());
        emptyArticle.setCreatedDate(new Date());
        emptyArticle.setEditedDate(new Date());
        return emptyArticle;
    }
}
