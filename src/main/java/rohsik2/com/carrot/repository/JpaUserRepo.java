package rohsik2.com.carrot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Transactional
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
    public User update(User user){
        em.merge(user);
        return user;
    }

    @Override
    public void delete(User user){
        em.remove(user);
    }

    @Override
    public boolean isDuplicate(User user) {
        return findByUserNo(user.getUserNo()).isPresent()
                || findByEmail(user.getEmail()).isPresent()
                || findByNickname(user.getNickname()).isPresent();
    }

    @Override
    public Optional<User> findByUserNo(long id) {
        return Optional.ofNullable(em.find(User.class, id));
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        List<User> userList = em.createQuery("select u from User u where u.nickname like :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList();
        return userList.stream().findAny();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> userList = em.createQuery("select u from User u where u.email like :email", User.class)
                .setParameter("email", email)
                .getResultList();
        return userList.stream().findAny();
    }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class)
                .getResultList();
    }
}
