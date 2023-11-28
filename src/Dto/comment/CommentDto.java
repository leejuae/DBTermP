package Dto.comment;

import java.sql.Timestamp;

public class CommentDto {
    private String comment_id;
    private Timestamp commented_at;
    private String location;
    private String content;

    public CommentDto(String comment_id, Timestamp commented_at, String location, String content) {
        this.comment_id = comment_id;
        this.commented_at = commented_at;
        this.location = location;
        this.content = content;
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

    @Override
    public String toString() {
        return "CommentDto{" +
                "comment_id='" + comment_id + '\'' +
                ", commented_at=" + commented_at +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
