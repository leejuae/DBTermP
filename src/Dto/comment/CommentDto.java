package Dto.comment;

import java.sql.Timestamp;

public class CommentDto {
    private String comment_id;
    private Timestamp commented_at;
    private String location;
    private String content;
    private String post_id;
    private String user_id;

    public CommentDto(){
        this.comment_id = "0000";
        this.commented_at = new Timestamp(System.currentTimeMillis());
        this.location = "0000";
        this.content = "0000";
        this.post_id = "0000";
        this.user_id = "0000";
    }

    public CommentDto(String comment_id, Timestamp commented_at, String location, String content, String post_id, String user_id) {
        this.comment_id = comment_id;
        this.commented_at = commented_at;
        this.location = location;
        this.content = content;
        this.post_id = post_id;
        this.user_id = user_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public Timestamp getCommented_at() {
        return commented_at;
    }

    public void setCommented_at(Timestamp commented_at) {
        this.commented_at = commented_at;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "CommentDto{" +
                "comment_id='" + comment_id + '\'' +
                ", commented_at=" + commented_at +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                ", post_id='" + post_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
