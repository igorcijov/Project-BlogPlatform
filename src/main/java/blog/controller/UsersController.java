package blog.controller;

import blog.domain.User;
import blog.service.UserService;
import liquibase.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/update_user/{id}")
    public String updateUserForm(Model model, @PathVariable final Integer id){
        model.addAttribute("user", userService.getAll());
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "update_user";
    }

    @RequestMapping(value = "/update_user/{id}", method = RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute("user") User user, @PathVariable Integer id) {

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String username = user.getUsername();
        String password = user.getPassword();

        if (!StringUtil.isEmpty(firstName) && !StringUtil.isEmpty(lastName)) {
            userService.updateOld(id, user);

            return "redirect:/list_users";
        }

        model.addAttribute("errorMessage", "First Name & Last Name is required!");
        return "update_users";
    }
}
