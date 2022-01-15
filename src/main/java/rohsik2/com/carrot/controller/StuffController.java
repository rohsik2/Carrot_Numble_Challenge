package rohsik2.com.carrot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.MemoryStuffRepo;
import rohsik2.com.carrot.repository.StuffRepository;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Controller
public class StuffController {
    StuffRepository stuffRepository;

    public StuffController(MemoryStuffRepo stuffRepository) {
        this.stuffRepository = stuffRepository;
    }

    @GetMapping("/stuff/new")
    public String stuffNew(Model model){
        return "/stuff/new";
    }

    @PostMapping("/stuff/new")
    public String stuffRegister(StuffForm form){
        //TODO : Get user data from form(?)
        Stuff stuff = new Stuff(form);
        stuffRepository.register(stuff, new User());
        return "redirect:/stuff/stuffList";
    }

    @GetMapping("/stuff/stuffList")
    public String stuffList(Model model){
        model.addAttribute("stuffs", stuffRepository.findByTitle(""));
        return "/stuff/stuffList";
    }

    @GetMapping("/stuff/detail")
    public String detail(@RequestParam("stuffId") long stuffId, Model model){
        Optional<Stuff> optionalStuff = stuffRepository.findByStuffId(stuffId);
        if(!optionalStuff.isEmpty()) {
            model.addAttribute("stuff", optionalStuff.get());
            return "/stuff/detail";
        }
        //stuff not found
        return "/stuff/stuffList";
    }

    @GetMapping("/stuff/search")
    public String detail(@RequestParam("title") String title, Model model){
        List<Stuff> stuffs = stuffRepository.findByTitle(title);
        model.addAttribute("stuffs", stuffs);
        return "/stuff/search";
    }


}
