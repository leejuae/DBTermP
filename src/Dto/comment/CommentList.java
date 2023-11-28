package Dto.comment;

import Dto.comment.CommentDto;

import java.util.ArrayList;
import java.util.List;

public class CommentList {
    private List<CommentDto> CommentList;
    private int count;

    public CommentList() {
        CommentList = new ArrayList<CommentDto>();
        count = 0;
    }

    public List<CommentDto> getCommentList() {
        return CommentList;
    }

    public void setCommentList(List<CommentDto> CommentList) {this.CommentList = CommentList;}

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
