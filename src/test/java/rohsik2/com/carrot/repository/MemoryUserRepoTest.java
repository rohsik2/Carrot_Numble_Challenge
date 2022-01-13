package rohsik2.com.carrot.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import rohsik2.com.carrot.domain.User;

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
    void save() {
        User temp_user = new User("id", "password", "nickname", "01012345678");
        memoryUserRepo.save(memoryUserRepo.save(temp_user));
        User result = memoryUserRepo.findByUserNo(temp_user.getUserNo()).get();
        Assertions.assertThat(temp_user).isEqualTo(result);
    }

    @Test
    void findByNickname() {
        User temp_user = new User("id", "password", "nickname", "01012345678");
        memoryUserRepo.save(memoryUserRepo.save(temp_user));
        User result = memoryUserRepo.findByNickname("nickname").get();
        Assertions.assertThat(temp_user).isEqualTo(result);
    }

}