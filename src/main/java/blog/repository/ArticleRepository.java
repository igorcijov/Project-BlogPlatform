package blog.repository;

import blog.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface ArticleRepository extends PagingAndSortingRepository<Article, Integer> { //PagingAndSortingRepository extends CrudRepository

    Article findTopByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();

    List<Article> findByTitleContainingIgnoreCase(String tag);

    List<Article> findAllByPostIdIsNullOrderByIdDesc();


    List<Article> findAllByParentIdNotNullAndPostIdEqualsOrderByIdDesc(int id);

    List<Article> findAllByPostIdEqualsOrderByIdAsc(int id);

    List<Article> findFirst10ByPostIdIsNullAndIsEnabledIsTrueOrderByIdDesc();

    List<Article>findAll();

    Iterable <Article> findAll(Sort sort);

    @Query (value = "SELECT * FROM articles WHERE post_id IS null AND is_enabled=true ORDER BY message_id DESC", nativeQuery = true)
    Page<Article> findAllTopics (Pageable pageable);
    @Query (value="SELECT * FROM articles WHERE tag='Frontend' AND post_id IS null AND is_enabled=true ORDER BY message_id DESC", nativeQuery = true)
    Page<Article> findAllFrontend (Pageable pageable);

    @Query (value="SELECT * FROM articles WHERE tag='Backend' AND post_id IS null AND is_enabled=true ORDER BY message_id DESC", nativeQuery = true)
    Page<Article> findAllBackend (Pageable pageable);

    @Query (value="SELECT * FROM articles WHERE tag='Linux' AND post_id IS null AND is_enabled=true ORDER BY message_id DESC", nativeQuery = true)
    Page<Article> findAllLinux (Pageable pageable);

}

