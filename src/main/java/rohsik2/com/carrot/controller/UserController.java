package rohsik2.com.carrot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("/user/login")
    public String login(Model model){
        return "login";
    }
}
