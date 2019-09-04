package com.kunfei.bookshelf.bean;

import com.kunfei.bookshelf.constant.BookType;

public class FindKindGroupBean {
    private String groupName;
    private String groupTag;
    private String groupBookType;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupTag() {
        return groupTag;
    }

    public void setGroupTag(String groupTag) {
        this.groupTag = groupTag;
    }

    public String getGroupBookType() { return groupBookType; }
    public void setGroupBookType(String value) { this.groupBookType = value; }

    public boolean isAudio() {
        return BookType.AUDIO.equals(this.groupBookType);
    }
}
