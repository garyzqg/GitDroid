package com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.view;

import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model.Repo;

import java.util.List;

/**
 * 作者：yuanchao on 2016/8/25 0025 11:11
 * 邮箱：yuanchao@feicuiedu.com
 */
public interface RepoLoadView {

    /**
     1. 显示加载视图
     2. 隐藏加载视图
     3. 加载失败视图
     4. 加载完成，拿到数据进行视图更新
     */
//    显示加载视图
    void showLoadingView();

//    隐藏加载视图
    void hideLoadView();

//    加载失败视图
    void showLoadError(String msg);

//    加载完成，拿到数据进行视图更新
    void addLoadData(List<Repo> list);
}
