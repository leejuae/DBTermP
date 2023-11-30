package Dto.post;

import java.sql.Timestamp;

public class PostDto {
    private String post_id;
    private Timestamp timeStamp;
    private String location;
    private  String content;
    private String user_id;

    public PostDto() {
        this.post_id = "0000";
        this.timeStamp = new Timestamp(System.currentTimeMillis());
        this.location = "0000";
        this.content = "0000";
        this.user_id = "0000";

    }
    public PostDto(String post_id, Timestamp timeStamp, String location, String content, String user_id) {
        this.post_id = post_id;
        this.timeStamp = timeStamp;
        this.location = location;
        this.content = content;
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "post_id='" + post_id + '\'' +
                ", timeStamp=" + timeStamp +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
