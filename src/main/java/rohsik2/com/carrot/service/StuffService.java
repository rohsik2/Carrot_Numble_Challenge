package rohsik2.com.carrot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.CommentRepository;
import rohsik2.com.carrot.repository.JpaStuffRepo;
import rohsik2.com.carrot.repository.StuffRepository;
import rohsik2.com.carrot.repository.UserRepository;

import java.util.*;

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
        if(!optionalStuff.isPresent()){
            Stuff stuff = optionalStuff.get();
            stuffRepository.delete(stuff);
        }
    }

    public void save(Stuff stuff, User user){
        stuff.setOwner(user);
        stuffRepository.register(stuff, user);
    }

    List<Stuff> findByUserId(long userId){
        return stuffRepository.findByUserId(userId);
    }

    public List<Stuff> findByCategory(String category){
        return stuffRepository.findByCategory(category);
    }

    public List<Stuff> findByTitle(String title){
        return stuffRepository.findByTitle(title);
    }

    public Optional<Stuff> findByStuffId(long stuffId){
        return stuffRepository.findByStuffId(stuffId);
    }

    public static List<String> getCategories(){
        List<String> categories = new ArrayList<>();
        categories.add("디지털기기");
        categories.add("생활가전");
        categories.add("가구/인테리어");
        categories.add("유아동");
        categories.add("생활/가공식품");
        categories.add("유아도서");
        categories.add("여성의류");
        categories.add("남성패션/잡화");
        categories.add("게임/취미");
        categories.add("뷰티/미용");
        categories.add("반려동물용품");
        categories.add("도서/티켓/음반");
        categories.add("식물");
        categories.add("기타중고물품");
        categories.add("중고차");
        return categories;
    }
}
