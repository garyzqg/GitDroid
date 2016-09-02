package com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.commons.ActivityUtils;
import com.fuicui.gitdroid.gitdroid.components.FooterView;
import com.fuicui.gitdroid.gitdroid.favorite.dao.DBHelp;
import com.fuicui.gitdroid.gitdroid.favorite.dao.LocalRepoDao;
import com.fuicui.gitdroid.gitdroid.favorite.model.LocalRepo;
import com.fuicui.gitdroid.gitdroid.favorite.model.RepoConverter;
import com.fuicui.gitdroid.gitdroid.github.hotrepo.Language;
import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.model.Repo;
import com.fuicui.gitdroid.gitdroid.github.hotrepo.repolist.view.RepoListView;
import com.fuicui.gitdroid.gitdroid.github.repoinfo.RepoInfoActivity;
import com.mugen.Mugen;
import com.mugen.MugenCallbacks;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;


/**
 * A simple {@link Fragment} subclass.
 */
public class HotRepoListFragment extends Fragment implements RepoListView {


    @BindView(R.id.lvRepos) ListView lvRepos;
    @BindView(R.id.ptrClassicFrameLayout) PtrClassicFrameLayout ptrFrameLayout;
    @BindView(R.id.emptyView) TextView emptyView;
    @BindView(R.id.errorView) TextView errorView;

    private RepoListPresenter presenter;
    private RepoListAdapter adapter;
    private FooterView footerView;

    private ActivityUtils activityUtils;

    private static final String KEY_LANGUAGE="key_language";

    public static HotRepoListFragment getInstance(Language language){
        HotRepoListFragment fragment = new HotRepoListFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_LANGUAGE, language);
        fragment.setArguments(bundle);
        return fragment;
    }

    private Language getLanguage(){
        return (Language) getArguments().getSerializable(KEY_LANGUAGE);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_repo_list, container, false);
        activityUtils = new ActivityUtils(this);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new RepoListPresenter(this,getLanguage());

        adapter = new RepoListAdapter();
        lvRepos.setAdapter(adapter);

        lvRepos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击某一条跳转到详情页
                Repo repo = (Repo) adapter.getItem(position);
                RepoInfoActivity.open(getContext(),repo);
            }
        });
        
        lvRepos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                /**
                 * 1.获取到我们长按的ListView的数据
                 * 2.将数据添加到本地仓库数据库表中
                 * 3.Dao里面需要传入的是LocalRepo
                 * 4.Repo  LocalRepo   要做的：将Repo转换为LocalRepo
                 */

                Repo repo = adapter.getItem(position);
                LocalRepo localRepo = RepoConverter.convert(repo);
                new LocalRepoDao(DBHelp.getInstance(getContext())).createOrUpdate(localRepo);
                activityUtils.showToast("收藏成功");
                return false;
            }
        });

        if (adapter.getCount()==0){
            ptrFrameLayout.postDelayed(new Runnable() {
                @Override public void run() {
                    ptrFrameLayout.autoRefresh();
                }
            },200);
        }
        //刷新
        initPullToRefresh();

        //上拉加载
        /**
         * 当ListView滑动到最后一条再继续滑动，触发加载
         * 加载完成，移除添加的加载的布局
         */
        initLoadMore();
    }

    private void initLoadMore() {
        footerView = new FooterView(getContext());
        /**
         * 实现上拉加载监听
         */
        Mugen.with(lvRepos, new MugenCallbacks() {

            //当ListView滑动到最后，触发这个方法进行加载
            @Override public void onLoadMore() {
                //上拉加载数据业务的完成
                presenter.loadMore();
            }

            //是不是正在加载
            @Override public boolean isLoading() {
                return lvRepos.getFooterViewsCount()>0 && footerView.isLoading();
            }

            //是不是加载完了数据
            @Override public boolean hasLoadedAllItems() {
                return lvRepos.getFooterViewsCount()>0 && footerView.isComplete();
            }
        }).start();
    }

    private void initPullToRefresh() {
        // 使用当前对象做为key，来记录上一次的刷新时间,如果两次下拉太近，将不会触发新刷新
        ptrFrameLayout.setLastUpdateTimeRelateObject(this);
        // 关闭header所用时长
        ptrFrameLayout.setDurationToCloseHeader(1500);
        // 以下代码（只是修改了header样式）
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE " + " JAVA");
        header.setPadding(0, 60, 0, 60);
        // 修改Ptr的HeaderView效果
        ptrFrameLayout.setHeaderView(header);
        ptrFrameLayout.addPtrUIHandler(header);
        ptrFrameLayout.setBackgroundResource(R.color.colorRefresh);


        // 下拉刷新监听处理
        ptrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            // 当你"下拉时",将触发此方法
            @Override public void onRefreshBegin(PtrFrameLayout frame) {
                // 去做数据的加载，做具体的业务
                // 也就是说，你要抛开视图，到后台线程去做你的业务处理(数据刷新加载)
                presenter.refresh();
            }
        });
    }


    //下拉刷新刷新的时候视图
    /**
     * 1.显示刷新,显示刷新的视图
     * 2.停止刷新
     * 3.加载错误、网络加载失败
     * 4.刷新的数据为空，空页面
     * 5.拿到刷新得到数据
     */
    @Override public void refreshData(List<Repo> list) {
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override public void showContentView() {
        ptrFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override public void stopRefresh() {
        ptrFrameLayout.refreshComplete();
    }

    @Override public void showEmptyView() {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override public void showErrorView(String errormsg) {
        ptrFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    //上拉加载视图分析
    /**
     1. 显示加载视图
     2. 隐藏加载视图
     3. 加载失败视图
     4. 加载完成，拿到数据进行视图更新
     */

    @Override public void showLoadingView() {
        if (lvRepos.getFooterViewsCount()==0){
            lvRepos.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override public void hideLoadView() {
        lvRepos.removeFooterView(footerView);
    }

    @Override public void showLoadError(String msg) {
        if (lvRepos.getFooterViewsCount()==0){
            lvRepos.addFooterView(footerView);
        }
        footerView.showError();
    }

    @Override public void addLoadData(List<Repo> list) {
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }
}
