package Dto.like;

import java.util.ArrayList;
import java.util.List;

public class PostLikeList {
    private List<PostLikeDto> PostLikeList;
    private int count;

    public PostLikeList() {
        PostLikeList = new ArrayList<PostLikeDto>();
        count = 0;
    }

    public List<PostLikeDto> getPostLikeList() {
        return PostLikeList;
    }

    public void setPostLikeList(List<PostLikeDto> PostLikeList) {this.PostLikeList = PostLikeList;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
