package com.fuicui.gitdroid.gitdroid.github.hotuser;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.fuicui.gitdroid.gitdroid.R;
import com.fuicui.gitdroid.gitdroid.commons.ActivityUtils;
import com.fuicui.gitdroid.gitdroid.components.FooterView;
import com.fuicui.gitdroid.gitdroid.login.model.User;
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
 * 热门开发者
 */
public class HotUserFragment extends Fragment implements HotUserPresenter.HotUserView{


    @BindView(R.id.lvRepos)
    ListView lvUsers;
    @BindView(R.id.ptrClassicFrameLayout)
    PtrClassicFrameLayout ptrClassicFrameLayout;
    @BindView(R.id.emptyView)
    TextView emptyView;
    @BindView(R.id.errorView)
    TextView errorView;
    private HotUserAdapter adapter;

    private ActivityUtils activityUtils;
    private HotUserPresenter presenter;
    private FooterView footerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot_user, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        presenter = new HotUserPresenter(this);
        activityUtils = new ActivityUtils(this);
        adapter = new HotUserAdapter();
        lvUsers.setAdapter(adapter);

        //下拉刷新的方法
        initPullToRefresh();

        //上拉加载的方法
        initLoadMore();

        //如果没有数据，就自动刷新
        if (adapter.getCount() <= 0) {
            ptrClassicFrameLayout.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ptrClassicFrameLayout.autoRefresh();
                }
            }, 200);
        }
    }

    //加载的基本配置
    private void initLoadMore() {
        footerView = new FooterView(getContext());
        Mugen.with(lvUsers, new MugenCallbacks() {
            @Override
            public void onLoadMore() {
                presenter.loadMore();
            }

            //判断是不是正在加载
            @Override
            public boolean isLoading() {
                return lvUsers.getFooterViewsCount() > 0 && footerView.isLoading();
            }

            @Override
            public boolean hasLoadedAllItems() {
                return lvUsers.getFooterViewsCount() > 0 && footerView.isComplete();
            }
        }).start();
    }

    //刷新的基本配置
    private void initPullToRefresh() {
        ptrClassicFrameLayout.setLastUpdateTimeRelateObject(this);
        ptrClassicFrameLayout.setDurationToClose(2000);

        //完成刷新的操作
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.refresh();
            }
        });

        // 以下代码（只是修改了header样式）
        StoreHouseHeader header = new StoreHouseHeader(getContext());
        header.initWithString("I LIKE " + " JAVA");
        header.setPadding(0, 60, 0, 60);
        // 修改Ptr的HeaderView效果
        ptrClassicFrameLayout.setHeaderView(header);
        ptrClassicFrameLayout.addPtrUIHandler(header);
        ptrClassicFrameLayout.setBackgroundResource(R.color.colorRefresh);
    }

    /**
     * 刷新的视图改变
     * @param list
     */
    @Override
    public void refreshData(List<User> list) {
        adapter.clear();
        adapter.addAll(list);
    }

    @Override
    public void showRefreshView() {
        ptrClassicFrameLayout.setVisibility(View.VISIBLE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void stopRefresh() {
        ptrClassicFrameLayout.refreshComplete();
    }

    @Override
    public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override
    public void showEmptyView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        ptrClassicFrameLayout.setVisibility(View.GONE);
        emptyView.setVisibility(View.GONE);
        errorView.setVisibility(View.VISIBLE);
    }

    /**
     * 上拉加载的视图实现
     * @param list
     */
    @Override
    public void addLoadData(List<User> list) {
        adapter.addAll(list);
    }

    @Override
    public void showLoadView() {
        if (lvUsers.getFooterViewsCount()==0){
            lvUsers.addFooterView(footerView);
        }
        footerView.showLoading();
    }

    @Override
    public void hideLoadView() {
        lvUsers.removeFooterView(footerView);
    }
}
