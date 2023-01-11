package blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import blog.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

}
