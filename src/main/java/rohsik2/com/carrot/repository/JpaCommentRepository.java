package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaCommentRepository implements CommentRepository{

    private EntityManager em;

    public JpaCommentRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment register(Comment comment, Stuff stuff) {
        comment.setStuff(stuff);
        em.persist(comment);
        return comment;
    }

    @Override
    public void delete(long commentId) {
        Stuff stuff = findByCommentId(commentId).orElse(null);
        if(stuff != null)
            em.remove(stuff);
    }

    @Override
    public List<Comment> findByUser(User writer) {
        return em.createQuery("select c from Comment c where c.writer = :writer", Comment.class)
                .setParameter("writer", writer)
                .getResultList();
    }

    @Override
    public List<Comment> findByStuffId(String category) {
        return null;
    }

    @Override
    public Optional<Stuff> findByCommentId(long commentId) {
        return Optional.empty();
    }
}
