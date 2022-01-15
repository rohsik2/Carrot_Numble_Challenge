package rohsik2.com.carrot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rohsik2.com.carrot.repository.JpaUserRepo;
import rohsik2.com.carrot.repository.UserRepository;
import rohsik2.com.carrot.service.UserService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;
    private final EntityManager em;
    public SpringConfig(DataSource dataSource, EntityManager em){
        this.em = em;
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new JpaUserRepo();
    }
}
