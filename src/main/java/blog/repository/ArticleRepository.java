package blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import blog.domain.Article;

import java.util.List;


public interface ArticleRepository extends CrudRepository<Article, Integer> {

    Article findTopByAndIsEnabledIsTrueOrderByIdDesc();

    List<Article> findByTitleContainingIgnoreCase(String tag);

    List<Article> findAllByOrderByIdDesc();

    List<Article> findAllByParentIdEquals(int id);

    List<Article> findFirst10ByAndIsEnabledIsTrueOrderByIdDesc();

    Page<Article> findAllByParentIdIsNull (Pageable pageable); //TODO de gandit

}

