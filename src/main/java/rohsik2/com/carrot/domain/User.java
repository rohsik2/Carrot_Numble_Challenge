package rohsik2.com.carrot.domain;

import rohsik2.com.carrot.controller.UserForm;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name = "user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userNo;
    private String email;
    private String pw;
    private String name;
    private String phoneNumber;
    private String nickname;

    @OneToMany(mappedBy = "user")
    private Set<Stuff> stuffSet = new HashSet<>();

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


    public long getUserNo() {
        return userNo;
    }

    public void setUserNo(long userNo) {
        this.userNo = userNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Stuff> getStuffSet() {
        return stuffSet;
    }

    public void setStuffSet(Set<Stuff> stuffSet) {
        this.stuffSet = stuffSet;
    }
}
