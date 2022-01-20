package rohsik2.com.carrot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rohsik2.com.carrot.repository.*;
import rohsik2.com.carrot.service.CommentService;
import rohsik2.com.carrot.service.StuffService;
import rohsik2.com.carrot.service.TokenService;
import rohsik2.com.carrot.service.UserService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    public static TokenService tokenService = new TokenService();

    private final DataSource dataSource;
    private final EntityManager em;


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
        return new JpaStuffRepo(em);
    }

    @Bean
    public UserRepository userRepository(){
        return new JpaUserRepo(em);
    }

    @Bean
    public CommentRepository commentRepository(){
        return new JpaCommentRepo(em);
    }

    @Bean
    public CommentService commentService(){
        return new CommentService(userRepository(), stuffRepository(), commentRepository());
    }
}
