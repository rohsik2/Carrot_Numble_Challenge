package rohsik2.com.carrot.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "comment")
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne @JoinColumn(name = "stuff")
    private Stuff stuff;
    private String text;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date wroteDate;

    public Comment() {
        ;
    }

    public Comment(String text, Date wroteDate){
        this.text = text;
        this.wroteDate = wroteDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getWroteDate() {
        return wroteDate;
    }

    public void setWroteDate(Date wroteDate) {
        this.wroteDate = wroteDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Stuff getStuff() {
        return stuff;
    }

    public void setStuff(Stuff stuff) {
        this.stuff = stuff;
    }
}
