package blog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import blog.domain.Article;

import java.util.List;


public interface ArticleRepository extends CrudRepository<Article, java.lang.Integer> {

    Article findTopByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();

    List<Article> findByTitleContainingIgnoreCase(String tag);

    List<Article> findAllByPostIdIsNullOrderByIdDesc();

    List<Article> findAllByPostIdEquals(int id);

    List<Article> findFirst10ByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();
   // List<Article> findAllAndIsEnabledIsTrue();

    List<Article>findAll();

//    @Query("SELECT a FROM Message a WHERE a.parentId = null AND a.isEnabled = true" )
//    Page<Article> findAll(Pageable pageable);

}

