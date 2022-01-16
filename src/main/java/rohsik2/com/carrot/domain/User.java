package rohsik2.com.carrot.domain;

import rohsik2.com.carrot.controller.UserForm;

public class User {
    private long userNo;
    private String email;
    private String pw;
    private String name;
    private String phoneNumber;
    private String nickname;

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
        email = userForm.getEmail();
        pw = userForm.getPw();
        name = userForm.getName();
        nickname = userForm.getNickname();
        phoneNumber = userForm.getPhoneNumber();
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
}
