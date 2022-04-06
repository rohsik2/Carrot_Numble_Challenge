package rohsik2.com.carrot.domain;

import lombok.Getter;
import lombok.Setter;
import rohsik2.com.carrot.controller.UserForm;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "member")
@Getter @Setter
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;
    private String email;
    private String pw;
    private String name;
    private String phoneNumber;
    private String nickname;
    private String role = "ROLE_USER";

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "owner")
    private Set<Stuff> stuffs = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,cascade = {CascadeType.ALL},mappedBy = "writer")
    private Set<Comment> comments = new HashSet<>();
    public User(){
        email = "";
    }

    public User(String email, String pw, String name, String phoneNumber, String nickname){
        this.email = email;
        this.pw = pw;
        this.name = name;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public User(UserForm userForm){
        this.email = userForm.getEmail();
        this.pw = userForm.getPw();
        this.name = userForm.getName();
        this.nickname = userForm.getNickname();
        this.phoneNumber = userForm.getPhoneNumber();
    }

}
