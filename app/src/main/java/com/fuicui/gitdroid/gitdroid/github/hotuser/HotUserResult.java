package com.fuicui.gitdroid.gitdroid.github.hotuser;

import com.fuicui.gitdroid.gitdroid.login.model.User;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 123 on 2016/8/30.
 */
public class HotUserResult {
    /**
     * total_count : 12
     * incomplete_results : false
     */

    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }
}
