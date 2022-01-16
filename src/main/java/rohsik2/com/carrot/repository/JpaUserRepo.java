package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaUserRepo implements UserRepository{
    EntityManager em;
    public JpaUserRepo(EntityManager em){
        this.em = em;
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public boolean isDuplicate(User user) {
        if(findByUserNo(user.getUserNo()).isEmpty()
            && findByEmail(user.getEmail()).isEmpty()
            && findByNickname(user.getNickname()).isEmpty()
        )
            return false;
        return true;
    }

    @Override
    public Optional<User> findByUserNo(long id) {
        return Optional.ofNullable(em.find(User.class, id));

    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return Optional.ofNullable(
                em.createQuery("select u from User u where user.nickname = :nickname", User.class)
                        .setParameter("nickname", nickname)
                        .getSingleResult()
        );
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(
                em.createQuery("select u from User u where user.email = :email", User.class)
                        .setParameter("nickname", email)
                        .getSingleResult()
        );
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
