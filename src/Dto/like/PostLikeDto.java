package Dto.like;

public class PostLikeDto {
    private String user_id;
    private String post_id;

    public PostLikeDto() {
        this.user_id = "0000";
        this.post_id = "0000";
    }
    public PostLikeDto(String user_id, String post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    @Override
    public String toString() {
        return "PostLikeDto{" +
                "user_id='" + user_id + '\'' +
                ", post_id='" + post_id + '\'' +
                '}';
    }
}
