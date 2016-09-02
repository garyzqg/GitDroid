package com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 作者：yuanchao on 2016/8/29 0029 10:52
 * 邮箱：yuanchao@feicuiedu.com
 */
public class RepoResult {

    /**
     * "total_count": 2074901,
     * "incomplete_results": false,
     * "items":[{}]
     */
    @SerializedName("total_count")
    private int totalCount;

    @SerializedName("incomplete_results")
    private boolean incompleteResults;

    @SerializedName("items")
    private List<Repo> repoList;

    public int getTotalCount() {
        return totalCount;
    }

    public boolean isIncompleteResults() {
        return incompleteResults;
    }

    public List<Repo> getRepoList() {
        return repoList;
    }
}
