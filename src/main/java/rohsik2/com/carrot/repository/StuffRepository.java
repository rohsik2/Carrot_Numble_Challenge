package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import java.util.List;
import java.util.Optional;

public interface StuffRepository {
    Stuff register(Stuff stuff, User user);
    void delete(Stuff stuff);
    List<Stuff> findByCategory(String category);
    List<Stuff> findByTitle(String title);
    Optional<Stuff> findByStuffId(long stuffId);

}
