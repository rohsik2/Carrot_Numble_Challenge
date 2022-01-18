package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment register(Comment comment);
    Comment update(Comment comment);
    void delete(Comment comment);
    List<Comment> findByUser(User user);
    List<Comment> findByStuff(Stuff stuff);
    Optional<Comment> findByCommentId(long commentId);
}
