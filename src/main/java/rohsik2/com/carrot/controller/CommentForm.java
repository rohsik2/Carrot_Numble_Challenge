package rohsik2.com.carrot.controller;

public class CommentForm {
    private String text;
    private long stuffId;
    private String userToken;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public long getStuffId() {
        return stuffId;
    }

    public void setStuffId(long stuffId) {
        this.stuffId = stuffId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}
