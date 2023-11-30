package Dto.comment;

public class MentionOnDto {
    private String mentioned_user_id;
    private String comment_id;

    public MentionOnDto() {
        this.mentioned_user_id = "0000";
        this.comment_id = "0000";
    }

    public MentionOnDto(String mentioned_user_id, String comment_id) {
        this.mentioned_user_id = mentioned_user_id;
        this.comment_id = comment_id;
    }

    public String getMentioned_user_id() {
        return mentioned_user_id;
    }

    public void setMentioned_user_id(String mentioned_user_id) {
        this.mentioned_user_id = mentioned_user_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    @Override
    public String toString() {
        return "MentionOnDto{" +
                "mentioned_user_id='" + mentioned_user_id + '\'' +
                ", comment_id='" + comment_id + '\'' +
                '}';
    }
}
