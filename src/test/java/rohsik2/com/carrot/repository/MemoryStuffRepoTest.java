package rohsik2.com.carrot.repository;

import org.junit.jupiter.api.Test;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryStuffRepoTest {

    private JpaStuffRepo stuffRepo;

    MemoryStuffRepoTest(JpaStuffRepo stuffRepo) {
        this.stuffRepo = stuffRepo;
    }


    @Test
    void register() {
        User temp_user = new User("name@gmail.com", "hello", "asdf", "01018740373","nickname" );
        Stuff new_stuff = new Stuff("title", "text",1, 0, "food", 10000);
        stuffRepo.register(new_stuff, temp_user);
        assertThat(new_stuff).isEqualTo(stuffRepo.findByStuffId(new_stuff.getStuffId()).get());
    }

    @Test
    void delete() {
        User temp_user = new User("name@gmail.com", "hello", "asdf", "01018740373","nickname" );
        Stuff new_stuff = new Stuff("title", "text",1, 0, "food", 10000);
        Stuff new_stuff2 = new Stuff("title2", "text2",1, 0, "food", 10000);
        stuffRepo.register(new_stuff, temp_user);
        stuffRepo.register(new_stuff2, temp_user);
        assertThat(new_stuff).isEqualTo(stuffRepo.findByStuffId(new_stuff.getStuffId()).get());
        stuffRepo.delete(new_stuff);
    }

    @Test
    void findByUser() {
    }

    @Test
    void findByCategory() {
    }

    @Test
    void findByTitle() {
    }

    @Test
    void findByStuffId() {
    }
}