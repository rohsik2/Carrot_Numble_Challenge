package rohsik2.com.carrot.service;

import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.CommentRepository;
import rohsik2.com.carrot.repository.StuffRepository;
import rohsik2.com.carrot.repository.UserRepository;

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

    public Comment register(Comment comment, Stuff stuff, User writer){
        comment.setWriter(writer);
        comment.setStuff(stuff);
        commentRepository.register(comment);

        stuff.getComments().add(comment);
        stuffRepository.update(stuff);

        writer.getComments().add(comment);
        userRepository.update(writer);

        return comment;
    }
}


