package blog.service;

import blog.controller.vo.ArticleVO;
import blog.domain.Article;
import blog.domain.User;
import blog.repository.ArticleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    static final Logger logger = LoggerFactory.getLogger(ArticleService.class);
    @Autowired
    private ArticleRepository articleRepository;

    public Article getLastArticle() {
        return articleRepository.findTopByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();
    }

    public List<Article> getAllDesc() {
        return articleRepository.findAllByPostIdIsNullOrderByIdDesc();
    }

    public List<Article> getByTitle(String tag) {
        return articleRepository.findByTitleContainingIgnoreCase(tag);
    }

    public List<Article> getLastTen() {
        return articleRepository.findFirst10ByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();
    }

    public void add(Article article) {
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

    public Article addEmptyArticle() {
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
        List<Article> comments = articleRepository.findAllByPostIdEqualsOrderByIdAsc(id);
        return comments;
    }

//    public Article commentToAuthor(int id) {
//        Article result = getById(id);
//        int newId = 1;
//        Article newArticle = getById(newId);
//        if (result.getParentId() != null) {
//            newId = result.getParentId();
//        } else {
//            newId = result.getPostId();
//        }
//        return newArticle;
//    }

    public Page<Article> getAllTopics(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 1);
        return articleRepository.findAllTopics(pageable);
    }

    public Page<Article> getAllFrontend(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 1);
        return articleRepository.findAllFrontend(pageable);
    }

    public Page<Article> getAllBackend(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 1);
        return articleRepository.findAllBackend(pageable);
    }

    public Page<Article> getAllLinux(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1, 1);
        return articleRepository.findAllLinux(pageable);
    }

    public void deleteAllByParent(int id) {
        Article article = getById(id);
        List<Article> articles = articleRepository.findAllByParentIdNotNullAndPostIdEqualsOrderByIdDesc(article.getPostId());
        List<Article> deleteCommentByParent = new ArrayList<>();

        if (!articles.isEmpty()) {
            for (Article comment : articles) {
                if (article.getId() == comment.getParentId()) {
                    deleteCommentByParent.add(comment);
                }
            }
            for (int i = 0; i < deleteCommentByParent.size(); i++) {
                for (int j = 0; j < articles.size(); j++) {
                    if (deleteCommentByParent.get(i).getId() == articles.get(j).getParentId()) {
                        deleteCommentByParent.add(articles.get(j));
                    }
                }
            }
            List<Article> sortedDeleteCommentByParent = deleteCommentByParent.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            for (Article deleteComments : sortedDeleteCommentByParent) {
                articleRepository.delete(deleteComments);
            }
        }
        articleRepository.delete(article);
    }

    public boolean deleteComments(int id){
        Article article = getById(id);
        if(article !=null){
            deleteAllByParent(id);
            return true;
        }
        return false;
    }
}

