package rohsik2.com.carrot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rohsik2.com.carrot.SpringConfig;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.service.UserService;

import java.util.List;

@Controller
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/login")
    public String login(Model model){
        return "/user/login";
    }

    @PostMapping("/user/login")
    public String loginResult(LoginForm loginForm, Model model){
        System.out.println(loginForm.getEmail()+" " +loginForm.getPw()+ "tried login");
        if(userService.isValidUser(loginForm.getEmail(), loginForm.getPw())){
            System.out.println("login_success");
            User user = userService.findByEmail(loginForm.getEmail()).get();
            String token = SpringConfig.tokenService.get_token(user);
            model.addAttribute("userToken", token);
            return "redirect:/stuff/stuffList?userToken="+token;
        }
        return "redirect:/user/login";
    }

    @GetMapping("/user/new")
    public String register(Model model){
        return "/user/new";
    }

    @PostMapping("/user/new")
    public String create(UserForm form){
        User user = new User(form);
        if(form.is_valid()){
            user.setPw(form.getPw());
            userService.join(user);
        }
        else
            return "redirect:/user/login";
        return "redirect:/user/login";
    }

    @GetMapping("/user/profile")
    public String profile(@RequestParam("userToken") String userToken, Model model){
        if(SpringConfig.tokenService.isValidToken(userToken)) {
            model.addAttribute("user", SpringConfig.tokenService.getUserByToken(userToken));
            model.addAttribute("userToken", userToken);
            return "/user/profile";
        }
        else
            return "redirect:/user/login";
    }

    @GetMapping("/user/userList")
    public String userList(Model model){
        List<User> users = userService.findUsers();
        model.addAttribute("users", users);
        return "/user/userList";
    }
}
