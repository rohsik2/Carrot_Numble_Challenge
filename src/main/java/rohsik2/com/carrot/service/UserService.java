package rohsik2.com.carrot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.CommentRepository;
import rohsik2.com.carrot.repository.StuffRepository;
import rohsik2.com.carrot.repository.UserRepository;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final StuffRepository stuffRepository;
    private final CommentRepository commentRepository;

    public UserService(UserRepository userRepository, StuffRepository stuffRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.stuffRepository = stuffRepository;
        this.commentRepository = commentRepository;

    }


    public Long join(User user){
        validateDuplicateUser(user);//validate existing user
        userRepository.save(user);
        return user.getUserNo();
    }

    private void validateDuplicateUser(User user) {
        userRepository.findByNickname(user.getNickname())
                .ifPresent(user1 -> {
                    throw new IllegalStateException("User already exist");
                });
        userRepository.findByEmail(user.getEmail())
                .ifPresent(user1 -> {
                    throw new IllegalStateException("User already exist");
                });
    }
    /*
     * 전체 회원 조회.
     * */
    // 서비스 클래스 작성시에는 비즈니스 로직이 들어가야 하므로, 기획자들또한 어떤 클래스 인지 알 수 있도록 이름 짓는 ㄱ서이 좋음
    // repository class 의 경우 단순히 넣고 빼는 용도로 사용되므로, 조금 더 CS적으로 이름을 짓는것이 좋음.
    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public Optional<User> findOne(Long userId){
        return userRepository.findByUserNo(userId);
    }
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public User login(String email, String pw){
        if(isValidUser(email, pw)){
            User currentUser = userRepository.findByEmail(email).get();
            return currentUser;
        }
        return null;
    }


    public boolean isValidUser(String email, String pw){
        if(userRepository.findByEmail(email).isPresent())
            if(userRepository.findByEmail(email).get().getPw().equals(pw))
                return true;
        return false;
    }
}

