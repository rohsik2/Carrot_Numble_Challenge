package rohsik2.com.carrot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rohsik2.com.carrot.SpringConfig;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.service.StuffService;

import java.util.List;
import java.util.Optional;

@Controller
@Transactional
public class StuffController {
    StuffService stuffService;

    public StuffController(StuffService stuffService) {
        this.stuffService = stuffService;
    }

    @GetMapping("/stuff/new")
    public String stuffNew(@RequestParam("userToken") String userToken, Model model){
        model.addAttribute("user", SpringConfig.tokenService.getUserByToken(userToken));
        model.addAttribute("categories", StuffService.getCategories());
        model.addAttribute("userToken", userToken);
        return "/stuff/new";
    }

    @PostMapping("/stuff/new")
    public String stuffRegister(StuffForm form){
        User user = SpringConfig.tokenService.getUserByToken(form.getUserToken());
        if(user == null)
            return "redirect:/user/login";
        Stuff stuff = new Stuff(form);
        stuffService.save(stuff, user);
        return "redirect:/stuff/stuffList?userToken="+form.getUserToken();
    }

    @GetMapping("/stuff/stuffList")
    public String stuffList(@RequestParam("userToken") String userToken, Model model){
        model.addAttribute("stuffs", stuffService.findByTitle(""));
        model.addAttribute("userToken", userToken);
        return "/stuff/stuffList";
    }

    @GetMapping("/stuff/category")
    public String categoryView(@RequestParam("userToken") String userToken, @RequestParam(value = "category", required = false)String category, Model model){

        if(category == null || category.equals("")){
            model.addAttribute("categories", StuffService.getCategories());
            return "/stuff/category";
        }
        model.addAttribute("stuffs", stuffService.findByCategory(category));
        model.addAttribute("userToken", userToken);
        return "/stuff/stuffList";
    }

    @GetMapping("/stuff/detail")
    public String detail(@RequestParam("userToken") String userToken, @RequestParam("stuffId") long stuffId, Model model){
        Optional<Stuff> optionalStuff = stuffService.findByStuffId(stuffId);
        if(!optionalStuff.isEmpty()) {
            Stuff stuff = optionalStuff.get();
            model.addAttribute("stuff", stuff);
            model.addAttribute("ownerStuffs", stuff.getOwner().getStuffs());
            model.addAttribute("userToken", userToken);
            return "/stuff/detail";
        }
        //stuff not found
        model.addAttribute("userToken", userToken);
        return "/stuff/stuffList";
    }

    @GetMapping("/stuff/search")
    public String detail(@RequestParam("userToken") String userToken, @RequestParam(value = "title", required = false) String title, Model model){
        if(title == null || title.equals("")) {
            model.addAttribute("userToken", userToken);
            return "/stuff/search";
        }
        else{
            List<Stuff> stuffs = stuffService.findByTitle(title);
            model.addAttribute("stuffs", stuffs);
            model.addAttribute("userToken", userToken);
            return "/stuff/stuffList";
        }
    }
}
