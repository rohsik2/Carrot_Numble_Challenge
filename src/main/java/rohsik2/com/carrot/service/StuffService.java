package rohsik2.com.carrot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;
import rohsik2.com.carrot.repository.JpaStuffRepo;
import rohsik2.com.carrot.repository.StuffRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Transactional
public class StuffService {

    private StuffRepository stuffRepository;
    @Autowired
    public StuffService(StuffRepository stuffRepository) {
        this.stuffRepository = stuffRepository;
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



}
