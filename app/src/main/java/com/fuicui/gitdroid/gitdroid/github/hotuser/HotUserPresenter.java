package com.fuicui.gitdroid.gitdroid.github.hotuser;

import com.fuicui.gitdroid.gitdroid.commons.LogUtils;
import com.fuicui.gitdroid.gitdroid.login.model.User;
import com.fuicui.gitdroid.gitdroid.network.GithubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 123 on 2016/8/30.
 */
public class HotUserPresenter {

    public interface HotUserView{
        /**
         * 刷新视图的分析
         * 1.重要的一点：得到网络请求来的数据
         * 2.显示刷新的视图
         * 3.停止刷新
         * 4.显示错误的信息
         * 5.空的数据，没有数据显示空的页面
         */

        void refreshData(List<User> list);

        void showRefreshView();

        void stopRefresh();

        void showMessage(String msg);

        void showEmptyView();

        void showErrorView();

        /**
         * 上拉加载视图的分析
         * 1.加载的数据进行填充
         * 2.显示加载的视图，显示正在加载
         * 3.隐藏加载的视图
         */

        void addLoadData(List<User> list);

        void showLoadView();

        void hideLoadView();

    }

    private HotUserView hotUserView;

    public HotUserPresenter(HotUserView hotUserView) {
        this.hotUserView = hotUserView;
    }

    private int nextPage = 1;
    private Call<HotUserResult> userCall;

    //刷新的业务
    public void refresh(){

        hotUserView.showRefreshView();
        if (userCall!=null){
            userCall.cancel();
        }
        userCall = GithubClient.getInstance().searchUsers("followers:>1000", nextPage);
        userCall.enqueue(refreUserCallback);
    }

    //加载的业务
    public void loadMore(){

        hotUserView.showLoadView();

        if (userCall!=null){
            userCall.cancel();
        }
        userCall = GithubClient.getInstance().searchUsers("followers:>1000", nextPage);
        userCall.enqueue(loadUserCallback);
    }

    private Callback<HotUserResult> refreUserCallback = new Callback<HotUserResult>() {

        @Override
        public void onResponse(Call<HotUserResult> call, Response<HotUserResult> response) {

            hotUserView.stopRefresh();
            if (response.isSuccessful()){
                HotUserResult hotUserResult = response.body();
                if (hotUserResult==null){
                    //显示一个空的视图、提示信息
                    hotUserView.showMessage("结果为空");
                    return;
                }
                List<User> users = hotUserResult.getUsers();
                hotUserView.refreshData(users);
                //显示Fragment上面
                nextPage=2;
                return;
            }
            hotUserView.showMessage("响应码："+response.code());
        }

        @Override
        public void onFailure(Call<HotUserResult> call, Throwable t) {
            //加载失败的信息以及视图的改变
            hotUserView.stopRefresh();
            hotUserView.showMessage("刷新失败"+t.getMessage());
        }
    };

    private Callback<HotUserResult> loadUserCallback = new Callback<HotUserResult>() {

        @Override
        public void onResponse(Call<HotUserResult> call, Response<HotUserResult> response) {

            hotUserView.hideLoadView();
            if (response.isSuccessful()){
                HotUserResult hotUserResult = response.body();
                if (hotUserResult==null){
                    hotUserView.showMessage("结果为空");
                    return;
                }
                List<User> users = hotUserResult.getUsers();
                //获得的集合添加
                hotUserView.addLoadData(users);
                nextPage++;
                return;
            }
            //显示信息
            hotUserView.showMessage("响应码："+response.code());
        }

        @Override
        public void onFailure(Call<HotUserResult> call, Throwable t) {
            //请求失败的信息
            hotUserView.hideLoadView();
            hotUserView.showMessage("加载失败："+t.getMessage());
        }
    };
}
