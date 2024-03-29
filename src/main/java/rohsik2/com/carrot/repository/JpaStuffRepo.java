package rohsik2.com.carrot.repository;

import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import javax.persistence.EntityManager;
import java.util.*;

@Transactional(readOnly = true)
public class JpaStuffRepo implements StuffRepository{
    private final EntityManager em;

    public JpaStuffRepo(EntityManager em) {
        this.em = em;
    }

    @Override @Transactional
    public Stuff register(Stuff stuff, User user) {
        stuff.setWroteDate(new Date());
        em.persist(stuff);
        em.merge(user);
        return stuff;
    }

    @Override @Transactional
    public Stuff update(Stuff stuff){
        em.merge(stuff);
        return stuff;
    }

    @Override @Transactional
    public void delete(Stuff stuff) {
        stuff.getOwner().getStuffs().remove(stuff);
        em.remove(stuff);
    }

    @Override
    public List<Stuff> findByUserId(long userId){
        return em.createQuery("select s from Stuff s where s.owner.userNo = :userId", Stuff.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Stuff> findByCategory(String category) {
        return em.createQuery("select s from Stuff s where s.category like :category", Stuff.class)
                .setParameter("category", "%"+category+"%")
                .getResultList();
    }

    @Override
    public List<Stuff> findByTitle(String title) {
        return em.createQuery("select s from Stuff s where s.title like :title", Stuff.class)
                .setParameter("title", "%"+title+"%")
                .getResultList();
    }

    @Override
    public Optional<Stuff> findByStuffId(long stuffId) {
        Stuff stuff = em.find(Stuff.class, stuffId);
        return Optional.ofNullable(stuff);
    }
}
