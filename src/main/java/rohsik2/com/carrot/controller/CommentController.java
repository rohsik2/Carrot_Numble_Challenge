package rohsik2.com.carrot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.service.CommentService;
import rohsik2.com.carrot.service.StuffService;

import java.util.*;

@Controller
public class CommentController {
    CommentService commentService;
    StuffService stuffService;

    @Autowired
    public void setCommentService(CommentService commentService, StuffService stuffService) {
        this.commentService = commentService;
        this.stuffService = stuffService;
    }

    @GetMapping("/comment/commentList")
    public String commentList(
            @RequestParam("stuffId") long stuffId, @RequestParam("userToken") String userToken, Model model)
    {
        model.addAttribute("userToken", userToken);
        model.addAttribute("stuff", stuffService.findByStuffId(stuffId).get());
        model.addAttribute("stuffId", stuffId);
        model.addAttribute("comments", commentService.getCommentsWithStuffId(stuffId));
        return "/comment/commentList";
    }

    @GetMapping("/comment/new")
    public String commentNew(
            @RequestParam("stuffId") long stuffId, @RequestParam("userToken") String userToken, Model model)
    {
        model.addAttribute("userToken", userToken);
        model.addAttribute("stuffId", stuffId);
        return "/comment/new";
    }

    @PostMapping("/comment/new")
    public String commentRegister(CommentForm commentForm)
    {
        commentService.register(new Comment(commentForm), commentForm.getStuffId(), commentForm.getUserToken());
        return "redirect:/comment/commentList?userToken="+commentForm.getUserToken()+"&stuffId="+commentForm.getStuffId();
    }
}
