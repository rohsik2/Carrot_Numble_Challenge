package rohsik2.com.carrot.domain;

import java.util.List;

public class User {
    private long userNo;
    private String id;
    private String pw;
    private String nickname;
    private String phoneNumber;
    private List<Stuff> stuffs;

    public User(){
        id = "";
    }

    public User(String id, String pw, String nickname, String phoneNumber){
        this.id = id;
        this.pw = pw;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
    }

    public long getUserNo() {
        return userNo;
    }

    public void setUserNo(long userNo) {
        this.userNo = userNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<Stuff> getStuffs() {
        return stuffs;
    }

    public void setStuffs(List<Stuff> stuffs) {
        this.stuffs = stuffs;
    }
}
