package rohsik2.com.carrot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rohsik2.com.carrot.repository.*;
import rohsik2.com.carrot.service.StuffService;
import rohsik2.com.carrot.service.UserService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;

    private UserRepository ur;
    private StuffRepository sr;
    private CommentRepository cr;


    public SpringConfig(DataSource dataSource, EntityManager em){
        this.em = em;
        this.dataSource = dataSource;
    }

    @Bean
    public StuffService stuffService(){
        return new StuffService(userRepository(), stuffRepository(), commentRepository());
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository(), stuffRepository(), commentRepository());
    }


    @Bean
    public StuffRepository stuffRepository() {
        if(sr == null) {
            this.sr = new JpaStuffRepo(em);
        }
        return sr;
    }


    @Bean
    public UserRepository userRepository(){
        if(ur == null) {
            this.ur = new JpaUserRepo(em);
        }
        return ur;
    }

    @Bean
    public CommentRepository commentRepository(){
        if(cr == null) {
            this.cr = new JpaCommentRepo(em);
        }
        return cr;
    }
}
