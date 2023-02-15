package blog.service;

import blog.controller.vo.ArticleVO;
import blog.domain.Article;
import blog.domain.User;
import blog.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    @Autowired
    private ArticleRepository articleRepository;

    public Article getLastArticle(){
        return articleRepository.findTopByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();
    }

    public List<Article> getAllDesc(){
        return articleRepository.findAllByPostIdIsNullOrderByIdDesc();
    }

    public List<Article> getByTitle(String tag){
        return articleRepository.findByTitleContainingIgnoreCase(tag);
    }

    public List <Article> getLastTen(){
        return articleRepository.findFirst10ByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();
    }

//    public Page<Article> findAll(Pageable pageable) {
//        return articleRepository.findAll(pageable);
//    }

    public void add (Article article){
        articleRepository.save(article);
        logger.info("Added {}", article);
    }

    public Article getById(int idx) {
        Optional<Article> result = articleRepository.findById(idx);
        return result.isPresent() ? result.get() : null;
    }

    public void updateOld(int idx, Article article) {
        Article old = articleRepository.findById(idx).get();
        old.setTitle(article.getTitle());
        old.setText(article.getText());
        old.setTag(article.getTag());
        old.setEditedDate(article.getEditedDate());
        old.setEditorId(article.getEditorId());
        old.setEnabled(article.getIsEnabled());
        articleRepository.save(old);
        logger.info("Updated {}", old);
    }

    public Article update(int idx, ArticleVO articleVO) {
        Article old = articleRepository.findById(idx).get();
        old.setTitle(articleVO.getTitle());
        old.setText(articleVO.getText());
        old.setTag(articleVO.getTag());
        old.setEditedDate(articleVO.getEditedDate());
        old.setEditorId(articleVO.getEditorId());
        old.setEnabled(articleVO.getIsEnabled());
        logger.info("Updated {}", old);
        return articleRepository.save(old);
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
    public List<Article> getAllByPostId(int id) {
        List<Article> comments = articleRepository.findAllByPostIdEquals(id);
        return comments;
    }


    public Article commentToAuthor (int id){
        Article result = getById(id);
        int newId=1;
        Article newArticle = getById(newId);
        if (result.getParentId()!=null){
             newId = result.getParentId();
        }
        else{
            newId = result.getPostId();
        }
        return newArticle;
    }


//    public List<Article> getLastComments(int num) {
//        List<Article> articles = articleRepository.findAllAndIsEnabledIsTrue();
//        return articles.stream()
//                .filter(a -> a.getParentId() != null)
//                .sorted((a1, a2) -> a2.compareTo(a1))
//                .limit(num)
//                .collect(Collectors.toList());
//    }

//    public boolean remove(Integer idx) {
//        Article article = getById(idx);
//        if (article != null) {
//            logger.info("Removed message {}", article);
//            //messageRepository.delete(message);
//            articleRepository.save(article);
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public void deleteAllByParent(int id) {
//        List<Article> articles = getAllByParent(id);
//
//        for ( Article article : articles) {
//            if (getAllByParent(article.getId()).isEmpty()) {
//                remove(article.getId());
//            } else if (!getAllByParent(article.getId()).isEmpty()) {
//                deleteAllByParent(article.getId());
//                remove(article.getId());
//            }
//        }
//    }
}
