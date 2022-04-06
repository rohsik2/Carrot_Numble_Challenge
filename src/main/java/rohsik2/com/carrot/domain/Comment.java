package rohsik2.com.carrot.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import rohsik2.com.carrot.controller.CommentForm;

import javax.persistence.*;
import java.util.Date;

@Entity @Table(name = "comment")
@Getter @Setter
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "stuff_comments")
    private Stuff stuff;

    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "user_comments")
    private User writer;
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

    public Comment(CommentForm commentForm){
        this.text = commentForm.getText();
        this.wroteDate = new Date();
    }

}
