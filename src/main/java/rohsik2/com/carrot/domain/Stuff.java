package rohsik2.com.carrot.domain;

import java.util.ArrayList;
import java.util.List;

public class Stuff {
    private long stuffId;
    private String title;
    private String text;
    private int isDone;
    private int numLike;
    private List<User> likeUsers;
    private User owner;

    public Stuff(){
        title = "";
        text = "";
        isDone = 0;
        numLike = 0;
        likeUsers = new ArrayList<>();
    }
    public Stuff(String title, String text, int isDone, int numLike) {
        this.title = title;
        this.text = text;
        this.isDone = isDone;
        this.numLike = numLike;
        this.likeUsers = new ArrayList<>();

    }

    public long getStuffId() {
        return stuffId;
    }

    public void setStuffId(long stuffId) {
        this.stuffId = stuffId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIsDone() {
        return isDone;
    }

    public void setIsDone(int isDone) {
        this.isDone = isDone;
    }

    public int getNumLike() {
        return numLike;
    }

    public void setNumLike(int numLike) {
        this.numLike = numLike;
    }

    public List<User> getLikeUsers() {
        return likeUsers;
    }

    public void setLikeUsers(List<User> likeUsers) {
        this.likeUsers = likeUsers;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
