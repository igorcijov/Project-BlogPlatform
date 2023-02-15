package blog.repository;

import blog.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import blog.domain.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();
    List<User> findByUsernameContainingIgnoreCase(String username);


}
