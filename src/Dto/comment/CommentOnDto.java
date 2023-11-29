package Dto.comment;

public class CommentOnDto {
    private String post_id;
    private String comment_id;
    private String user_id;

    public CommentOnDto(String post_id, String comment_id, String user_id) {
        this.post_id = post_id;
        this.comment_id = comment_id;
        this.user_id = user_id;
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

    @Override
    public String toString() {
        return "CommentOnDto{" +
                "post_id='" + post_id + '\'' +
                ", comment_id='" + comment_id + '\'' +
                ", user_id='" + user_id + '\'' +
                '}';
    }
}
