package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.User;

import java.util.List;
import java.util.Optional;

public class JpaUserRepo implements UserRepository{
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean isDuplicate(User user) {
        return false;
    }

    @Override
    public Optional<User> findByUserNo(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
