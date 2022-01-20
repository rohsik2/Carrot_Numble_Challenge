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
        assertEquals(stuffService.findByStuffId(myStuff.getStuffId()).get(), myStuff);
    }

    @Test
    public void 사람으로물건찾기(){
        User member = new User("name@gmail.com", "password123!!!", "name","01012345678","nickname");
        Long saveId = userService.join(member);
        Stuff myStuff1 = new Stuff("Carrot", "It's delicious", 0,0,"Food",1000);
        stuffService.save(myStuff1, member);
        Stuff myStuff2 = new Stuff("Carrot", "It's delicious", 0,0,"Food",1000);
        stuffService.save(myStuff2, member);
        assertEquals( 2, stuffService.findByUserId(member.getUserNo()).size());
    }

    @Test
    public void 물건으로사람찾기(){
        User member = new User("name@gmail.com", "password123!!!", "name","01012345678","nickname");
        Long saveId = userService.join(member);
        Stuff myStuff1 = new Stuff("Carrot", "It's delicious", 0,0,"Food",1000);
        stuffService.save(myStuff1, member);
        Stuff myStuff2 = new Stuff("Carrot", "It's delicious", 0,0,"Food",1000);
        stuffService.save(myStuff2, member);

        assertEquals(stuffService.findByStuffId(myStuff1.getStuffId()).get().getOwner(), member);
    }

    @Test
    public void 물건제거(){
        User member = new User("name@gmail.com", "password123!!!", "name","01012345678","nickname");
        Long saveId = userService.join(member);
        Stuff myStuff1 = new Stuff("Carrot", "It's delicious", 0,0,"Food",1000);
        stuffService.save(myStuff1, member);
        Stuff myStuff2 = new Stuff("Carrot", "It's delicious", 0,0,"Food",1000);
        stuffService.save(myStuff2, member);
        assertEquals(2, member.getStuffs().size());
        stuffService.delete(myStuff2.getStuffId());
        assertEquals(1, member.getStuffs().size());
    }

}
