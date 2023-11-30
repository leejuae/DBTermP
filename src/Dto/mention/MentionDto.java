package Dto.mention;

public class MentionDto {
    private String mentioned_user_id;
    private String mention_user_id;

    public MentionDto() {
        this.mentioned_user_id = "0000";
        this.mention_user_id = "0000";
    }
    public MentionDto(String mentioned_user_id, String mention_user_id) {
        this.mentioned_user_id = mentioned_user_id;
        this.mention_user_id = mention_user_id;
    }

    public String getMentioned_user_id() {
        return mentioned_user_id;
    }

    public void setMentioned_user_id(String mentioned_user_id) {
        this.mentioned_user_id = mentioned_user_id;
    }

    public String getMention_user_id() {
        return mention_user_id;
    }

    public void setMention_user_id(String mention_user_id) {
        this.mention_user_id = mention_user_id;
    }

    @Override
    public String toString() {
        return "MentionDto{" +
                "mentioned_user_id='" + mentioned_user_id + '\'' +
                ", comment_id='" + mention_user_id + '\'' +
                '}';
    }
}
