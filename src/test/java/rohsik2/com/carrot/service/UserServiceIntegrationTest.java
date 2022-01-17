package rohsik2.com.carrot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class UserServiceIntegrationTest {
    @Autowired UserService userService;
    @Autowired UserRepository userRepository;

    @Test
    public void 회원가입(){
        User member = new User("name@gmail.com", "password123!!!", "name","01012345678","nickname");
        //When
        Long saveId = userService.join(member);
        //Then
        User findMember = userRepository.findByUserNo(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복회원가입(){
        User member = new User("name@gmail.com", "password123!!!", "name","01012345678","nickname");
        Long saveId = userService.join(member);
        User member2 = new User("name1@gmail.com", "password123!!!", "name1","01012345688","nickname");
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> userService.join(member2));//예외가 발생해야 한다. assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}
