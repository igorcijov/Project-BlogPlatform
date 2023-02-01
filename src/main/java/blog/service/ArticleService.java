package blog.service;

import blog.domain.Article;
import blog.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article getLastArticle(){
        return articleRepository.findTopByAndIsEnabledIsTrueOrderByIdDesc();
    }

    public List<Article> getAllDesc(){
        return articleRepository.findAllByOrderByIdDesc();
    }

    public List<Article> getByTitle(String tag){
        return articleRepository.findByTitleContainingIgnoreCase(tag);
    }

    public List <Article> getLastTen(){
        return articleRepository.findFirst10ByAndIsEnabledIsTrueOrderByIdDesc();
    }
    public void add (Article article){
        articleRepository.save(article);
    }

    public Article getById(int idx) {
        Optional<Article> result = articleRepository.findById(idx);
        return result.isPresent() ? result.get() : null;
    }

    public void update(int idx, Article article) {
        Article old = articleRepository.findById(idx).get();
        old.setTitle(article.getTitle());
        old.setText(article.getText());
        old.setTag(article.getTag());
        old.setEditedDate(article.getEditedDate());
        old.setEditorId(article.getEditorId());
        old.setEnabled(article.getIsEnabled());
        articleRepository.save(old);
      //  logger.info("Updated article {}", old);
    }

    public List<Article> getAllByParent(int id){
        return articleRepository.findAllByParentIdEquals(id); //TODO de adus la conditii
    }
}
