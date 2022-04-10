package rohsik2.com.carrot.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.service.CommentService;
import rohsik2.com.carrot.service.StuffService;

import java.util.*;
@RestController
@Controller
public class CommentController {
    CommentService commentService;
    StuffService stuffService;


    @Operation(summary = "test hello", description = "hello api example")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @GetMapping("/hello")
    public ResponseEntity<String> hello(@Parameter(description = "이름", required = true, example = "Park") @RequestParam String name) {
        return ResponseEntity.ok("hello " + name);
    }

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
