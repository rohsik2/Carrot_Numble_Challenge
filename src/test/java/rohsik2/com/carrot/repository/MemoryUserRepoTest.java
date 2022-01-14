package rohsik2.com.carrot.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rohsik2.com.carrot.domain.User;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryUserRepoTest {

    @Autowired
    public MemoryUserRepoTest(MemoryUserRepo memoryUserRepo) {
        this.memoryUserRepo = memoryUserRepo;
    }

    MemoryUserRepo memoryUserRepo;


    @AfterEach
    void afterEach(){
        memoryUserRepo.clear();
    }

    @Test
    void save(){
        User temp_user = new User("temp@temp.com", "password", "realname", "01012345678", "nickname");
        memoryUserRepo.save(memoryUserRepo.save(temp_user));
        User result = memoryUserRepo.findByUserNo(temp_user.getUserNo()).get();
        assertThat(temp_user).isEqualTo(result);
    }

    @Test
    void 중복확인()  {
        User temp_user = new User("temp@temp.com", "password", "realname", "01012345678", "nickname");
        memoryUserRepo.save(temp_user);
        User temp_user2 = new User("temp@temp.com", "password", "realname", "01012345678", "nickname");

        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memoryUserRepo.save(temp_user2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("User already exist");
    }

    @Test
    void findByNickname() {
        User temp_user = new User("temp@temp.com", "password", "realname", "01012345678", "nickname");
        memoryUserRepo.save(temp_user);
        Optional<User> user = memoryUserRepo.findByNickname("nickname");
        assertThat(temp_user).isEqualTo(user.get());
    }

}