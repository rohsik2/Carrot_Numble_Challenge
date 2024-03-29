package rohsik2.com.carrot.repository;

import org.springframework.stereotype.Repository;
import rohsik2.com.carrot.domain.User;

import java.util.*;

public class MemoryUserRepo implements UserRepository{

    private static final Map<Long, User> users = new HashMap<>();
    private static long seqNo = 0L;

    @Override
    public User save(User user) {
        if(!isDuplicate(user))
            user.setUserNo(++seqNo);
        else
            throw new IllegalStateException("User Already Exist");
        users.put(user.getUserNo(), user);
        return user;
    }

    @Override
    public User update(User user){
        return user;
    }

    @Override
    public void delete(User user){
        users.remove(user.getUserNo());
    }

    @Override
    public boolean isDuplicate(User user){
        return !(
                findByEmail(user.getEmail()).isPresent() &&
                findByNickname(user.getNickname()).isPresent() &&
                users.values().stream()
                        .filter(curUser -> curUser.getPhoneNumber()
                        .equals(user.getPhoneNumber())).findAny()
                        .isPresent()
                );
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
        return new ArrayList<>(users.values());
    }

    @Override
    public Optional<User> findByEmail(String email){
        return users.values().stream()
                .filter(user -> user.getEmail().equals(email))
                .findAny();
    }

    public void clear(){
        users.clear();
    }
}
