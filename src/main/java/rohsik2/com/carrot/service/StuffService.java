package rohsik2.com.carrot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.CommentRepository;
import rohsik2.com.carrot.repository.JpaStuffRepo;
import rohsik2.com.carrot.repository.StuffRepository;
import rohsik2.com.carrot.repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
public class StuffService {

    private final UserRepository userRepository;
    private final StuffRepository stuffRepository;
    private final CommentRepository commentRepository;

    public StuffService(UserRepository userRepository, StuffRepository stuffRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.stuffRepository = stuffRepository;
        this.commentRepository = commentRepository;
    }

    public void delete(long stuffId){
        //TODO : Change to better code with optionalStuff
        Optional<Stuff> optionalStuff = stuffRepository.findByStuffId(stuffId);
        optionalStuff.orElseThrow(() -> new NoSuchElementException());
        if(!optionalStuff.isEmpty()){
            stuffRepository.delete(optionalStuff.get());
        }
    }

    public void save(Stuff stuff, User user){
        stuffRepository.register(stuff, user);
    }

    List<Stuff> findByUserId(long userId){
        return stuffRepository.findByUserId(userId);
    }

    List<Stuff> findByCategory(String category){
        return stuffRepository.findByCategory(category);
    }

    List<Stuff> findByTitle(String title){
        return stuffRepository.findByTitle(title);
    }

    Optional<Stuff> findByStuffId(long stuffId){
        return stuffRepository.findByStuffId(stuffId);
    }

}
