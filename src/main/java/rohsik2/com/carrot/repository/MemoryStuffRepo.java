package rohsik2.com.carrot.repository;

import rohsik2.com.carrot.domain.Stuff;
import rohsik2.com.carrot.domain.User;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryStuffRepo implements StuffRepository {

    private static final Map<Long, Stuff> stuffs = new HashMap<>();
    private static long seqNo = 0L;

    @Override
    public Stuff register(Stuff stuff, User user) {
        stuff.setOwner(user);
        stuff.setStuffId(++seqNo);
        stuffs.put(seqNo, stuff);
        user.getStuffs().add(stuff);
        return stuff;
    }

    @Override
    public void delete(Stuff stuff) {
        //TODO : have to make method in User for deleting Stuff for him.
        stuff.getOwner().getStuffs().remove(stuff);
        stuffs.remove(stuff.getStuffId());
    }

    @Override
    public List<Stuff> findByUser(User user) {
        return new ArrayList<Stuff>(
            stuffs.values().stream()
                    .filter(stuff -> stuff.getOwner().equals(user))
                    .collect(Collectors.<Stuff>toList())
        );
    }

    @Override
    public List<Stuff> findByCategory(String category) {
        return new ArrayList<Stuff>(
                stuffs.values().stream()
                        .filter(stuff -> stuff.getCategory().equals(category))
                        .collect(Collectors.<Stuff>toList())
        );
    }

    @Override
    public List<Stuff> findByTitle(String title) {
        return new ArrayList<Stuff>(
                stuffs.values().stream()
                        .filter(stuff -> stuff.getCategory().indexOf(title) != -1)
                        .collect(Collectors.<Stuff>toList())
        );
    }

    @Override
    public Optional<Stuff> findByStuffId(long stuffId) {
        return stuffs.values().stream()
                .filter(user -> user.getStuffId() == stuffId)
                .findAny();
    }
}
