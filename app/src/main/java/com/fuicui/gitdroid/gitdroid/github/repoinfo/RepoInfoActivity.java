package com.fuicui.gitdroid.gitdroid.github.repoinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.commons.ActivityUtils;
import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model.Repo;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RepoInfoActivity extends AppCompatActivity implements RepoInfoPresenter.RepoInfoView{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.ivIcon) ImageView ivIcon;
    @BindView(R.id.tvRepoName) TextView tvRepoName;
    @BindView(R.id.tvRepoStars) TextView tvRepoStars;
    @BindView(R.id.tvRepoInfo) TextView tvRepoInfo;
    @BindView(R.id.webView) WebView webView;
    @BindView(R.id.progressBar) ProgressBar progressBar;

    private ActivityUtils activityUtils;

    private Repo repo;

    private static final String KEY_REPO = "key_repo";
    private RepoInfoPresenter presenter;

    public static void open(Context context,@NonNull Repo repo){
        Intent intent = new Intent(context,RepoInfoActivity.class);
        intent.putExtra(KEY_REPO,repo);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        //设置View布局的时候就会触发我们的onContentChanged
        setContentView(R.layout.activity_repo_info);

        presenter = new RepoInfoPresenter(this);
        presenter.getReadme(repo);

    }

    @Override public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        repo = (Repo) getIntent().getSerializableExtra(KEY_REPO);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置Toolbar的标题
        getSupportActionBar().setTitle(repo.getName());
        tvRepoName.setText(repo.getFullName());
        tvRepoInfo.setText(repo.getDescription());
        tvRepoStars.setText(String.format("start: %d  fork: %d", repo.getStarCount(), repo.getForksCount()));
        ImageLoader.getInstance().displayImage(repo.getOwner().getAvatar(),ivIcon);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override public void setData(String htmlContent) {
        //加载html字符串数据
        webView.loadData(htmlContent,"text/html", "UTF-8");
    }
}
