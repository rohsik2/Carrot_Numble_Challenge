package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment register(Comment comment, Stuff stuff);
    void delete(long commentId);
    List<Comment> findByUserId(long userId);
    List<Comment> findByStuffId(String category);
    Optional<Stuff> findByCommentId(long commentId);
}
