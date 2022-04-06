package rohsik2.com.carrot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import rohsik2.com.carrot.controller.StuffForm;

import javax.persistence.*;
import java.util.*;

@Entity @Table(name = "stuff")
@Getter @Setter
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

    public void setOwner(User user){
        this.owner = user;
    }
}
