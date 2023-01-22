package blog.controller;

import blog.domain.Role;
import blog.domain.User;
import blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.addAttribute("message", "User exists!");
            return "registration";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);

        return "redirect:/login";
    }


}
