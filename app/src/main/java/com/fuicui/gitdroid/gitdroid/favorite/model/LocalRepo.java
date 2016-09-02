package com.fuicui.gitdroid.gitdroid.favorite.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 本地仓库表
 * Created by 123 on 2016/8/31.
 */

@DatabaseTable(tableName = "local_repo")
public class LocalRepo {

    /**
     * id : 892275
     * name : retrofit
     * full_name : square/retrofit
     * description : Type-safe HTTP client for Android and Java by Square, Inc.
     * stargazers_count : 13283
     * forks_count : 2656
     * avatar_url : https://avatars.githubusercontent.com/u/82592?v=3
     * group : {"id":1,"name":"网络连接"}
     */

    public static final String COLUMN_GROUP_ID="group_id";

    //主键 ，不能重复，一般是递增
    @DatabaseField(id = true)
    private int id;

    @DatabaseField
    private String name;

    @DatabaseField(columnName = "full_name")
    @SerializedName("full_name")
    private String fullName;

    @DatabaseField
    private String description;

    @DatabaseField(columnName = "start_count")
    @SerializedName("stargazers_count")
    private int stargazersCount;

    @DatabaseField(columnName = "forks_count")
    @SerializedName("forks_count")
    private int forksCount;

    @DatabaseField(columnName = "avatar_url")
    @SerializedName("avatar_url")
    private String avatarUrl;

    //外键，主要的作用是关联另外一张表，这个字段是我们数据库中另外一个表，这个字段就是一个外键
    @DatabaseField(columnName = COLUMN_GROUP_ID,foreign = true,canBeNull = true)
    @SerializedName("group")
    private RepoGroup repoGroup;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStargazersCount() {
        return stargazersCount;
    }

    public void setStargazersCount(int stargazersCount) {
        this.stargazersCount = stargazersCount;
    }

    public int getForksCount() {
        return forksCount;
    }

    public void setForksCount(int forksCount) {
        this.forksCount = forksCount;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    private static List<LocalRepo> localRepoList;

    public RepoGroup getRepoGroup() {
        return repoGroup;
    }

    public void setRepoGroup(RepoGroup repoGroup) {
        this.repoGroup = repoGroup;
    }

    public static List<LocalRepo> getDefaultLocalRepo(Context context){
        if (localRepoList!=null){
            return localRepoList;
        }
        try {
            InputStream inputStream = context.getAssets().open("defaultrepos.json");
            String content = IOUtils.toString(inputStream);
            Gson gson = new Gson();
            localRepoList = gson.fromJson(content,new TypeToken<List<LocalRepo>>(){}.getType());
            return localRepoList;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
