package com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model;

import com.fuicui.gitdroid.gitdroid.login.model.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 作者：yuanchao on 2016/8/29 0029 10:55
 * 邮箱：yuanchao@feicuiedu.com
 */
public class Repo implements Serializable{
    /**
     * {
     "id": 29028775,
     "name": "react-native",
     "full_name": "facebook/react-native",
     "owner": {
     "login": "facebook",
     "id": 69631,
     "avatar_url": "https://avatars.githubusercontent.com/u/69631?v=3",
     },
     "description": "A framework for building native apps with React.",
     "stargazers_count": 33961,
     "forks_count": 7122,
     }
     */

    private int id;
    private String name;

    @SerializedName("full_name")
    private String fullName;

    private String description;

    @SerializedName("stargazers_count")
    private int starCount;

    @SerializedName("forks_count")
    private int forksCount;

    private User owner;

    public User getOwner() {
        return owner;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getDescription() {
        return description;
    }

    public int getStarCount() {
        return starCount;
    }

    public int getForksCount() {
        return forksCount;
    }
}
