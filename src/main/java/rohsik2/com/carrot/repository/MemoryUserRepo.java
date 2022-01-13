package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.User;

import java.util.*;

public class MemoryUserRepo implements UserRepository{

    private static final Map<Long, User> users = new HashMap<>();
    private static long seqNo = 0L;

    @Override
    public User save(User user) {
        user.setUserNo(++seqNo);
        users.put(user.getUserNo(), user);
        return user;
    }

    @Override
    public Optional<User> findByUserNo(long userNo) {
        return Optional.ofNullable(users.get(userNo));
    }

    @Override
    public Optional<User> findByNickname(String nickname) {
        return users.values().stream()
                .filter(user -> user.getNickname().equals(nickname))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<User>(users.values());
    }

    public void clear(){
        users.clear();
    }
}
