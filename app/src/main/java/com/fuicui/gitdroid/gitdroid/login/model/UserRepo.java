package com.fuicui.gitdroid.gitdroid.login.model;

/**
 * 作者：yuanchao on 2016/8/26 0026 15:17
 * 邮箱：yuanchao@feicuiedu.com
 */

//主要是为了暂时存储我们的Token值以及请求得到的个人信息
public class UserRepo {

    private UserRepo(){}

    private static String accessToken;

    private static User user;

    // 当前是否有token
    public static boolean hasAccessToken(){
        return accessToken != null;
    }
    // 当前是否是"空的"(还没有登陆)
    public static boolean isEmpty(){
        return accessToken == null || user == null;
    }
    // 清除信息
    public static void clear(){
        accessToken = null;
        user = null;
    }


    public static void setAccessToken(String accessToken) {
        UserRepo.accessToken = accessToken;
    }

    public static void setUser(User user) {
        UserRepo.user = user;
    }

    public static String getAccessToken() {
        return accessToken;
    }

    public static User getUser() {
        return user;
    }
}
