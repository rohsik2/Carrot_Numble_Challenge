package rohsik2.com.carrot.service;

import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.SpringConfig;
import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.CommentRepository;
import rohsik2.com.carrot.repository.StuffRepository;
import rohsik2.com.carrot.repository.UserRepository;

import java.util.*;

@Transactional
public class CommentService {

    private final UserRepository userRepository;
    private final StuffRepository stuffRepository;
    private final CommentRepository commentRepository;

    public CommentService(UserRepository userRepository, StuffRepository stuffRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.stuffRepository = stuffRepository;
        this.commentRepository = commentRepository;
    }

    public Comment register(Comment comment, long stuffId, String userToken){
        User writer = SpringConfig.tokenService.getUserByToken(userToken);
        Optional<Stuff> opt = stuffRepository.findByStuffId(stuffId);
        if(opt.isPresent() && writer != null){
            Stuff stuff = opt.get();
            comment.setWriter(writer);
            comment.setStuff(stuff);
            commentRepository.register(comment);

            stuff.getComments().add(comment);
            stuffRepository.update(stuff);

            writer.getComments().add(comment);
            userRepository.update(writer);
        }

        return comment;
    }
    // returns sorted comments list by date with stuffid
    public List<Comment> getCommentsWithStuffId(long stuffId){
        Optional<Stuff> opt = stuffRepository.findByStuffId(stuffId);
        if(opt.isPresent()){
            Set<Comment> commentSet = opt.get().getComments();
            List<Comment> commentList = new ArrayList<>(commentSet);
            Collections.sort(commentList, new Comparator<Comment>(){
                public int compare(Comment s1, Comment s2) {
                    return s1.getWroteDate().compareTo(s2.getWroteDate());
                }
            });
            return commentList;
        }
        //TODO : find Better exception statement for not found.
        return null;
    }
}


