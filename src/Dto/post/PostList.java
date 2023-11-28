package Dto.post;

import java.util.List;
import java.util.ArrayList;

public class PostList {
    private List<PostDto> postList;
    private int count;

    public PostList() {
        postList = new ArrayList<PostDto>();
        count = 0;
    }

    public List<PostDto> getPostList() {
        return postList;
    }

    public void setPostList(List<PostDto> postList) {
        this.postList = postList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
