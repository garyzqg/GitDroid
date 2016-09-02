package com.fuicui.gitdroid.gitdroid.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fuicui.gitdroid.gitdroid.MainActivity;
import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.commons.ActivityUtils;
import com.fuicui.gitdroid.gitdroid.network.GithubApi;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;


/**
 * 1.使用WebView来加载登录网址，实现登录账户的填写及登录
 * 2.是否同意授权，如果同意授权，重定向另一个URL，会有一个临时授权码code
 * 3.拿到临时授权码之后，使用API来获得用户Token
 * 4.获得用户Token之后，访问user,public_repo,repo，主要为了拿到用户信息展示出来
 */
public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.webView) WebView webView;
    @BindView(R.id.gifImageView) GifImageView gifImageView;

    private LoginPresenter presenter;
    private ActivityUtils activityUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);
    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWebView();
    }

    private void initWebView() {

        //删除所有的Cookie ,主要是为了清除登录记录
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();

        //加载网址
        webView.loadUrl(GithubApi.AUTH_URL);

        //让WebView获得焦点
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        //设置WdebView进度的监听，让他加载完成之后，隐藏动画，显示WebView
        webView.setWebChromeClient(webChromeClient);
        //监听WebView页面刷新时候，
        webView.setWebViewClient(webViewClient);
    }

    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress>=100){
                gifImageView.setVisibility(View.GONE);
            }
        }
    };

    private WebViewClient webViewClient = new WebViewClient(){

        //当WebView刷新的时候，这个方法就会触发，刷新包括当我们的账户填写错误，输对了，授权完了也会刷新
        @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Uri uri = Uri.parse(url);
            if (GithubApi.CALL_BACK.equals(uri.getScheme())){
                String code = uri.getQueryParameter("code");
                //得到临时授权码，去执行操作来获取Token
                presenter.login(code);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };


    /**
     * 需要实现的视图
     */
    @Override public void gotoMainActivity() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override public void showProgress() {
        gifImageView.setVisibility(View.VISIBLE);
    }

    @Override public void resetWebView() {
        initWebView();
    }
}
