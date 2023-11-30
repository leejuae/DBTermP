package Dto.like;

public class CommentLikeDto {
    private String user_id;
    private String comment_id;

    public CommentLikeDto() {
        this.user_id = "0000";
        this.comment_id = "0000";
    }
    public CommentLikeDto(String user_id, String comment_id) {
        this.user_id = user_id;
        this.comment_id = comment_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    @Override
    public String toString() {
        return "CommentLikeDto{" +
                "user_id='" + user_id + '\'' +
                ", comment_id='" + comment_id + '\'' +
                '}';
    }
}
