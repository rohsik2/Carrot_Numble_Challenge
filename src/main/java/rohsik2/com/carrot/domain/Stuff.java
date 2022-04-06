package rohsik2.com.carrot.domain;

import org.springframework.format.annotation.DateTimeFormat;
import rohsik2.com.carrot.controller.StuffForm;

import javax.persistence.*;
import java.util.*;

@Entity @Table(name = "stuff")
public class Stuff {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stuffId;
    private String title;
    private String text;
    private int isDone;
    private int numLike;
    private int price;
    private String category;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date wroteDate;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "stuff")
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_no")
    private User owner;

    public Stuff(){
        title = "";
        text = "";
        isDone = 0;
        numLike = 0;
        price = 0;
        category = "";
    }

    public Stuff(String title, String text, int isDone, int numLike, String category, int price) {
        this.title = title;
        this.text = text;
        this.isDone = isDone;
        this.numLike = numLike;
        this.category = category;
        this.price = price;

    }

    public Stuff(StuffForm stuffForm){
        this.title = stuffForm.getTitle();
        this.text = stuffForm.getText();
        this.isDone = 0;
        this.numLike = 0;
        this.category = stuffForm.getCategory();
        this.price = stuffForm.getPrice();
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


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Date getWroteDate() {
        return wroteDate;
    }

    public void setWroteDate(Date wroteDate) {
        this.wroteDate = wroteDate;
    }
}
