package rohsik2.com.carrot.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(){
        return "index";
    }
//    public String getCurrentUser(@AuthenticationPrincipal User user, Model model) {
//        String username = user.getUsername();
//        model.addAttribute("username", username);
//        return "home";
//    }
}
