package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User update(User user);
    void delete(User user);
    boolean isDuplicate(User user);
    Optional<User> findByUserNo(long id);
    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);
    List<User> findAll();
}
