package Dto.mention;

import Dto.post.PostDto;

import java.util.ArrayList;
import java.util.List;

public class MentionList {
    private List<MentionDto> mentionList;
    private int count;

    public MentionList() {
        mentionList = new ArrayList<MentionDto>();
        count = 0;
    }

    public List<MentionDto> getMentionList() {
        return mentionList;
    }

    public void setMentionList(List<MentionDto> mentionList) {
        this.mentionList = mentionList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
