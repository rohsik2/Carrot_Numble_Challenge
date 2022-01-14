package rohsik2.com.carrot.controller;

import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Member;

public class StuffController {
    @GetMapping("/stuff/stuffList")
    public String stuffList(Member member){
        return "/stuff/stuffList";
    }
}
