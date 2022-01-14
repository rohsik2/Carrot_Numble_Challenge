package rohsik2.com.carrot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.UserRepository;

import java.util.List;

@Controller
public class UserController {

    UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user/login")
    public String login(Model model){
        return "/user/login";
    }

    @GetMapping("/user/new")
    public String register(Model model){
        return "/user/new";
    }

    @PostMapping("/user/new")
    public String create(UserForm form){
        System.out.println("new member post method get");
        User user = new User(form);
        if(form.is_valid()){
            userRepository.save(user);
        }
        else
            return "/user/new";
        return "redirect:/user/userList";
    }

    @GetMapping("/user/profile")
    public String profile(Model model){
        return "/user/profile";
    }

    @GetMapping("/user/userList")
    public String userList(Model model){
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "/user/userList";
    }
}
