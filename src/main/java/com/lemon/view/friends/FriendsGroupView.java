package com.lemon.view.friends;

import java.util.List;

/**
 * Created by simpletour_Jenkin on 2017/1/19.
 */
public class FriendsGroupView {
    private String groupName;

    private List<FriendElementView> elementViewList;

    public FriendsGroupView(String groupName, List<FriendElementView> elementViewList) {
        this.groupName = groupName;
        this.elementViewList = elementViewList;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<FriendElementView> getElementViewList() {
        return elementViewList;
    }

    public void setElementViewList(List<FriendElementView> elementViewList) {
        this.elementViewList = elementViewList;
    }
}
