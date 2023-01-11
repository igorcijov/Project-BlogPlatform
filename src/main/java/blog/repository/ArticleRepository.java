package blog.repository;

import org.springframework.data.repository.CrudRepository;
import blog.domain.Article;

import java.util.List;


public interface ArticleRepository extends CrudRepository<Article, Integer> {

    List<Article> findByTag(String tag);

}

