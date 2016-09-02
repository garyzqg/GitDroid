package com.fuicui.gitdroid.gitdroid.login;

import com.fuicui.gitdroid.gitdroid.login.model.AccessToken;
import com.fuicui.gitdroid.gitdroid.login.model.User;
import com.fuicui.gitdroid.gitdroid.login.model.UserRepo;
import com.fuicui.gitdroid.gitdroid.network.GithubApi;
import com.fuicui.gitdroid.gitdroid.network.GithubClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：yuanchao on 2016/8/26 0026 14:02
 * 邮箱：yuanchao@feicuiedu.com
 */
public class LoginPresenter {

    private Call<AccessToken> tokenCall;
    private Call<User> userCall;

    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    /**
     * 登录，完成的工作：使用code 换取Token，再换取用户信息
     *
     * @param code
     */
    public void login(String code) {
        loginView.showProgress();
        if (tokenCall != null) {
            tokenCall.cancel();
        }
        //获取Token
        tokenCall = GithubClient.getInstance().getOAuthToken(
                GithubApi.CLIENT_ID,
                GithubApi.CLIENT_SECRET,
                code);
        tokenCall.enqueue(tokenCallback);
    }

    //获取Token返回
    private Callback<AccessToken> tokenCallback = new Callback<AccessToken>() {
        @Override public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
            //成功返回
            AccessToken accessToken = response.body();
            String token = accessToken.getAccessToken();
            //保存用户Token
            UserRepo.setAccessToken(token);

            //使用Token来获取用户信息
            if (userCall!=null){
                userCall.cancel();
            }
            userCall = GithubClient.getInstance().getUser();
            userCall.enqueue(userCallback);
        }

        @Override public void onFailure(Call<AccessToken> call, Throwable t) {
            //请求失败
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWebView();


        }
    };

    //请求用户信息
    private Callback<User> userCallback = new Callback<User>() {
        @Override public void onResponse(Call<User> call, Response<User> response) {
            //请求完成
            //存储user
            User user = response.body();
            UserRepo.setUser(user);

            loginView.showMessage("登陆成功");
            loginView.gotoMainActivity();

        }

        @Override public void onFailure(Call<User> call, Throwable t) {
            //请求失败
            loginView.showMessage(t.getMessage());
            loginView.showProgress();
            loginView.resetWebView();
        }
    };
}
