package Dto.comment;

import java.sql.Timestamp;

public class CommentOnDto {
    private String post_id;
    private String comment_id;
    private String user_id;
    private String parent_id;
    private Timestamp date;
    private String content;

    public CommentOnDto() {
        this.post_id = "0000";
        this.comment_id = "0000";
        this.user_id = "0000";
        this.parent_id = "0000";
        this.date = new Timestamp(System.currentTimeMillis());
        this.content = "0000";
    }

    public CommentOnDto(String post_id, String comment_id, String user_id, String parent_id, Timestamp date, String content) {
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.user_id = user_id;
        this.parent_id = parent_id;
        this.date = date;
        this.content = content;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String child_comment_id) {
        this.parent_id = parent_id;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "CommentOnDto{" +
                "post_id='" + post_id + '\'' +
                ", comment_id='" + comment_id + '\'' +
                ", user_id='" + user_id + '\'' +
                ", child_comment_id='" + parent_id + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
