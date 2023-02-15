package blog.controller;

import blog.domain.User;
import blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {
    @Autowired
    private UserService userService;

    @GetMapping("/list_users")
    public String main(@RequestParam(required = false, defaultValue = "") String filter,
                       @RequestParam(required = false, defaultValue = "") String param_error, Model model) {
        List<User> users = userService.getAll();
        if (filter != null && !filter.isEmpty()) {
            users = userService.getByUserNameFilter(filter);
        } else {
            users = userService.getAll();
        }
        model.addAttribute("users", users);
        model.addAttribute("filter", filter);
        return "list_users";
    }
}
