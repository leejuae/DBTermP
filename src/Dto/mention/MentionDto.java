package Dto.mention;

public class MentionDto {
    private String mentioned_user_id;
    private String comment_id;

    public MentionDto(String mentioned_user_id, String comment_id) {
        this.mentioned_user_id = mentioned_user_id;
        this.comment_id = comment_id;
    }

    public String getMentioned_user_id() {
        return mentioned_user_id;
    }

    public void setMentioned_user_id(String mentioned_user_id) {
        this.mentioned_user_id = mentioned_user_id;
    }

    @Override
    public String toString() {
        return "MentionDto{" +
                "mentioned_user_id='" + mentioned_user_id + '\'' +
                ", comment_id='" + comment_id + '\'' +
                '}';
    }
}
