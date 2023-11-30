package Dto.user;

import java.sql.Time;
import java.sql.Timestamp;

public class UserDto {
    private String user_id;
    private String pw;
    private String name;
    private String profile_pic;
    private String introduction;
    private Timestamp created_at;
    private String background_pic;

    public UserDto() {
        this.user_id = "0000";
        this.pw = "0000";
        this.name = "0000";
        this.profile_pic = "src/assets/profile_image.png";
        this.introduction = "0000";
        this.created_at = new Timestamp(System.currentTimeMillis());
        this.background_pic = "src/assets/cloud.png";
    }


    public UserDto(String user_id, String pw, String name, String profile_pic, String introduction, Timestamp created_at, String background_pic) {
        this.user_id = user_id;
        this.pw = pw;
        this.name = name;
        this.profile_pic = profile_pic;
        this.introduction = introduction;
        this.created_at = created_at;
        this.background_pic = background_pic;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getBackground_pic() {
        return background_pic;
    }

    public void setBackground_pic(String background_pic) {
        this.background_pic = background_pic;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "user_id='" + user_id + '\'' +
                ", pw='" + pw + '\'' +
                ", name='" + name + '\'' +
                ", profile_pic='" + profile_pic + '\'' +
                ", introduction='" + introduction + '\'' +
                ", created_at=" + created_at +
                ", background_pic='" + background_pic + '\'' +
                '}';
    }
}
