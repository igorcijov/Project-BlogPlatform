package blog.service;

import blog.domain.User;
import blog.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User getByUserName(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> getByUserNameFilter(String username){
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }


    public List<User> getAll(){
       return userRepository.findAll();
    }

    public User addUser(User user){
        return userRepository.save(user);
    }

    public void updateOld(Long idx, User user) {
        User old = userRepository.findById(idx).get();
        old.setFirstName(user.getFirstName());
        old.setLastName(user.getLastName());
        old.setEmail(user.getEmail());
        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setActive(user.isActive());
        userRepository.save(old);
        logger.info("Updated {}", old);
    }
}
