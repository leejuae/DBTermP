package Dto.follow;

public class FollowDto {
    private String following_id;
    private String follower_id;

    public FollowDto() {
        this.following_id = "0000";
        this.follower_id = "0000";
    }
    public FollowDto(String following_id, String follower_id) {
        this.following_id = following_id;
        this.follower_id = follower_id;
    }

    public String getFollowing_id() {
        return following_id;
    }

    public void setFollowing_id(String following_id) {
        this.following_id = following_id;
    }

    public String getFollower_id() {
        return follower_id;
    }

    public void setFollower_id(String follower_id) {
        this.follower_id = follower_id;
    }

    @Override
    public String toString() {
        return "FollowDto{" +
                "following_id='" + following_id + '\'' +
                ", follower_id='" + follower_id + '\'' +
                '}';
    }
}
