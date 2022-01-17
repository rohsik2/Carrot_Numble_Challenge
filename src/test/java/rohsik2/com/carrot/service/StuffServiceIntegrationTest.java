package rohsik2.com.carrot.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.StuffRepository;
import rohsik2.com.carrot.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
public class StuffServiceIntegrationTest {

    @Autowired
    UserService userService;
    @Autowired
    StuffService stuffService;

    @Test
    public void 물건등록(){
        User member = new User("name@gmail.com", "password123!!!", "name","01012345678","nickname");
        Long saveId = userService.join(member);
        User findMember = userService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
        Stuff myStuff = new Stuff("Carrot", "It's delicious", 0,0,"Food",1000);
        stuffService.save(myStuff, member);
        assertEquals(stuffService.findByStuffId(myStuff.getStuffId()), myStuff);
    }
}
