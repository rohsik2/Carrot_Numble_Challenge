package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.Comment;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaCommentRepo implements CommentRepository{

    EntityManager em;

    public JpaCommentRepo(EntityManager em) {
        this.em = em;
    }

    @Override
    public Comment register(Comment comment, Stuff stuff) {
        comment.setStuff(stuff);
        em.persist(comment);
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        em.remove(comment);
    }

    @Override
    public List<Comment> findByUser(User user) {
        return em.createQuery("select c from Comment c where c.writer = :user", Comment.class)
                .setParameter("user", user)
                .getResultList();
    }

    @Override
    public List<Comment> findByStuff(Stuff stuff) {
        return em.createQuery("select c from Comment c where c.stuff = :stuff", Comment.class)
                .setParameter("stuff", stuff)
                .getResultList();
    }

    @Override
    public Optional<Comment> findByCommentId(long commentId) {
        return Optional.ofNullable(em.find(Comment.class, commentId));
    }
}
