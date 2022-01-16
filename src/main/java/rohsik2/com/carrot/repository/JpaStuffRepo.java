package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaStuffRepo implements StuffRepository{
    private final EntityManager em;

    public JpaStuffRepo(EntityManager em) {
        this.em = em;
    }

    @Override
    public Stuff register(Stuff stuff, User user) {
        em.persist(stuff);
        return stuff;
    }

    @Override
    public void delete(Stuff stuff) {
        em.createQuery("delete s from Stuff s where s.stuffId =:stuffId", Stuff.class)
                .setParameter("stuffId", stuff.getStuffId());
    }

    @Override
    public List<Stuff> findByUserId(long userId){
        return em.createQuery("select s from Stuff s where s.ownerId = :owenerId", Stuff.class)
                .setParameter("ownerId", userId)
                .getResultList();
    }

    @Override
    public List<Stuff> findByCategory(String category) {
        return em.createQuery("select s from Stuff s", Stuff.class)
                .getResultList();
    }

    @Override
    public List<Stuff> findByTitle(String title) {
        return em.createQuery("select s from stuff s where s.title like :title", Stuff.class)
                .setParameter("title", "*"+title+"*")
                .getResultList();
    }

    @Override
    public Optional<Stuff> findByStuffId(long stuffId) {
        Stuff stuff = em.find(Stuff.class, stuffId);
        return Optional.ofNullable(stuff);
    }
}
