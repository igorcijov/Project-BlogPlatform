package blog.repository;

import blog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();
    List<User> findByUsernameContainingIgnoreCase(String username);
    Optional <User> findById(int id);


}
