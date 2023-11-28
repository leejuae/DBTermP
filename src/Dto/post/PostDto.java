package Dto.post;

import java.sql.Timestamp;

public class PostDto {
    private String post_id;
    private Timestamp timeStamp;
    private String location;
    private  String content;

    public PostDto(String post_id, Timestamp timeStamp, String location, String content) {
        this.post_id = post_id;
        this.timeStamp = timeStamp;
        this.location = location;
        this.content = content;
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

    @Override
    public String toString() {
        return "PostDto{" +
                "post_id='" + post_id + '\'' +
                ", timeStamp=" + timeStamp +
                ", location='" + location + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
