package Dto.follow;

import Dto.like.PostLikeDto;

import java.util.ArrayList;
import java.util.List;

public class FollowList {
    private List<FollowDto> FollowList;
    private int count;

    public FollowList() {
        FollowList = new ArrayList<FollowDto>();
        count = 0;
    }

    public List<FollowDto> getFollowList() {
        return FollowList;
    }

    public void setFollowList(List<FollowDto> FollowList) {this.FollowList = FollowList;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
