package com.fuicui.gitdroid.gitdroid.network;

import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model.RepoResult;
import com.fuicui.gitdroid.gitdroid.github.hotuser.HotUserResult;
import com.fuicui.gitdroid.gitdroid.github.repoinfo.RepoContentResult;
import com.fuicui.gitdroid.gitdroid.login.model.AccessToken;
import com.fuicui.gitdroid.gitdroid.login.model.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 放置接口请求API
 * 作者：yuanchao on 2016/8/26 0026 09:40
 * 邮箱：yuanchao@feicuiedu.com
 */
public interface GithubApi {

    //Github开发者，申请的信息
    String CLIENT_ID = "b3e74e2bfb623295d629";
    String CLIENT_SECRET = "553af778b07f66221efc8225e7c20491f88c3169";

    //申请时填写的标志（重定向标记）
    String CALL_BACK = "feicui";

    //授权登陆时可访问域
    String AUTH_SCOPE = "user,public_repo,repo";

    //登录页面的网址(WebView来进行访问)
    String AUTH_URL = "https://github.com/login/oauth/authorize?client_id=" + CLIENT_ID + "&scope=" + AUTH_SCOPE;

    /**
     * 获得访问令牌Token
     *
     * @return
     */
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    @Headers("Accept: application/json") Call<AccessToken> getOAuthToken(
            @Field("client_id") String clientId,
            @Field("client_secret") String clientSecret,
            @Field("code") String code);

    /**
     * 使用Token获取用户信息
     *
     * @return
     */
    @GET("user") Call<User> getUser();

    /**
     * 获取仓库列表的请求Api
     *
     * @param query  查询的参数--体现为语言
     * @param pageId 查询页数--从1开始
     * @return
     */
    @GET("/search/repositories") Call<RepoResult> searchRepos(
            @Query("q") String query,
            @Query("page") int pageId);


    /**
     * 获取readme
     * @param owner 仓库拥有者
     * @param repo 仓库名称
     * @return
     */
    @GET("/repos/{owner}/{repo}/readme")
    Call<RepoContentResult> getReadme(@Path("owner") String owner,
                     @Path("repo") String repo);

    /**
     * 获取MarkDown文件内容，内容以HTML形式展示出来
     * @param body
     * @return
     */
    @Headers({"Content-Type:text/plain"})
    @POST("/markdown/raw")
    Call<ResponseBody> markDown(@Body RequestBody body);


//    https://api.github.com/search/users?q=tom+repos:>42+followers:>1000
//    https://api.github.com/search/users?q=repos:>42+followers:>1000
//    https://api.github.com/search/users?q=followers:>1000
//    https://api.github.com/search/users?q=repos:>42

    /**
     * 获取热门开发者
     * @param query  查询条件
     * @param pageId 查询页数
     * @return
     */
    @GET("/search/users")
    Call<HotUserResult> searchUsers(@Query("q")String query,@Query("page")int pageId);

}
