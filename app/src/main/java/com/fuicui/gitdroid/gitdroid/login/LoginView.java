package com.fuicui.gitdroid.gitdroid.login;

/**
 * 作者：yuanchao on 2016/8/26 0026 16:38
 * 邮箱：yuanchao@feicuiedu.com
 */
public interface LoginView {

    /**
     * 1.直接跳转到主页面
     * 2.显示信息
     * 3.显示我们的加载的画面
     * 4.页面进行刷新，重新进行请求
     */

    void gotoMainActivity();

    void showMessage(String msg);

    void showProgress();

    void resetWebView();
}
